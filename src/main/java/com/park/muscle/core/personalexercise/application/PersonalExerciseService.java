package com.park.muscle.core.personalexercise.application;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.personalexercise.domain.PersonalExercise;
import com.park.muscle.core.personalexercise.domain.PersonalExerciseRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonalExerciseService {
    private final PersonalExerciseRepository personalExerciseRepository;

    public void save(final PersonalExercise personalExercise) {
        personalExerciseRepository.save(personalExercise);
    }

    public PersonalExercise findPersonalExerciseById(final long personalExerciseId) {
        return personalExerciseRepository.findById(personalExerciseId).orElseThrow();
    }

    public void updateExercises(final List<Exercise> exercises, PersonalExercise personalExercise) {
        List<Exercise> existedExercises = personalExercise.getExercises();
        existedExercises.forEach(existed ->
                exercises.stream()
                .filter(updated -> existed.getId().equals(updated.getId()))
                .findFirst()
                .ifPresent(personalExercise::updateExercise));
    }
}
