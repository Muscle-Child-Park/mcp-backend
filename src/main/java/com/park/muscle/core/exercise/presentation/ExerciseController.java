package com.park.muscle.core.exercise.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/exercises/{memberId}")
@RequiredArgsConstructor
@RestController
public class ExerciseController {
}
