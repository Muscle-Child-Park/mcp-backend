package com.park.muscle.core.lesson.application;

import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.lesson.domain.LessonRepository;
import com.park.muscle.mock.ReserveRequest;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

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

    public boolean reserveSchedule(final Long trainerId, final ReserveRequest reserveRequest) {
        return false;
    }
}
