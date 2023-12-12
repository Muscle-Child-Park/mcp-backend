package com.park.muscle.core.ticket.dto.request;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.trainer.domain.Trainer;
import lombok.Getter;

public class TicketRequest {

    @Getter
    public static class Create {
        private Long trainerId;
        private String memberTagId;
        private int totalQuantity;

        public Ticket toEntity(Member member, Trainer trainer) {
            return Ticket.builder()
                    .trainer(trainer)
                    .member(member)
                    .totalQuantity(this.totalQuantity)
                    .build();
        }
    }
}
