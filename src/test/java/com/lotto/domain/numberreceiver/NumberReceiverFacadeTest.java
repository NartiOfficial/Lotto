package com.lotto.domain.numberreceiver;

import com.lotto.domain.numberreceiver.dto.InputNumberResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.InstantSource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {

    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            new NumberValidator(),
            new InMemoryNumberReceiverRepositoryTestImpl(),
            (Clock) InstantSource.fixed(LocalDateTime.of(2025, 1, 1, 1, 0, 0).toInstant(ZoneOffset.UTC))
    );

    @Test
    @DisplayName("Should return success when user gave six numbers")
    public void should_return_success_when_user_gave_six_numbers() {
        //given
        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        //when
        final InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("success");
    }

    @Test
    @DisplayName("Should return failed when user gave at least one number out of range of 1 to 99")
    public void should_return_failed_when_user_gave_at_least_one_number_out_of_range_of_1_to_99() {
        //given
        final Set<Integer> numbersFromUser = Set.of(1, 200, 3, 4, 5, 6);
        //when
        final InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    @DisplayName("Should return failed when user gave less than six numbers")
    public void should_return_failed_when_user_gave_less_than_six_numbers() {
        //given
        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5);
        //when
        final InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    @DisplayName("Should return failed when user gave more than six numbers")
    public void should_return_failed_when_user_gave_more_than_six_numbers() {
        //given
        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);
        //when
        final InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    @DisplayName("Should return save to database when user gave six numbers")
    public void should_return_save_to_database_when_user_gave_six_numbers() {
        //given
        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        final InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        final LocalDateTime drawDate = LocalDateTime.of(2025, 1, 1, 1, 0, 0);
        //when
        final List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(drawDate);
        //then
        assertThat(ticketDtos).contains(TicketDto.builder().ticketId(result.ticketId()).drawDate(drawDate).numbersFromUser(result.numbersFromUser()).build());
    }
}