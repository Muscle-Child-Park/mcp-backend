package com.park.muscle.core.ticket.exception;

import com.park.muscle.global.exception.BusinessException;
import com.park.muscle.global.exception.ErrorCode;

public class TicketNotFoundException extends BusinessException {
    public TicketNotFoundException() {
        super(ErrorCode.TICKET_NOT_FOUND);
    }
}
