//package com.park.muscle.mock;
//
//import com.park.muscle.core.lesson.application.LessonService;
//import com.park.muscle.core.lesson.domain.Lesson;
//import com.park.muscle.core.lesson.dto.request.ReserveRequest;
//import com.park.muscle.core.member.application.MemberService;
//import com.park.muscle.core.trainer.domain.Trainer;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/schedule")
//@Api(tags = "schedule management system")
//public class ScheduleController {
//
//    private final LessonService lessonService;
//    private final MemberService memberService;
//
//    @ApiOperation(value = "멤버가 트레이너의 정보 확인", notes = "멤버가 특정 트레이너의 정보를 확인합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "트레이너 정보 조회 성공", response = Trainer.class),
//            @ApiResponse(code = 404, message = "트레이너를 찾을 수 없습니다.")
//    })
//    @GetMapping("/member/{memberId}/trainer/{trainerId}")
//    public ResponseEntity<Trainer> getTrainerInfoByMember(
//            @ApiParam(value = "멤버 ID", required = true) @PathVariable Long memberId,
//            @ApiParam(value = "트레이너 ID", required = true) @PathVariable Long trainerId) {
//        Trainer trainer = memberService.getTrainerInfo(memberId, trainerId);
//        if (trainer != null) {
//            return ResponseEntity.ok(trainer);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @ApiOperation(value = "트레이너의 스케줄 조회")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success", response = Lesson.class),
//            @ApiResponse(code = 404, message = "Not Found")
//    })
//    @GetMapping("/{trainerId}/pt")
//    public ResponseEntity<List<Lesson>> getTrainerSchedule(
//            @ApiParam(value = "트레이너 ID", required = true) @PathVariable Long trainerId) {
//        List<Lesson> Lesson = null;
//        return ResponseEntity.ok(Lesson);
//    }
//
//    @ApiOperation(value = "트레이너 스케줄에 예약 추가")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "스케줄 예약 성공"),
//            @ApiResponse(code = 400, message = "잘못된 요청"),
//            @ApiResponse(code = 404, message = "트레이너 또는 스케줄을 찾을 수 없음")
//    })
//    @PostMapping("/reserve")
//    public ResponseEntity<String> reserveSchedule(
//            @ApiParam(value = "트레이너 ID", required = true) @PathVariable Long trainerId,
//            @ApiParam(value = "예약 정보", required = true) @RequestBody ReserveRequest reserveRequest) {
//
//        boolean reservationSuccess = lessonService.reserveSchedule(trainerId, reserveRequest);
//        if (reservationSuccess) {
//            return ResponseEntity.status(HttpStatus.CREATED).body("스케줄 예약 성공");
//        } else {
//            return ResponseEntity.badRequest().body("잘못된 요청 또는 스케줄 예약 실패");
//        }
//    }
//
//    @ApiOperation(value = "회원의 PT 예약 정보 조회")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success", response = Lesson.class, responseContainer = "List"),
//            @ApiResponse(code = 404, message = "Not Found")
//    })
//    @GetMapping("/{memberId}/reservations-check")
//    public ResponseEntity<List<Lesson>> getMemberReservations(
//            @ApiParam(value = "회원 ID", required = true) @PathVariable Long memberId) {
//        List<Lesson> reservations = LessonService.getMemberReservations(memberId);
//        if (reservations.isEmpty()) {
//            return ResponseEntity.notFound().build(); // 회원의 예약 정보가 없는 경우 404 Not Found 반환
//        }
//        return ResponseEntity.ok(reservations); // 회원의 예약 정보를 반환
//    }
//}
