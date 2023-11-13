package com.park.muscle.core.exercise.application;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.Create;
import com.park.muscle.core.lesson.dto.LessonRequestDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
    public List<Exercise> save(final LessonRequestDto.Create lessonRequestDto) {
        return lessonRequestDto.getExercises().stream()
                .map(Create::toEntity)
                .collect(Collectors.toList());
    }
}
