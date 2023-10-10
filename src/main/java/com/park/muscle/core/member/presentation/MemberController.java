package com.park.muscle.core.member.presentation;

import com.park.muscle.core.member.application.MemberService;
import com.park.muscle.core.member.dto.request.FurtherInfoRequest;
import com.park.muscle.core.member.dto.request.OnboardingQuestionRequest;
import com.park.muscle.core.member.dto.request.SignUpRequest;
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

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/signup")
    public ResponseEntity<Long> signup(@Valid @RequestBody final SignUpRequest signUpRequest) {
        Long createdId = memberService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdId);
    }

    @PostMapping("/members/{memberId}/add-info")
    public ResponseEntity<Void> addFurtherInfo(@PathVariable final Long memberId,
                                               @Valid @RequestBody final FurtherInfoRequest furtherInfoRequest) {
        memberService.addFurtherInfo(memberId, furtherInfoRequest);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/members/{memberId}/add-onboarding")
    public ResponseEntity<Void> addOnboardingQuestion(@PathVariable final Long memberId,
                                                      @Valid @RequestBody final OnboardingQuestionRequest onboardingQuestionRequest) {
        memberService.addOnboardingQuestion(memberId, onboardingQuestionRequest);
        return ResponseEntity.ok()
                .build();
    }
}