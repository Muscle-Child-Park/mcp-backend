package com.park.muscle.core.lesson.application;

import com.park.muscle.core.exercise.application.ExerciseLogService;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.exercise.dto.LogRequestDto.LessonLogReflectionDto;
import com.park.muscle.core.exercise.dto.LogRequestDto.LessonLogUpdateDto;
import com.park.muscle.core.exercise.dto.LogResponseDto.LogReflectionResponseDto;
import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.lesson.domain.LessonRepository;
import com.park.muscle.core.lesson.exception.LessonNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final ExerciseLogService exerciseLogService;
    private final LessonRepository lessonRepository;

    public void save(final Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void addFeedback(final Long lessonId, final String feedback) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(LessonNotFoundException::new);
        lesson.addFeedback(feedback);
        lessonRepository.save(lesson);
    }

    public Lesson getOwnLessonById(final Long lessonId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        return lesson.orElseThrow(LessonNotFoundException::new);
    }

    public LogReflectionResponseDto addExerciseDiary(LessonLogReflectionDto lessonLogReflectionDto) {
        ExerciseDiary exerciseDiary = lessonLogReflectionDto.toEntity(lessonLogReflectionDto.getLog());
        exerciseLogService.save(exerciseDiary);
        Lesson lesson = getOwnLessonById(lessonLogReflectionDto.getLessonId());
        lesson.addExerciseDiary(exerciseDiary);
        lessonRepository.save(lesson);
        return LogReflectionResponseDto.fromEntity(exerciseDiary);
    }

    public void updateExerciseDiary(final LessonLogUpdateDto lessonLogUpdateDto) {
        Lesson lesson = getOwnLessonById(lessonLogUpdateDto.getLessonId());
        ExerciseDiary exerciseDiary = exerciseLogService.updateLessonExerciseLog(lessonLogUpdateDto);
        lesson.updateExerciseDiary(exerciseDiary);
        lessonRepository.save(lesson);
    }
}
