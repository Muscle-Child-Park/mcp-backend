package com.park.muscle.core.exercise.application;

import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.exercise.domain.ExerciseLogRepository;
import com.park.muscle.core.exercise.dto.LogRequestDto.LessonLogUpdateDto;
import com.park.muscle.core.exercise.dto.LogRequestDto.PersonalLogReflectionDto;
import com.park.muscle.core.exercise.dto.LogRequestDto.PersonalLogUpdateDto;
import com.park.muscle.core.exercise.dto.LogResponseDto.LogReflectionResponseDto;
import com.park.muscle.core.exercise.exception.ExerciseDiaryNotFoundException;
import com.park.muscle.core.personalexercise.application.PersonalExerciseService;
import com.park.muscle.core.personalexercise.domain.PersonalExercise;
import com.park.muscle.core.personalexercise.domain.PersonalExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseLogService {
    private final PersonalExerciseService personalExerciseService;
    private final ExerciseLogRepository exerciseLogRepository;
    private final PersonalExerciseRepository personalExerciseRepository;

    @Transactional
    public LogReflectionResponseDto addPersonalExerciseDiary(PersonalLogReflectionDto personalLogReflectionDto) {
        ExerciseDiary exerciseDiary = personalLogReflectionDto.toEntity(personalLogReflectionDto.getLog());
        save(exerciseDiary);
        PersonalExercise personalExercise = personalExerciseService.findPersonalExerciseById(
                personalLogReflectionDto.getPersonalId());
        personalExercise.addExerciseDiary(exerciseDiary);
        personalExerciseRepository.save(personalExercise);
        return LogReflectionResponseDto.fromEntity(exerciseDiary);
    }

    public ExerciseDiary updatePersonalExerciseLog(final PersonalLogUpdateDto personalLogUpdateDto) {
        ExerciseDiary exerciseDiary = getOwnLogById(personalLogUpdateDto.getExerciseDiaryId());
        exerciseDiary.updateLog(personalLogUpdateDto.getLog());
        exerciseLogRepository.save(exerciseDiary);
        return exerciseDiary;
    }

    public ExerciseDiary updateLessonExerciseLog(final LessonLogUpdateDto lessonLogUpdateDto) {
        ExerciseDiary exerciseDiary = getOwnLogById(lessonLogUpdateDto.getExerciseDiaryId());
        exerciseDiary.updateLog(lessonLogUpdateDto.getLog());
        exerciseLogRepository.save(exerciseDiary);
        return exerciseDiary;
    }

    public void save(final ExerciseDiary exerciseDiary) {
        exerciseLogRepository.save(exerciseDiary);
    }

    public ExerciseDiary getOwnLogById(final long exerciseDiaryId) {
        return exerciseLogRepository.findById(exerciseDiaryId)
                .orElseThrow(ExerciseDiaryNotFoundException::new);
    }
}
