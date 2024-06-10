package com.lotto.domain.numberreceiver;

class TicketMapper {

    public static TicketDto mapFromTicket(Ticket ticket) {
        return TicketDto.builder()
                .ticketId(ticket.ticketId())
                .drawDate(ticket.drawDate())
                .numbersFromUser(ticket.numbersFromUser())
                .build();
    }
}
