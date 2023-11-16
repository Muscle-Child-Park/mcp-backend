package com.park.muscle.core.lesson.presentation;

import static com.park.muscle.core.exercise.dto.ExerciseRequestDto.Create;
import static com.park.muscle.core.lesson.dto.LessonResponseDto.LessonCreateResponse;

import com.park.muscle.core.exercise.application.ExerciseService;
import com.park.muscle.core.lesson.application.LessonService;
import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.lesson.dto.LessonResponseDto.LessonRetrieveResponse;
import com.park.muscle.core.lesson.dto.LessonWithExerciseRequestDto;
import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.core.ticket.domain.Ticket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
@RestController
@Api(tags = "Lesson Management")
public class LessonController {

    private final LessonService lessonService;
    private final ExerciseService exerciseService;
    private final TicketService ticketService;

    @ApiOperation(value = "수업 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "수업 조회 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonRetrieveResponse> getOwnLessonById(@PathVariable Long lessonId) {
        Lesson lesson = lessonService.getOwnLessonById(lessonId);
        List<Exercise> exercises = lesson.getExercises();
        LessonRetrieveResponse lessonRetrieveResponse = LessonRetrieveResponse.fromEntity(lesson, exercises);
        return ResponseEntity.status(HttpStatus.OK).body(lessonRetrieveResponse);
    }

    @ApiOperation(value = "수업 등록")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "수업 등록 성공"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/create/{ticketId}")
    public ResponseEntity<LessonCreateResponse> createLesson(@PathVariable Long ticketId,
            @Valid @RequestBody LessonWithExerciseRequestDto lessonWithExerciseRequestDto) {

        Lesson lessons = lessonWithExerciseRequestDto.getLessonRequestDto().toEntity();
        List<Create> exercisesRequestDto = lessonWithExerciseRequestDto.getExerciseRequestDtos();
        List<Exercise> exercises = exerciseService.saveAll(exercisesRequestDto);
        Ticket ticket = ticketService.findById(ticketId);

        lessons.updateExercise(exercises);
        ticket.setLesson(lessons);
        ticketService.saveTicket(ticket);

        lessonService.save(lessons);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(LessonCreateResponse.fromEntity(lessons));
    }

    @ApiOperation(value = "피드백 추가", notes = "수업에 대한 피드백을 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "피드백이 성공적으로 추가되었습니다."),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/{lessonId}/feedback")
    public ResponseEntity<String> addFeedbackToLesson(@RequestBody Map<String, String> feedback,
                                                      @PathVariable final long lessonId) {
        lessonService.addFeedback(lessonId, feedback.get("feedback"));
        return ResponseEntity.status(HttpStatus.OK).body("피드백이 성공적으로 추가되었습니다.");
    }
}
