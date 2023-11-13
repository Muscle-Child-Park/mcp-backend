package com.park.muscle.core.lesson.application;

import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.lesson.domain.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

/*    public static List<Lesson> getMemberReservations(Long memberId) {
        return null;
    }

    public boolean reserveSchedule(Long trainerId, ReserveRequest reserveRequest) {
        return false;
    }

    public boolean registerLesson(LessonRequest lessonRequest) {
        return false;
    }

    public boolean addMemberExercise(Long memberId, MemberExerciseRequest exerciseRequest) {
        return false;
    }

    public boolean addFeedback(Long lessonId, FeedbackRequest feedbackRequest) {
        return false;
    }*/

    public void save(final Lesson lesson) {
        lessonRepository.save(lesson);
    }
}
