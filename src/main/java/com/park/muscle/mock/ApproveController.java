package com.park.muscle.mock;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.trainer.application.TrainerService;
import com.park.muscle.core.trainer.dto.request.ClassRegistrationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/approve")
@Api(tags = "schedule management system")
public class ApproveController {

    private final TrainerService trainerService;

    @ApiOperation(value = "트레이너의 회원 등록 요청 승인", notes = "트레이너가 회원의 수강권 등록 요청을 승인하고 수업을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "회원 등록 요청 승인 및 수업 등록 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 404, message = "회원을 찾을 수 없습니다.")
    })
    @PostMapping("/{trainerId}/approve/{memberId}")
    public ResponseEntity<String> approveMemberRegistration(
            @ApiParam(value = "트레이너 ID", required = true) @PathVariable Long trainerId,
            @ApiParam(value = "회원 ID", required = true) @PathVariable Long memberId,
            @ApiParam(value = "수업 등록 정보", required = true) @RequestBody ClassRegistrationRequest classRegistrationRequest) {
        // 회원 등록 요청 승인 및 수업 등록 로직을 구현
        boolean success = trainerService.approveMemberRegistration(trainerId, memberId, classRegistrationRequest);
        if (success) {
            return ResponseEntity.ok("회원 등록 요청 승인 및 수업 등록 성공");
        } else {
            return ResponseEntity.badRequest().body("잘못된 요청 또는 회원 등록 요청 승인 및 수업 등록 실패");
        }
    }

    @ApiOperation(value = "트레이너의 회원 정보 확인", notes = "트레이너가 회원의 정보를 확인합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "회원 정보 조회 성공", response = Member.class),
            @ApiResponse(code = 404, message = "회원을 찾을 수 없습니다.")
    })
    @GetMapping("/{trainerId}/{memberId}")
    public ResponseEntity<Member> getMemberInfoByTrainer(
            @ApiParam(value = "트레이너 ID", required = true) @PathVariable Long trainerId,
            @ApiParam(value = "회원 ID", required = true) @PathVariable Long memberId) {
        Member member = trainerService.getMemberInfo(trainerId, memberId);
        if (member != null) {
            return ResponseEntity.ok(member);
        }
        return ResponseEntity.notFound().build();
    }
}
