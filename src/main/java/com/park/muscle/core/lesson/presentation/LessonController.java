package com.park.muscle.core.lesson.presentation;

import static com.park.muscle.core.lesson.dto.ExerciseRequestDto.Create;
import static com.park.muscle.core.lesson.dto.LessonResponseDto.LessonCreateResponse;

import com.park.muscle.core.lesson.application.ExerciseService;
import com.park.muscle.core.lesson.application.LessonService;
import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.lesson.dto.ExerciseResponseDto.CreateResponse;
import com.park.muscle.core.lesson.dto.LessonRequestDto;
import com.park.muscle.core.lesson.dto.LessonWithExerciseRequestDto;
import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.core.ticket.domain.Ticket;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
@RestController
public class LessonController {

    private final LessonService lessonService;
    private final ExerciseService exerciseService;
    private final TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<LessonCreateResponse> createLesson(@Valid @RequestBody LessonWithExerciseRequestDto lessonWithExerciseRequestDto) {
        LessonRequestDto.Create lessonsRequestDto = lessonWithExerciseRequestDto.getLessonRequestDto();
        List<Create> exercisesRequestDto = lessonWithExerciseRequestDto.getExerciseRequestDtos();

        long ticketId = lessonsRequestDto.getTicketId();
        Ticket ticket = ticketService.findById(ticketId);
        Lesson lesson = lessonsRequestDto.toEntity(ticket);

        lessonService.save(lesson);
        CreateResponse createResponse = exerciseService.saveAll(exercisesRequestDto, lesson);

        LessonCreateResponse lessonCreateResponse = LessonCreateResponse.fromEntity(lessonsRequestDto, createResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonCreateResponse);
    }
}
