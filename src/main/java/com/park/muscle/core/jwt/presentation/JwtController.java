package com.park.muscle.core.jwt.presentation;

import static com.park.muscle.core.member.application.MemberAuthService.setCookieAndHeader;

import com.park.muscle.core.jwt.application.JwtTokenReissueService;
import com.park.muscle.core.jwt.dto.ReIssueTokenDto;
import com.park.muscle.global.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Token Management", description = "APIs related to managing token")
public class JwtController {

    private final JwtTokenReissueService jwtTokenReissueService;

    @Operation(summary = "JWT 재발급", description = "JWT를 재발급 할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰이 성공적으로 재발급되었습니다."),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/api/jwt/refresh")
    public ResponseEntity<DataResponse<ReIssueTokenDto>> reIssueToken(@CookieValue(name = "refreshToken") String refreshToken) {
        ReIssueTokenDto reIssueTokenDto = jwtTokenReissueService.reIssueToken(refreshToken);
        HttpHeaders headers = setCookieAndHeader(reIssueTokenDto);

        return new ResponseEntity<>(DataResponse.of(HttpStatus.CREATED, "Token 재발급 성공", reIssueTokenDto),
                HttpStatus.CREATED);
    }
}
