package com.lotto.infrastructure.resultannouncer.controller.error;

import org.springframework.http.HttpStatus;

record ResultAnnouncerErrorResponse(
        String message,
        HttpStatus httpStatus
) {
}
