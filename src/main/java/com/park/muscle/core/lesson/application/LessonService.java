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

    public static List<Lesson> getMemberReservations(Long memberId) {
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
    }
}
