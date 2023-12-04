package com.park.muscle.core.personalexercise.application;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.personalexercise.domain.PersonalExercise;
import com.park.muscle.core.personalexercise.domain.PersonalExerciseRepository;
import com.park.muscle.core.personalexercise.dto.response.PersonalExerciseResponse.AllPersonalExerciseResponse;
import com.park.muscle.core.personalexercise.exception.PersonalExerciseNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
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
        return personalExerciseRepository.findById(personalExerciseId)
                .orElseThrow(PersonalExerciseNotFoundException::new);
    }

    public void updateExercises(final List<Exercise> exercises, PersonalExercise personalExercise) {
        List<Exercise> existedExercises = personalExercise.getExercises();
        existedExercises.forEach(existed ->
                exercises.stream()
                .filter(updated -> existed.getId().equals(updated.getId()))
                .findFirst()
                .ifPresent(personalExercise::updateExercise));
    }

    public List<AllPersonalExerciseResponse> getAllPersonalEx(final List<PersonalExercise> personalExercises) {
        return personalExercises.stream()
                .map(AllPersonalExerciseResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
