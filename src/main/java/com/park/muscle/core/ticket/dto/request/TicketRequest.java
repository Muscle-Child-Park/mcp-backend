package com.park.muscle.core.ticket.dto.request;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.ticket.domain.Ticket;
import com.park.muscle.core.trainer.domain.Trainer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class TicketRequest {

    @Getter
    public static class Create {
        @Schema(description = "트레이너 고유 아이디")
        private Long trainerId;
        @Schema(description = "멤버 태그 아이디", example = "#00001")
        private String memberTagId;
        @Schema(description = "수업 생성 가능 횟 수")
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
