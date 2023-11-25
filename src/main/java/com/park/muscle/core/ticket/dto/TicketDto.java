package com.park.muscle.core.ticket.dto;

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

public class TicketDto {

    @Getter
    public static class create {
        private Long memberId;
        private String trainerTagId;
        private int totalQuantity;

        public Ticket toEntity(Member member, Trainer trainer) {
            return Ticket.builder()
                    .trainer(trainer)
                    .member(member)
                    .totalQuantity(this.totalQuantity)
                    .build();
        }
    }

    @Getter
    public static class TrainerTicketResponse {
        private final String trainer;
        private final String member;
        private final int totalQuantity;
        private final boolean accepted;

        public TrainerTicketResponse(Trainer trainer, Member member, int totalQuantity, boolean accepted) {
            this.trainer = trainer.getUniqueTag().formattedId();
            this.member = member.getUniqueTag().formattedId();
            this.totalQuantity = totalQuantity;
            this.accepted = accepted;
        }
    }

    @Getter
    public static class TicketResponse {

        @Schema(description = "member의 고유 ID")
        private final String memberId;

        @Schema(description = "trainer 고유 ID")
        private final String trainerId;

        @Schema(description = "trainer name")
        private final String trainerName;

        @Schema(description = "ticket 고유 ID")
        private final String ticketId;

        public TicketResponse(Member member, Trainer trainer, Ticket ticket) {
            this.memberId = member.getId().toString();
            this.trainerId = trainer.getId().toString();
            this.ticketId = ticket.getId().toString();
            this.trainerName = trainer.getName();
        }
    }

    @Getter
    public static class TicketCreateResponse {
        @Schema(description = "멤버, 트레이너, 티켓 id가 반환되는 dto")
        private final TicketResponse ticket;

        public TicketCreateResponse(TicketResponse ticket) {
            this.ticket = ticket;
        }
    }

    @Getter
    @Builder
    public static class PendingMemberNameResponse {
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
        private String trainerName;
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
}
