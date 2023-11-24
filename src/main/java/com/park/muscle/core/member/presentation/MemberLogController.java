package com.park.muscle.core.member.presentation;

import com.park.muscle.core.exercise.application.ExerciseLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/member/log")
public class MemberLogController {

    private final ExerciseLogService exerciseLogService;

//    @ApiOperation(value = "운동일지 회고글 추가", notes = "운동일지에 회고글을 추가합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "운동일지 회고글 추가 성공"),
//            @ApiResponse(code = 400, message = "잘못된 요청")
//    })
//    @PostMapping("/{memberId}/{logId}/reflection")
//    public ResponseEntity<String> addExerciseLogReflection(
//            @ApiParam(value = "회원 ID", required = true) @PathVariable Long memberId,
//            @ApiParam(value = "운동일지 ID", required = true) @PathVariable Long logId,
//            @ApiParam(value = "운동일지 데이터", required = true) @RequestBody LogReflectionDto request) {
//
//        boolean success = exerciseLogService.addExerciseLogReflection(memberId, logId, request);
//        if (success) {
//            return ResponseEntity.status(HttpStatus.CREATED).body("운동일지 회고글 추가 성공");
//        } else {
//            return ResponseEntity.badRequest().body("잘못된 요청 또는 운동일지 추가 실패");
//        }
//    }

//    @ApiOperation(value = "운동일지 추가", notes = "개인 운동 또는 PT 운동의 일지를 추가합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "운동일지 추가 성공"),
//            @ApiResponse(code = 400, message = "잘못된 요청")
//    })
//    @PostMapping("/{memberId}")
//    public ResponseEntity<String> addExerciseLog(
//            @ApiParam(value = "회원 ID", required = true) @PathVariable Long memberId,
//            @ApiParam(value = "운동일지 데이터", required = true) @RequestBody LogReflectionDto logRequest) {
//        // 운동일지 추가 로직을 구현
//        boolean success = exerciseLogService.addExerciseLog(memberId, logRequest);
//        if (success) {
//            return ResponseEntity.status(HttpStatus.CREATED).body("운동일지 추가 성공");
//        } else {
//            return ResponseEntity.badRequest().body("잘못된 요청 또는 운동일지 추가 실패");
//        }
//    }

//    @ApiOperation(value = "운동일지 수정", notes = "운동일지를 수정합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "운동일지 수정 성공"),
//            @ApiResponse(code = 400, message = "잘못된 요청"),
//            @ApiResponse(code = 404, message = "운동일지를 찾을 수 없음")
//    })
//    @PutMapping("/{memberId}/{logId}")
//    public ResponseEntity<String> updateExerciseLog(
//            @ApiParam(value = "회원 ID", required = true) @PathVariable Long memberId,
//            @ApiParam(value = "운동일지 ID", required = true) @PathVariable Long logId,
//            @ApiParam(value = "운동일지 데이터", required = true) @RequestBody LogReflectionDto logRequest) {
//        boolean success = exerciseLogService.updateExerciseLog(memberId, logId, logRequest);
//        if (success) {
//            return ResponseEntity.ok("운동일지 수정 성공");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}


