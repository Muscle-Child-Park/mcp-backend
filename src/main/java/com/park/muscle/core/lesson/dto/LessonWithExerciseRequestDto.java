package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.exercise.dto.ExerciseRequestDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LessonWithExerciseRequestDto {
    private LessonRequestDto.Create lessonRequestDto;
    private List<ExerciseRequestDto.Create> exerciseRequestDtos;
}
