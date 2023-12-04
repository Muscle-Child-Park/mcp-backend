package com.park.muscle.core.exercise.application;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseRepository;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithLesson;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithPersonal;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.UpdateExerciseWithPersonal;
import com.park.muscle.core.exercise.exception.ExerciseNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public List<Exercise> saveExerciseWithLesson(final List<CreateExerciseWithLesson> exerciseRequestDto) {
        List<Exercise> exercises = exerciseRequestDto.stream()
                .map(CreateExerciseWithLesson::toEntity)
                .collect(Collectors.toList());
        exerciseRepository.saveAll(exercises);
        return exercises;
    }

    public List<Exercise> saveExerciseWithMember(final List<CreateExerciseWithPersonal> exerciseRequestDto) {
        List<Exercise> exercises = exerciseRequestDto.stream()
                .map(CreateExerciseWithPersonal::toEntity)
                .collect(Collectors.toList());
        exerciseRepository.saveAll(exercises);
        return exercises;
    }

    public Exercise findById(final Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(ExerciseNotFoundException::new);
    }

    public List<Exercise> updateExercises(final List<UpdateExerciseWithPersonal> exerciseUpdateRequestDtos) {
        List<Exercise> exercises = new ArrayList<>();
        exerciseUpdateRequestDtos.forEach(exerciseUpdateRequestDto -> {
            Exercise exercise = findById(exerciseUpdateRequestDto.getExerciseId());
            exercise.updateExercise(exerciseUpdateRequestDto);
            exercises.add(exercise);
            exerciseRepository.save(exercise);
        });
        return exercises;
    }
}

