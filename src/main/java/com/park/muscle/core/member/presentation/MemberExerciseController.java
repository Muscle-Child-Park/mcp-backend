package com.park.muscle.core.member.presentation;

import static com.park.muscle.core.personalexercise.dto.response.PersonalExerciseResponse.AllPersonalExerciseResponse;
import static com.park.muscle.core.personalexercise.dto.response.PersonalExerciseResponse.PersonalExerciseCreateResponse;

import com.park.muscle.core.exercise.application.ExerciseService;
import com.park.muscle.core.exercise.domain.Exercise;
import com.park.muscle.core.exercise.dto.ExerciseRequestDto.CreateExerciseWithPersonal;
import com.park.muscle.core.member.application.MemberService;
import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.personalexercise.application.PersonalExerciseService;
import com.park.muscle.core.personalexercise.domain.PersonalExercise;
import com.park.muscle.core.personalexercise.dto.request.PersonalExerciseWithMemberRequest;
import com.park.muscle.core.personalexercise.dto.response.PersonalExerciseResponse.OwnPersonalExerciseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/{memberId}")
    public ResponseEntity<AllPersonalExerciseResponse> getMemberAllExercises(@PathVariable long memberId) {
        Member member = memberService.findMemberById(memberId);
        List<PersonalExercise> personalExercises = member.getPersonalExercises();
        return ResponseEntity.status(HttpStatus.OK).body(AllPersonalExerciseResponse.fromEntity(personalExercises));
    }

    @GetMapping("/own/{exerciseId}")
    public ResponseEntity<OwnPersonalExerciseResponse> getMemberOwnExercises(@PathVariable long exerciseId) {
        Exercise exercise = exerciseService.findById(exerciseId);
        return ResponseEntity.status(HttpStatus.OK).body(OwnPersonalExerciseResponse.fromEntity(exercise));
    }

    @PostMapping("/{memberId}")
    public ResponseEntity<PersonalExerciseCreateResponse> addMemberExercises(@PathVariable Long memberId,
                                                                             @RequestBody PersonalExerciseWithMemberRequest personalExerciseWithMemberRequest) {

        PersonalExercise personalExercise = personalExerciseWithMemberRequest.getPersonalExerciseRequest().toEntity();
        List<CreateExerciseWithPersonal> exerciseRequestDtos = personalExerciseWithMemberRequest.getExerciseRequestDtos();
        List<Exercise> exercises = exerciseService.saveExerciseWithMember(exerciseRequestDtos);
        personalExercise.updateExercise(exercises);
        personalExerciseService.save(personalExercise);

        Member memberById = memberService.findMemberById(memberId);
        memberById.updatePersonalExercises(personalExercise);
        memberService.save(memberById);
        return ResponseEntity.status(HttpStatus.OK).body(PersonalExerciseCreateResponse.fromEntity(personalExercise));
    }
}
