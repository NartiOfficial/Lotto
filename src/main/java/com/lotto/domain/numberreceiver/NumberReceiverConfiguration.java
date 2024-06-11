package com.lotto.domain.numberreceiver;

import java.time.Clock;

class NumberReceiverConfiguration {

    NumberReceiverFacade createForTest(HashGenerable hashGenerable, Clock clock, TicketRepository ticketRepository) {
        NumberValidator numberValidator = new NumberValidator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator(clock);
        return new NumberReceiverFacade(numberValidator, drawDateGenerator, hashGenerable, ticketRepository);
    }
}