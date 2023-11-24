package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithLesson;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LessonWithExerciseRequestDto {
    private LessonRequestDto.Create lessonRequestDto;
    private List<CreateExerciseWithLesson> exerciseRequestDtos;
}
