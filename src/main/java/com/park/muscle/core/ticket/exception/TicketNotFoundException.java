package com.park.muscle.core.ticket.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TicketNotFoundException extends BusinessException {
    public TicketNotFoundException() {
        super(ErrorCode.TICKET_NOT_FOUND);
    }
}
