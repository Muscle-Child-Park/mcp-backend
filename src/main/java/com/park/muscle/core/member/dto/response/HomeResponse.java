package com.park.muscle.core.member.dto.response;

import com.park.muscle.core.ticket.dto.TicketDto.LessonByTicketSimpleResponse;
import com.park.muscle.core.trainer.dto.TrainerResponseDto.TrainerResponse;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HomeResponse {
    List<TrainerResponse> trainerReservations;
    List<LessonByTicketSimpleResponse> flattenedList;

    public static HomeResponse fromEntity(List<TrainerResponse> trainerReservations,
                                          List<LessonByTicketSimpleResponse> flattenedList) {
        return HomeResponse.builder()
                .trainerReservations(trainerReservations)
                .flattenedList(flattenedList)
                .build();
    }
}
