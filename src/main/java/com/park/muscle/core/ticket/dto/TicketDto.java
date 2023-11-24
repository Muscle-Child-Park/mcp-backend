package com.park.muscle.core.ticket.dto;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.trainer.domain.Trainer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class TicketDto {

    @Getter
    public static class create {
        private Long memberId;
        private Long trainerId;
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

        @Schema(description = "ticket 고유 ID")
        private final String ticketId;

        public TicketResponse(Member member, Trainer trainer, Ticket ticket) {
            this.memberId = member.getId().toString();
            this.trainerId = trainer.getId().toString();
            this.ticketId = ticket.getId().toString();
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
}
