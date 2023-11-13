package com.park.muscle.core.lesson.presentation;

import static com.park.muscle.core.lesson.dto.LessonRequestDto.*;
import static com.park.muscle.core.lesson.dto.LessonResponseDto.*;

import com.park.muscle.core.exercise.application.ExerciseService;
import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.lesson.application.LessonService;
import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.ticket.application.TicketService;
import com.park.muscle.core.ticket.domain.Ticket;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/lesson")
@RequiredArgsConstructor
@RestController
public class LessonController {

    private final LessonService lessonService;
    private final TicketService ticketService;
    private final ExerciseService exerciseService;

    @PostMapping("/create")
    public ResponseEntity<LessonCreateResponse> createLesson(@Valid @RequestBody Create lessonRequestDto) {
        Ticket ticket = ticketService.findById(lessonRequestDto.getTicketId());
        List<Exercise> exercises = exerciseService.save(lessonRequestDto);
        Lesson lesson = lessonRequestDto.toEntity(ticket, exercises);
        lessonService.save(lesson);
        LessonCreateResponse lessonCreateResponse = LessonCreateResponse.fromEntity(lessonRequestDto, lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonCreateResponse);
    }
}
