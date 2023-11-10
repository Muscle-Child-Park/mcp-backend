package com.park.muscle.mock;

import com.park.muscle.core.lesson.application.LessonService;
import com.park.muscle.core.lesson.dto.request.LessonRequest;
import com.park.muscle.core.member.dto.request.MemberExerciseRequest;
import com.park.muscle.core.trainer.dto.request.FeedbackRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
@Api(tags = "Lesson Management")
public class LessonController {

    private final LessonService lessonService;

    @ApiOperation(value = "수업 등록")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "수업 등록 성송"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/register")
    public ResponseEntity<String> registerLesson(
            @RequestBody LessonRequest lessonRequest) {
        // 수업 등록 로직을 구현
        boolean success = lessonService.registerLesson(lessonRequest);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("수업 등록 성공");
        } else {
            return ResponseEntity.badRequest().body("잘못된 요청 또는 수업 등록 실패");
        }
    }

    @ApiOperation(value = "Add Member Exercise", notes = "멤버 운동을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Member exercise 등록 성공"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/members/{memberId}/exercises")
    public ResponseEntity<String> addMemberExercise(
            @PathVariable Long memberId,
            @RequestBody MemberExerciseRequest exerciseRequest) {
        // 회원 운동 등록 로직을 구현
        boolean success = lessonService.addMemberExercise(memberId, exerciseRequest);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("회원 운동 등록 성공");
        } else {
            return ResponseEntity.badRequest().body("잘못된 요청 또는 회원 운동 등록 실패");
        }
    }

    @ApiOperation(value = "피드백 추가", notes = "수업에 대한 피드백을 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "피드백이 성공적으로 추가되었습니다."),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/feedback/{lessonId}")
    public ResponseEntity<String> addFeedback(
            @ApiParam(value = "수업 ID", required = true) @PathVariable Long lessonId,
            @ApiParam(value = "피드백 데이터", required = true) @RequestBody FeedbackRequest feedbackRequest) {
        boolean success = lessonService.addFeedback(lessonId, feedbackRequest);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Feedback added successfully");
        } else {
            return ResponseEntity.badRequest().body("Bad request or feedback registration failed");
        }
    }
}
