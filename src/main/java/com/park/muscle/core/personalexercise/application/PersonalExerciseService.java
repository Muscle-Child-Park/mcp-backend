package com.park.muscle.core.personalexercise.application;

import com.park.muscle.core.personalexercise.domain.PersonalExercise;
import com.park.muscle.core.personalexercise.domain.PersonalExerciseRepository;
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
}
