package com.park.muscle.core.lesson.application;

import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.lesson.dto.request.LessonRequest;
import com.park.muscle.core.lesson.dto.request.ReserveRequest;
import com.park.muscle.core.member.dto.request.MemberExerciseRequest;
import com.park.muscle.core.trainer.dto.request.FeedbackRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

    public static List<Lesson> getMemberReservations(final Long memberId) {
        return null;
    }

    public boolean reserveSchedule(final Long trainerId, final ReserveRequest reserveRequest) {
        return false;
    }

    public boolean registerLesson(final LessonRequest lessonRequest) {
        return false;
    }

    public boolean addMemberExercise(final Long memberId, final MemberExerciseRequest exerciseRequest) {
        return false;
    }

    public boolean addFeedback(final Long lessonId, final FeedbackRequest feedbackRequest) {
        return false;
    }
}
