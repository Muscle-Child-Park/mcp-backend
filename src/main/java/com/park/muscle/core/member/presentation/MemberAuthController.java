package com.park.muscle.core.member.presentation;

import com.park.muscle.core.member.application.MemberAuthService;
import com.park.muscle.core.member.dto.request.LoginRequest;
import com.park.muscle.core.member.dto.request.OnboardingQuestionRequest;
import com.park.muscle.core.member.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/member/auth")
@RequiredArgsConstructor
@RestController
@Tag(name = "Member Auth Management", description = "Manage only the authentication authorization of the member")
public class MemberAuthController {

    private final MemberAuthService memberAuthService;


    @Operation(summary = "회원 등록 또는 로그인", description = "Register a new Member or Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원 로그인 또는 가입 및 아이디 발급 성공"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginMember(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = memberAuthService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loginResponse);
    }

    @Operation(summary = "온보딩 정보 등록", description = "Register on-boarding ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Member profile updated successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Member not found")
    })
    @PostMapping("/{memberId}/add-onboarding")
    public ResponseEntity<Void> addOnboardingQuestion(@PathVariable Long memberId,
                                                      @Valid
                                                      @RequestBody OnboardingQuestionRequest onboardingQuestionRequest) {
        memberAuthService.addOnboardingQuestion(memberId, onboardingQuestionRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "온보딩 정보 등록", description = "Register on-boarding ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Member account deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Member not found")
    })
    @DeleteMapping("/{memberId}/delete")
    public ResponseEntity<String> deleteOwnMemberAccount(@PathVariable Long memberId) {

        memberAuthService.deleteMember(memberId);
        return new ResponseEntity<>("Member deleted successfully", HttpStatus.NO_CONTENT);
    }
}