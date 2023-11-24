package com.park.muscle.core.personalexercise.dto.request;

import com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithPersonal;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonalExerciseWithMemberRequest {
    private PersonalExerciseRequest.Create personalExerciseRequest;
    private List<CreateExerciseWithPersonal> exerciseRequestDtos;
}
