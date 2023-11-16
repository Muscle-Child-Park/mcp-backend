package com.park.muscle.core.exercise.application;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseRepository;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.Create;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public List<Exercise> saveAll(final List<ExerciseRequestDto.Create> exerciseRequestDto) {
        List<Exercise> exercises = exerciseRequestDto.stream()
                .map(Create::toEntity)
                .collect(Collectors.toList());
        exerciseRepository.saveAll(exercises);
        return exercises;
    }

    public Exercise findById(final Long exerciseId) {
        return exerciseRepository.findById(exerciseId).orElseThrow(() -> new IllegalArgumentException("Could NOT Found"));
    }
}

