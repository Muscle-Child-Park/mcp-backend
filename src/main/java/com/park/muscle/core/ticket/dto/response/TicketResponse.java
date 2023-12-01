package com.park.muscle.core.ticket.dto.response;

import com.park.muscle.core.exercise.domain.ClassType;
import com.park.muscle.core.lesson.domain.Lesson;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.trainer.domain.Trainer;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class TicketResponse {

    @Getter
    public static class TicketTrainerResponse {
        private final String trainer;
        private final String member;
        private final int totalQuantity;
        private final boolean accepted;

        public TicketTrainerResponse(Trainer trainer, Member member, int totalQuantity, boolean accepted) {
            this.trainer = trainer.getUniqueTag().formattedId();
            this.member = member.getUniqueTag().formattedId();
            this.totalQuantity = totalQuantity;
            this.accepted = accepted;
        }
    }

    @Getter
    @Builder
    public static class TicketMemberResponse {
        private String name;
        private int totalQuantity;
        private int leftQuantity;

        public static TicketMemberResponse fromEntity(Trainer trainer, int totalQuantity, int leftQuantity) {
            return TicketMemberResponse.builder()
                    .name(trainer.getName())
                    .totalQuantity(totalQuantity)
                    .leftQuantity(leftQuantity)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class TicketBasicResponse {
        @Schema(description = "member의 고유 ID")
        private final long memberId;

        @Schema(description = "trainer 고유 ID")
        private final long trainerId;

        @Schema(description = "ticket 고유 ID")
        private final long ticketId;

        @Schema(description = "trainer name")
        private final String trainerName;

        public static TicketBasicResponse fromEntity(Member member, Trainer trainer, Ticket ticket) {
            return TicketBasicResponse.builder()
                    .memberId(member.getId())
                    .trainerId(trainer.getId())
                    .ticketId(ticket.getId())
                    .trainerName(trainer.getName())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class PendingMemberNameResponse {
        @Schema(description = "ticket acceptance waiting list")
        private List<String> names;

        public static PendingMemberNameResponse fromEntity(List<String> members) {
            return PendingMemberNameResponse.builder()
                    .names(members)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class TrainerInfoByTicketResponse {
        @Schema(description = "trainer name 반환")
        private String trainerName;
        @Schema(description = "ticket accept 여부 반환")
        private boolean isAccept;

        public static TrainerInfoByTicketResponse fromEntity(Ticket ticket) {
            return TrainerInfoByTicketResponse.builder()
                    .trainerName(ticket.getTrainer().getName())
                    .isAccept(ticket.isAccepted())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class LessonByTicketResponse {
        @Schema(description = "Lesson id 반환")
        private Long id;

        @Schema(description = "Lesson title 반환")
        private String title;

        @Schema(description = "lessonDate 반환")
        private LocalDateTime lessonDate;

        @Schema(description = "timeSlot 반환")
        private String timeSlot;

        @Schema(description = "feedback 반환")
        private String feedback;

        private ClassType classType;

        @Schema(description = "completionToggle 반환")
        private boolean completionToggle;

        public static LessonByTicketResponse fromEntity(Lesson lesson) {
            return LessonByTicketResponse.builder()
                    .id(lesson.getId())
                    .title(lesson.getTitle())
                    .lessonDate(lesson.getLessonDate())
                    .timeSlot(lesson.getTimeSlot())
                    .feedback(lesson.getFeedback())
                    .classType(lesson.getClassType())
                    .completionToggle(lesson.isCompletionToggle())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class LessonByTicketSimpleResponse {
        @Schema(description = "Lesson id 반환")
        private Long id;

        @Schema(description = "Lesson title 반환")
        private String title;

        @Schema(description = "lessonDate 반환")
        private LocalDateTime lessonDate;

        @Schema(description = "timeSlot 반환")
        private String timeSlot;

        public static LessonByTicketSimpleResponse fromEntity(Lesson lesson) {
            return LessonByTicketSimpleResponse.builder()
                    .id(lesson.getId())
                    .title(lesson.getTitle())
                    .lessonDate(lesson.getLessonDate())
                    .timeSlot(lesson.getTimeSlot())
                    .build();
        }
    }
}
