package com.park.muscle.core.personalexercise.dto.response;

import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.personalexercise.domain.PersonalExercise;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class PersonalExerciseResponse {

    @Getter
    @Builder
    public static class PersonalExerciseCreateResponse {
        private long id;

        public static PersonalExerciseCreateResponse fromEntity(PersonalExercise personalExercise) {
            return PersonalExerciseCreateResponse.builder()
                    .id(personalExercise.getId())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class AllPersonalExerciseResponse {
        private List<PersonalExercise> personalExercises;

        public static AllPersonalExerciseResponse fromEntity(List<PersonalExercise> personalExercise) {
            return AllPersonalExerciseResponse.builder()
                    .personalExercises(personalExercise)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class OwnPersonalExerciseResponse {
        private Exercise exercise;

        public static OwnPersonalExerciseResponse fromEntity(Exercise exercise) {
            return OwnPersonalExerciseResponse.builder()
                    .exercise(exercise)
                    .build();
        }
    }
}
