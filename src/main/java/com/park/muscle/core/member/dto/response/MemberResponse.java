package com.park.muscle.core.member.dto.response;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.ticket.dto.response.TicketResponse.LessonByTicketSimpleResponse;
import com.park.muscle.core.trainer.dto.TrainerResponse.TrainerTicketInfoResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class MemberResponse {

    @Getter
    @Builder
    public static class LoginResponse {
        @Schema(description = "발급된 액세스 토큰")
        private final String accessToken;

        @Schema(description = "발급된 리프레쉬 토큰")
        private final String refreshToken;

        @Schema(description = "멤버의 id만 반환되는 dto")
        private final SignUpResponse member;

        public static LoginResponse fromEntity(String accessToken, String refreshToken, SignUpResponse member) {
            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .member(member)
                    .build();
        }

        public LoginResponse(String accessToken, String refreshToken, SignUpResponse member) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.member = member;
        }
    }

    @Getter
    public static class SignUpResponse {

        @Schema(description = "member의 고유 ID")
        private final String memberId;

        @Schema(description = "member의 고유 TAG")
        private final String memberTag;

        public SignUpResponse(Member member) {
            this.memberId = member.getId().toString();
            this.memberTag = member.getUniqueTag().formattedId();
        }
    }

    @Getter
    @Builder
    public static class HomeResponse {
        List<TrainerTicketInfoResponse> trainerReservations;
        List<LessonByTicketSimpleResponse> flattenedList;

        public static HomeResponse fromEntity(List<TrainerTicketInfoResponse> trainerReservations,
                                              List<LessonByTicketSimpleResponse> flattenedList) {
            return HomeResponse.builder()
                    .trainerReservations(trainerReservations)
                    .flattenedList(flattenedList)
                    .build();
        }
    }
}
