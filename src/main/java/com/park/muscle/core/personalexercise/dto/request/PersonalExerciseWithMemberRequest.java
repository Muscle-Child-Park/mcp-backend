package com.park.muscle.core.personalexercise.dto.request;

import com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithPersonal;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.UpdateExerciseWithPersonal;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonalExerciseWithMemberRequest {

    @Getter
    public static class CreatePersonalExercise{
        private PersonalExerciseRequest.Create personalExerciseRequest;
        private List<CreateExerciseWithPersonal> exerciseRequestDtos;
    }

    @Getter
    public static class UpdatePersonalExercise{
        private PersonalExerciseRequest.Update personalExerciseUpdateRequest;
        private List<UpdateExerciseWithPersonal> exerciseUpdateRequestDtos;
    }
}
