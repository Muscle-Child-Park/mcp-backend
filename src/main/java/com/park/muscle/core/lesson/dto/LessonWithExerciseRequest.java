package com.park.muscle.core.lesson.dto;

import com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithLesson;
import com.park.muscle.core.lesson.dto.LessonRequest.LessonCreate;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Tag(name = "수업 생성", description = "수업 생성에 필요한 요청을 묶은 랩퍼 DTO")
public class LessonWithExerciseRequest {
    private LessonCreate lessonRequestDto;
    private List<CreateExerciseWithLesson> exerciseRequestDtos;
}
