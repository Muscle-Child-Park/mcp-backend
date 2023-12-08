package com.park.muscle.core.member.presentation;

import static com.park.muscle.core.personalexercise.dto.request.PersonalExerciseWithMemberRequest.CreatePersonalExercise;
import static com.park.muscle.core.personalexercise.dto.request.PersonalExerciseWithMemberRequest.UpdatePersonalExercise;
import static com.park.muscle.core.personalexercise.dto.response.PersonalExerciseResponse.AllPersonalExerciseResponse;
import static com.park.muscle.core.personalexercise.dto.response.PersonalExerciseResponse.PersonalExerciseCreateResponse;

import com.park.muscle.core.exercise.application.ExerciseService;
import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithPersonal;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.UpdateExerciseWithPersonal;
import com.park.muscle.core.exercise.dto.LogResponseDto.LogReflectionResponseDto;
import com.park.muscle.core.member.application.MemberService;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.personalexercise.application.PersonalExerciseService;
import com.park.muscle.core.personalexercise.domain.PersonalExercise;
import com.park.muscle.core.personalexercise.dto.request.PersonalExerciseRequest.Update;
import com.park.muscle.core.personalexercise.dto.response.PersonalExerciseResponse.OwnPersonalExerciseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/member/exercise")
@Tag(name = "Member-exercise Management")
public class MemberExerciseController {
    private final PersonalExerciseService personalExerciseService;
    private final ExerciseService exerciseService;
    private final MemberService memberService;

    @Operation(summary = "회원 개인 운동 목록 조회", description = "멤버 아이디를 통해 해당 멤버의 전체 개인 운동 목록을 조회할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "개인 운동 조회 성공"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/{memberId}")
    public ResponseEntity<List<AllPersonalExerciseResponse>> getMemberAllExercises(@PathVariable Long memberId) {
        Member member = memberService.findMemberById(memberId);
        List<PersonalExercise> personalExercises = member.getPersonalExercises();
        return ResponseEntity.status(HttpStatus.OK).body(personalExerciseService.getAllPersonalEx(personalExercises));
    }

    @Operation(summary = "회원 개인 운동 조회", description = "개인 운동 아이디를 통해 해당 멤버의 개인 운동을 조회할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "개인 운동 조회 성공"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/own/{personalExerciseId}")
    public ResponseEntity<OwnPersonalExerciseResponse> getExercisesFromPersonalEx(
            @PathVariable long personalExerciseId) {
        PersonalExercise personalExercise = personalExerciseService.findPersonalExerciseById(personalExerciseId);
        List<Exercise> exercises = personalExercise.getExercises();
        ExerciseDiary exerciseDiary = personalExercise.getExerciseDiary();

        LogReflectionResponseDto logReflectionResponseDto = null;
        if (exerciseDiary != null) {
            logReflectionResponseDto = LogReflectionResponseDto.fromEntity(exerciseDiary);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(OwnPersonalExerciseResponse.fromEntity(personalExercise, exercises, logReflectionResponseDto));
    }

    @Operation(summary = "회원 개인 운동 생성", description = "새로운 개인 운동을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "개인 운동 조회 성공"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping("/{memberId}")
    public ResponseEntity<PersonalExerciseCreateResponse> addPersonalExercises(@PathVariable Long memberId,
                                                                               @Valid @RequestBody CreatePersonalExercise createPersonalExercise) {

        PersonalExercise personalExercise = createPersonalExercise.getPersonalExerciseRequest().toEntity();
        List<CreateExerciseWithPersonal> exerciseRequestDtos = createPersonalExercise.getExerciseRequestDtos();
        List<Exercise> exercises = exerciseService.saveExerciseWithMember(exerciseRequestDtos);
        personalExercise.addExercise(exercises);
        personalExerciseService.save(personalExercise);

        Member memberById = memberService.findMemberById(memberId);
        memberById.addPersonalExercises(personalExercise);
        memberService.save(memberById);
        return ResponseEntity.status(HttpStatus.OK).body(PersonalExerciseCreateResponse.fromEntity(personalExercise));
    }

    @Operation(summary = "회원 개인 운동 수정", description = "개인 운동을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "운동 수정 성공"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PutMapping("/{memberId}")
    public ResponseEntity<PersonalExerciseCreateResponse> updateMemberExercises(@PathVariable Long memberId,
                                                                                @Valid @RequestBody UpdatePersonalExercise updatePersonalExercise) {
        List<UpdateExerciseWithPersonal> exerciseUpdate = updatePersonalExercise.getExerciseUpdateRequestDtos();
        Update personalUpdate = updatePersonalExercise.getPersonalExerciseUpdateRequest();

        List<Exercise> exercises = exerciseService.updateExercises(exerciseUpdate);
        PersonalExercise personalEx = personalExerciseService.findPersonalExerciseById(
                personalUpdate.getPersonalExerciseId());

        personalEx.updatePersonalExercise(personalUpdate);
        Member member = memberService.findMemberById(memberId);
        personalExerciseService.updateExercises(exercises, personalEx);

        personalExerciseService.save(personalEx);
        memberService.save(member);
        return ResponseEntity.status(HttpStatus.OK).body(PersonalExerciseCreateResponse.fromEntity(personalEx));
    }
}
