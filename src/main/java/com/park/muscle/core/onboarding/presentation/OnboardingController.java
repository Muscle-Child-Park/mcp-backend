package com.park.muscle.core.onboarding.presentation;

import com.park.muscle.core.onboarding.application.OnboardingService;
import com.park.muscle.core.onboarding.domain.Onboarding;
import com.park.muscle.core.onboarding.dto.OnboardingRequest.UpdateRequest;
import com.park.muscle.core.onboarding.dto.OnboardingResponse.FindResponse;
import com.park.muscle.core.onboarding.dto.OnboardingResponse.UpdateOnboarding;
import com.park.muscle.global.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@Tag(name = "On-boarding Management", description = "APIs related to managing On-boarding")
public class OnboardingController {
    private final OnboardingService onboardingService;

    @Operation(summary = "회원 페이지 정보 조회", description = "특정 회원의 페이지 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 정보가 조회 되었습니다."),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/my-page/{memberId}")
    public ResponseEntity<DataResponse<FindResponse>> getMemberPage(@PathVariable Long memberId) {
        Onboarding onboarding = onboardingService.findOwnOnboard(memberId);
        FindResponse onboardingResponse = FindResponse.fromEntity(onboarding);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "특정 회원 페이지 조회 성공", onboardingResponse),
                HttpStatus.OK);
    }

    @Operation(summary = "회원 페이지 정보 업데이트", description = "특정 회원의 페이지 정보를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 정보가 성공적으로 업데이트 되었습니다."),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/my-page/{memberId}")
    public ResponseEntity<DataResponse<UpdateOnboarding>> updateOnboarding(@PathVariable Long memberId,
                                                             @Valid @RequestBody UpdateRequest request) {
        UpdateOnboarding updateOnboarding = onboardingService.updateOnboard(memberId, request);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "회원 페이지 업데이트 성공", updateOnboarding), HttpStatus.OK);
    }
}
