package com.park.muscle.core.lesson.application;

import com.park.muscle.core.lesson.domain.Exercise;
import com.park.muscle.core.lesson.domain.ExerciseRepository;
import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.lesson.dto.ExerciseRequestDto;
import com.park.muscle.core.lesson.dto.ExerciseResponseDto;
import com.park.muscle.core.lesson.dto.ExerciseResponseDto.CreateResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseResponseDto.CreateResponse saveAll(final List<ExerciseRequestDto.Create> exerciseRequestDto,
                                                      final Lesson lesson) {
        List<Exercise> exercises = exerciseRequestDto.stream()
                .map(exercise -> exercise.toEntity(lesson))
                .collect(Collectors.toList());
        exerciseRepository.saveAll(exercises);
        return CreateResponse.fromEntity(exercises.get(0));
    }
}
