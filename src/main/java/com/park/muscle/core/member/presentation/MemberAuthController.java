package com.park.muscle.core.member.presentation;

import com.park.muscle.core.member.application.MemberAuthService;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.dto.request.OnboardingQuestionRequest;
import com.park.muscle.core.member.dto.request.LoginRequest;
import com.park.muscle.core.member.dto.response.LoginResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@RequestMapping("/api/members/auth")
@RequiredArgsConstructor
@RestController
public class MemberAuthController {

    private final MemberAuthService memberAuthService;

    @ApiOperation(value = "회원 등록", response = Member.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered a new member"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = memberAuthService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loginResponse);
    }


    @ApiOperation(value = "온보딩 정보 등록")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Member profile updated successfully"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Member not found")
    })
    @PostMapping("/{memberId}/add-onboarding")
    public ResponseEntity<Void> addOnboardingQuestion(@PathVariable Long memberId,
                                                      @Valid
                                                      @RequestBody OnboardingQuestionRequest onboardingQuestionRequest) {
        memberAuthService.addOnboardingQuestion(memberId, onboardingQuestionRequest);
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "멤버 계정 삭제")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Member account deleted successfully"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Member not found")
    })
    @DeleteMapping("/{memberId}/delete")
    public ResponseEntity<String> deleteOwnMemberAccount(@PathVariable Long memberId) {

        memberAuthService.deleteMember(memberId);
        return new ResponseEntity<>("Member deleted successfully", HttpStatus.NO_CONTENT);
    }
}