package com.park.muscle.core.member.dto.response;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.ticket.dto.response.TicketResponse.LessonByTicketSimpleResponse;
import com.park.muscle.core.trainer.dto.TrainerResponse.TrainerTicketInfoResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpHeaders;

public class MemberResponse {

    @Getter
    @Builder
    @ApiResponse(description = "JWT와 로그인 정보를 반환하는 리스폰스")
    public static class LoginResponse {
        private final SignUpResponse signUpResponse;
        private final HttpHeaders httpHeaders;

        public static LoginResponse fromEntity(HttpHeaders httpHeaders, SignUpResponse signUpResponse) {
            return LoginResponse.builder()
                    .signUpResponse(signUpResponse)
                    .httpHeaders(httpHeaders)
                    .build();
        }
    }

    @Getter
    @Builder
    @ApiResponse(description = "JWT를 반환하는 리스폰스")
    public static class TokenResponse {
        @Schema(description = "발급된 액세스 토큰")
        private final String accessToken;

        @Schema(description = "발급된 리프레쉬 토큰")
        private final String refreshToken;

        public static TokenResponse fromEntity(String accessToken, String refreshToken) {
            return TokenResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
    }

    @Getter
    @Builder
    @ApiResponse(description = "로그인 정보를 반환하는 리스폰스")
    public static class SignUpResponse {

        @Schema(description = "member의 고유 ID")
        private final Long memberId;

        @Schema(description = "member의 고유 TAG")
        private final String memberTag;

        public static SignUpResponse fromEntity(Member member) {
            return SignUpResponse.builder()
                    .memberId(member.getId())
                    .memberTag(member.getUniqueTag().formattedId())
                    .build();
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
