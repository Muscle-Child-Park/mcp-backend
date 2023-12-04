package com.park.muscle.core.exercise.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExerciseDiaryTest {

    @DisplayName("운동 다이어리 업데이트 테스트")
    @Test
    void 운동_로그_업데이트() {
        ExerciseDiary exerciseDiary = ExerciseDiary.builder()
                .memo("변경 전")
                .build();

        exerciseDiary.updateLog("변경 후");

        Assertions.assertThat(exerciseDiary.getMemo()).isEqualTo("변경 후");
    }
}