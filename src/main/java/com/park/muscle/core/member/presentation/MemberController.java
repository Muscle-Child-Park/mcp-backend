package com.park.muscle.core.member.presentation;

import com.park.muscle.core.member.application.MemberService;
import com.park.muscle.core.member.dto.request.FurtherInfoRequest;
import com.park.muscle.core.member.dto.request.OnboardingQuestionRequest;
import com.park.muscle.core.member.dto.request.SignUpRequest;
import com.park.muscle.core.member.dto.response.SignUpResponse;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/signup")
    public ResponseEntity<SignUpResponse> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = memberService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(signUpResponse);
    }

    @PostMapping("/members/{memberId}/add-info")
    public ResponseEntity<Void> addFurtherInfo(@PathVariable Long memberId,
                                               @Valid @RequestBody FurtherInfoRequest furtherInfoRequest) {
        memberService.addFurtherInfo(memberId, furtherInfoRequest);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/members/{memberId}/add-onboarding")
    public ResponseEntity<Void> addOnboardingQuestion(@PathVariable Long memberId,
                                                      @Valid @RequestBody OnboardingQuestionRequest onboardingQuestionRequest) {
        memberService.addOnboardingQuestion(memberId, onboardingQuestionRequest);
        return ResponseEntity.ok()
                .build();
    }
}