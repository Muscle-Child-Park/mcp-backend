package com.park.muscle.mock;

import static com.park.muscle.global.enumerate.SocialType.KAKAO;

import com.park.muscle.core.member.application.MemberAuthService;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.Name;
import com.park.muscle.core.member.domain.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/members/auth")
@Api(tags = "auth management system")
public class AuthController {

    private final MemberAuthService memberAuthService;

    @ApiOperation(value = "소셜 로그인", response = Member.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully logged in as a member"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Member not found")
    })
    @PostMapping("/temp")
    public ResponseEntity<Member> loginMember(
            @ApiParam(value = "User's social login provider (e.g., KAKAO, APPLE)", required = true)
            @RequestParam String socialProvider,
            @ApiParam(value = "User's social login ID (e.g., KAKAO UID, APPLE UID)", required = true)
            @RequestParam String socialLoginId) {
        // 예시: 카카오 소셜 로그인 처리
        if ("KAKAO".equalsIgnoreCase(socialProvider)) {
            Member member = new Member(KAKAO, socialLoginId, Name.from("홍길동"), Role.ROLE_MEMBER);
            return ResponseEntity.ok(member);
        }
        // 사용자를 찾지 못한 경우 404 Not Found 또는 401 Unauthorized 반환
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
