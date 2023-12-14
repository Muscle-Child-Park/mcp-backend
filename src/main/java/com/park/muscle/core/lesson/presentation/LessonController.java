package com.park.muscle.core.lesson.presentation;

import static com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithLesson;
import static com.park.muscle.core.exercise.dto.LogRequestDto.LessonLogReflectionDto;
import static com.park.muscle.core.lesson.dto.LessonRequest.FeedbackCreate;
import static com.park.muscle.core.lesson.dto.LessonResponse.LessonCreateResponse;

import com.park.muscle.core.exercise.application.ExerciseService;
import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.exercise.dto.LogRequestDto.LessonLogUpdateDto;
import com.park.muscle.core.exercise.dto.LogResponseDto.LogReflectionResponseDto;
import com.park.muscle.core.lesson.application.LessonService;
import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.lesson.dto.LessonRequest.LessonCreate;
import com.park.muscle.core.lesson.dto.LessonResponse.LessonRetrieveResponse;
import com.park.muscle.core.lesson.dto.LessonWithExerciseRequest;
import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.global.exception.ErrorResponse;
import com.park.muscle.global.response.DataResponse;
import com.park.muscle.global.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
@RestController
@Tag(name = "Lesson Management", description = "APIs related to managing lessons")
public class LessonController {
    private final LessonService lessonService;
    private final ExerciseService exerciseService;
    private final TicketService ticketService;

    @Operation(summary = "수업 조회", description = "수업을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수업 조회 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LessonRetrieveResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "잘못된 요청", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "잘못된 요청", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping("/{lessonId}")
    public ResponseEntity<DataResponse<LessonRetrieveResponse>> getLessonById(@PathVariable Long lessonId) {
        Lesson lesson = lessonService.getOwnLessonById(lessonId);
        List<Exercise> exercises = lesson.getExercises();
        ExerciseDiary exerciseDiary = lesson.getExerciseDiary();

        LogReflectionResponseDto logReflectionResponseDto = null;
        if (exerciseDiary != null) {
            logReflectionResponseDto = LogReflectionResponseDto.fromEntity(exerciseDiary);
        }
        LessonRetrieveResponse lessonRetrieveResponse = LessonRetrieveResponse.fromEntity(lesson, exercises,
                logReflectionResponseDto);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "수업 조회 성공", lessonRetrieveResponse), HttpStatus.OK);
    }

    @Operation(summary = "수업 등록", description = "수업을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "수업 등록 성공"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<DataResponse<LessonCreateResponse>> createLesson(
            @Valid @RequestBody LessonWithExerciseRequest lessonWithExerciseRequest) {

        LessonCreate lessonRequestDto = lessonWithExerciseRequest.getLessonRequestDto();
        Lesson lesson = lessonRequestDto.toEntity();
        lessonService.save(lesson);

        List<CreateExerciseWithLesson> exercisesRequestDto = lessonWithExerciseRequest.getExerciseRequestDtos();
        List<Exercise> exercises = exerciseService.saveExerciseWithLesson(exercisesRequestDto);
        lesson.addExercise(exercises);
        lessonService.save(lesson);

        Ticket ticket = ticketService.findTicketById(lessonRequestDto.getTicketId());
        ticket.setLesson(lesson);
        ticketService.saveTicket(ticket);
        LessonCreateResponse lessonCreateResponse = LessonCreateResponse.fromEntity(lesson, ticket.getId());
        return new ResponseEntity<>(DataResponse.of(HttpStatus.CREATED, "수업 생성 성공", lessonCreateResponse),
                HttpStatus.CREATED);
    }

    @Operation(summary = "피드백 생성", description = "수업에 대한 피드백을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "피드백이 성공적으로 추가되었습니다."),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/feedback/{lessonId}")
    public ResponseEntity<MessageResponse> addFeedbackToLesson(@Valid @RequestBody FeedbackCreate feedback,
                                                               @PathVariable final long lessonId) {
        lessonService.addFeedback(lessonId, feedback.getFeedback());
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.CREATED, "피드백 생성 성공"), HttpStatus.CREATED);
    }


    @Operation(summary = "회고 생성", description = "수업에 대한 회고를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회고가 생성되었습니다."),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/log")
    public ResponseEntity<DataResponse<LogReflectionResponseDto>> addLogToLesson(
            @Valid @RequestBody LessonLogReflectionDto lessonLogReflectionDto) {

        LogReflectionResponseDto logReflectionResponseDto = lessonService.addExerciseDiary(lessonLogReflectionDto);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.CREATED, "회고 추가 성공", logReflectionResponseDto),
                HttpStatus.CREATED);
    }

    @Operation(summary = "회고 업데이트", description = "수업에 대한 회고 업데이트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회고가 성공적으로 추가되었습니다."),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/log")
    public ResponseEntity<MessageResponse> updateLogToLesson(
            @Valid @RequestBody LessonLogUpdateDto lessonLogUpdateDto) {
        lessonService.updateExerciseDiary(lessonLogUpdateDto);
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "회고 업데이트 성공"), HttpStatus.OK);
    }
}
