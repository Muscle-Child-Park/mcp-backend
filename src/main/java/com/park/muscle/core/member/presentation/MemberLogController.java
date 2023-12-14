package com.park.muscle.core.member.presentation;

import com.park.muscle.core.exercise.application.ExerciseLogService;
import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.park.muscle.core.exercise.dto.LogRequestDto.PersonalLogReflectionDto;
import com.park.muscle.core.exercise.dto.LogRequestDto.PersonalLogUpdateDto;
import com.park.muscle.core.exercise.dto.LogResponseDto.LogReflectionResponseDto;
import com.park.muscle.core.personalexercise.application.PersonalExerciseService;
import com.park.muscle.core.personalexercise.domain.PersonalExercise;
import com.park.muscle.global.response.DataResponse;
import com.park.muscle.global.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/member/log")
public class MemberLogController {
    private final ExerciseLogService exerciseLogService;
    private final PersonalExerciseService personalExerciseService;

    @Operation(summary = "개인 운동 회고 추가", description = "개인 운동에 대한 회고를 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회고가 성공적으로 추가되었습니다."),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<DataResponse<LogReflectionResponseDto>> addLogToLesson(
            @Valid @RequestBody PersonalLogReflectionDto personalLogReflectionDto) {

        LogReflectionResponseDto logReflectionResponseDto =
                exerciseLogService.addPersonalExerciseDiary(personalLogReflectionDto);

        return new ResponseEntity<>(DataResponse.of(HttpStatus.CREATED, "개인 운동 회고 추가 성공", logReflectionResponseDto),
                HttpStatus.CREATED);
    }

    @Operation(summary = "개인 운동 회고 업데이트", description = "개인 운동에 대한 회고 업데이트")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회고가 성공적으로 업데이트 되었습니다."),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping
    public ResponseEntity<MessageResponse> updateLogToLesson(@Valid @RequestBody PersonalLogUpdateDto personalLogUpdateDto) {

        ExerciseDiary exerciseDiary = exerciseLogService.updatePersonalExerciseLog(personalLogUpdateDto);
        PersonalExercise personalExercise = personalExerciseService.findPersonalExerciseById(
                personalLogUpdateDto.getPersonalId());
        personalExercise.updateExerciseDiary(exerciseDiary);

        return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "개인 운동 회고 업데이트 성공"), HttpStatus.OK);
    }
}
