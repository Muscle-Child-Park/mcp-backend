package com.park.muscle.core.lesson.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LessonWithExerciseRequestDto {
    private LessonRequestDto.Create lessonRequestDto;
    private List<ExerciseRequestDto.Create> exerciseRequestDtos;
}
