package com.park.muscle.core.reservation.presentation;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
@RestController
@Api(tags = "Reservation Management")
public class ReservationController {
}
