package com.park.muscle.mock;


import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.trainer.application.TrainerService;
import com.park.muscle.core.trainer.domain.Trainer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trainers/auth")
@Api(tags = "trainer management system")
public class TrainerAuthController {

    @Autowired
    TrainerService trainerService;

    @ApiOperation(value = "트레이너 등록", notes = "트레이너의 가입을 완료하고 고유 아이디를 발급합니다.", response = Trainer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "트레이너 가입 및 아이디 발급 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PostMapping("/register")
    public ResponseEntity<String> registerTrainer(
            @ApiParam(value = "트레이너 정보", required = true) @RequestBody Trainer trainer) {
        String trainerUniqueId = trainerService.completeSignUp(trainer);
        if (trainerUniqueId != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("트레이너 가입 및 아이디 발급 성공. 고유 아이디: " + trainerUniqueId);
        } else {
            return ResponseEntity.badRequest().body("잘못된 요청 또는 트레이너 가입 및 아이디 발급 실패");
        }
    }

    @ApiOperation(value = "소셜 로그인", response = Member.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "트레이너 로그인 성송"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Trainer not found")
    })
    @PostMapping("/login")
    public ResponseEntity<Member> loginTrainer(
            @ApiParam(value = "Trainer social login provider (e.g. KAKAO, APPLE)", required = true)
            @RequestParam String socialProvider,
            @ApiParam(value = "Trainer social login ID (e.g. KAKAO UID, APPLE UID)", required = true)
            @RequestParam String socialLoginId) {

        // 사용자를 찾지 못한 경우 404 Not Found 또는 401 Unauthorized 반환
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiOperation(value = "트레이너 프로필 업데이트", response = Trainer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "트레이너 프로필 업데이트 성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "트레이너를 찾을 수 없음")
    })
    @PutMapping("/profile/{trainerId}")
    public ResponseEntity<Trainer> updateTrainerProfile(
            @ApiParam(value = "트레이너 ID", required = true) @PathVariable Long trainerId,
            @ApiParam(value = "업데이트된 트레이너 프로필 정보", required = true) @RequestBody Trainer updatedTrainer) {

        return ResponseEntity.ok(updatedTrainer);
    }

    @ApiOperation(value = "트레이너 계정 삭제")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "트레이너 계정 삭제 성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "트레이너를 찾을 수 없음")
    })
    @DeleteMapping("/delete/{trainerId}")
    public ResponseEntity<Void> deleteTrainerAccount(
            @ApiParam(value = "트레이너 ID", required = true) @PathVariable Long trainerId) {

        // 삭제가 성공한 경우 204 No Content 반환
        return ResponseEntity.noContent().build();
    }
}
