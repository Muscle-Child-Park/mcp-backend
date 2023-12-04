package com.park.muscle.core.lesson.presentation;

import static com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithLesson;
import static com.park.muscle.core.exercise.dto.LogRequestDto.LessonLogReflectionDto;
import static com.park.muscle.core.lesson.dto.LessonResponse.LessonCreateResponse;

import com.park.muscle.core.exercise.application.ExerciseService;
import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.exercise.dto.LogRequestDto.LessonLogUpdateDto;
import com.park.muscle.core.exercise.dto.LogResponseDto.LogReflectionResponseDto;
import com.park.muscle.core.lesson.application.LessonService;
import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.lesson.dto.LessonResponse.LessonRetrieveResponse;
import com.park.muscle.core.lesson.dto.LessonWithExerciseRequest;
import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.core.ticket.domain.Ticket;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
@RestController
@Tag(name = "Lesson Management", description = "APIs related to managing lessons")
public class LessonController {
    private final LessonService lessonService;
    private final ExerciseService exerciseService;
    private final TicketService ticketService;

    @Operation(summary = "수업 조회", description = "수업을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수업 조회 성공"),
            @ApiResponse(responseCode = "401", description = "잘못된 요청")
    })
    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonRetrieveResponse> getOwnLessonById(@PathVariable Long lessonId) {
        Lesson lesson = lessonService.getOwnLessonById(lessonId);
        List<Exercise> exercises = lesson.getExercises();
        ExerciseDiary exerciseDiary = lesson.getExerciseDiary();

        LogReflectionResponseDto logReflectionResponseDto = null;
        if (exerciseDiary != null) {
            logReflectionResponseDto = LogReflectionResponseDto.fromEntity(exerciseDiary);
        }
        LessonRetrieveResponse lessonRetrieveResponse = LessonRetrieveResponse.fromEntity(lesson, exercises, logReflectionResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(lessonRetrieveResponse);
    }

    @Operation(summary = "수업 등록", description = "수업을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "수업 등록 성공"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/create/{ticketId}")
    public ResponseEntity<LessonCreateResponse> createLesson(@PathVariable Long ticketId,
            @Valid @RequestBody LessonWithExerciseRequest lessonWithExerciseRequest) {
        Lesson lesson = lessonWithExerciseRequest.getLessonRequestDto().toEntity();
        List<CreateExerciseWithLesson> exercisesRequestDto = lessonWithExerciseRequest.getExerciseRequestDtos();
        List<Exercise> exercises = exerciseService.saveExerciseWithLesson(exercisesRequestDto);

        Ticket ticket = ticketService.findTicketById(ticketId);
        lesson.addExercise(exercises);
        ticket.setLesson(lesson);

        lessonService.save(lesson);
        ticketService.saveTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(LessonCreateResponse.fromEntity(lesson, ticketId));
    }

    @Operation(summary = "피드백 추가", description = "수업에 대한 피드백을 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "피드백이 성공적으로 추가되었습니다."),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/{lessonId}/feedback")
    public ResponseEntity<String> addFeedbackToLesson(@RequestBody Map<String, String> feedback,
                                                      @PathVariable final long lessonId) {
        lessonService.addFeedback(lessonId, feedback.get("feedback"));
        return ResponseEntity.status(HttpStatus.OK).body("피드백이 성공적으로 추가되었습니다.");
    }


    @Operation(summary = "회고 추가", description = "수업에 대한 회고를 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회고가 성공적으로 추가되었습니다."),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/log")
    public ResponseEntity<LogReflectionResponseDto> addLogToLesson(@RequestBody LessonLogReflectionDto lessonLogReflectionDto) {
        LogReflectionResponseDto logReflectionResponseDto = lessonService.addExerciseDiary(lessonLogReflectionDto);
        return ResponseEntity.status(HttpStatus.OK).body(logReflectionResponseDto);
    }

    @Operation(summary = "회고 업데이트", description = "수업에 대한 회고 업데이트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회고가 성공적으로 추가되었습니다."),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/log")
    public ResponseEntity<String> updateLogToLesson(@RequestBody LessonLogUpdateDto lessonLogUpdateDto) {
        lessonService.updateExerciseDiary(lessonLogUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body("update success");
    }
}
