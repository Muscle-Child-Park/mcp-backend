package com.park.muscle.core.onboarding.presentation;

import com.park.muscle.core.onboarding.application.OnboardingService;
import com.park.muscle.core.onboarding.domain.Onboarding;
import com.park.muscle.core.onboarding.dto.request.OnboardingRequest;
import com.park.muscle.core.onboarding.dto.request.UpdateOnboardingRequest;
import com.park.muscle.core.onboarding.dto.response.UpdateOnboardingResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
@Api(value = "OnboardingController", tags = "Onboarding management system")
public class OnboardingController {
    private final OnboardingService onboardingService;

    @GetMapping("/{memberId}/my-page")
    @ApiOperation(value = "회원 페이지 정보 조회", notes = "특정 회원의 페이지 정보를 조회합니다.")
    public ResponseEntity<OnboardingRequest> getMemberPage(@PathVariable Long memberId) {
        Onboarding onboarding = onboardingService.findOwn(memberId);
        OnboardingRequest onboardingRequest = new OnboardingRequest(onboarding);
        return ResponseEntity.ok(onboardingRequest);
    }

    @PutMapping("/{memberId}/my-page")
    @ApiOperation(value = "회원 페이지 정보 업데이트", notes = "특정 회원의 페이지 정보를 업데이트합니다.")
    public ResponseEntity<UpdateOnboardingResponse> updateOnboarding(@PathVariable Long memberId,
                                                                     @RequestBody UpdateOnboardingRequest request) {
        UpdateOnboardingResponse updateOnboardingResponse = onboardingService.update(memberId, request);
        return ResponseEntity.ok(updateOnboardingResponse);
    }
}
