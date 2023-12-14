package com.park.muscle.core.exercise.application;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseRepository;
import com.park.muscle.core.exercise.domain.ExerciseType;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithLesson;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithPersonal;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.UpdateExerciseWithPersonal;
import com.park.muscle.core.exercise.exception.ExerciseNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public List<Exercise> saveExerciseWithLesson(final List<CreateExerciseWithLesson> exerciseRequestDto) {
        List<Exercise> exercises = exerciseRequestDto.stream()
                .map(CreateExerciseWithLesson::toEntity)
                .collect(Collectors.toList());
        exerciseRepository.saveAll(exercises);
        return exercises;
    }


    @Transactional
    public List<Exercise> saveExerciseWithMember(final List<CreateExerciseWithPersonal> exerciseRequestDto) {
        List<Exercise> exercises = exerciseRequestDto.stream()
                .map(CreateExerciseWithPersonal::toEntity)
                .collect(Collectors.toList());
        exerciseRepository.saveAll(exercises);
        return exercises;
    }

    @Transactional(readOnly = true)
    public Exercise findById(final Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(ExerciseNotFoundException::new);
    }

    @Transactional
    public List<Exercise> updateExercises(final List<UpdateExerciseWithPersonal> exerciseUpdateRequestDtos) {
        List<Exercise> exercises = new ArrayList<>();
        exerciseUpdateRequestDtos.forEach(update -> {
            Exercise exercise = findById(update.getExerciseId());
            exercise.updateExercise(
                    ExerciseType.findType(update.getExerciseType()), update.getName(),
                    update.getWeight(), update.getReps(), update.getSets(), update.getRunTime()
            );
            exercises.add(exercise);
            exerciseRepository.save(exercise);
        });
        return exercises;
    }
}
