package com.park.muscle.core.lesson.application;

import com.park.muscle.core.exercise.application.ExerciseLogService;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.exercise.dto.LogRequestDto.LogReflectionDto;
import com.park.muscle.core.exercise.dto.LogRequestDto.LogUpdateDto;
import com.park.muscle.core.exercise.dto.LogResponseDto.LogReflectionResponseDto;
import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.lesson.domain.LessonRepository;
import com.park.muscle.core.member.application.MemberService;
import com.park.muscle.core.member.domain.Member;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final ExerciseLogService exerciseLogService;
    private final MemberService memberService;
    private final LessonRepository lessonRepository;

    public static List<Lesson> getMemberReservations(final Long memberId) {
        return null;
    }

    public void save(final Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void addFeedback(final Long lessonId, final String feedback) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 수업을 찾을 수 없습니다: " + lessonId));
        lesson.addFeedback(feedback);
        lessonRepository.save(lesson);
    }

    public Lesson getOwnLessonById(final Long lessonId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        return lesson.orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 수업을 찾을 수 없습니다: " + lessonId));
    }

    public LogReflectionResponseDto addExerciseDiary(LogReflectionDto logReflectionDto) {
        Member member = memberService.findMemberById(logReflectionDto.getMemberId());
        ExerciseDiary exerciseDiary = logReflectionDto.toEntity(member, logReflectionDto.getLog());
        exerciseLogService.save(exerciseDiary);
        Lesson lesson = getOwnLessonById(logReflectionDto.getLessonId());
        lesson.addExerciseDiary(exerciseDiary);
        lessonRepository.save(lesson);
        return LogReflectionResponseDto.fromEntity(exerciseDiary);
    }

    public void updateExerciseDiary(final LogUpdateDto logUpdateDto) {
        Lesson lesson = getOwnLessonById(logUpdateDto.getLessonId());
        ExerciseDiary exerciseDiary = exerciseLogService.updateExerciseLog(logUpdateDto.getExerciseDiaryId(),
                logUpdateDto.getLog());
        lesson.updateExerciseDiary(exerciseDiary);
        lessonRepository.save(lesson);
    }
}
