package com.lotto.domain.numberreceiver;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;


import com.lotto.domain.AdjustableClock;
import com.lotto.domain.numberreceiver.dto.NumberReceiverResponseDto;
import com.lotto.domain.numberreceiver.dto.TicketDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    private final TicketRepository ticketRepository = new TicketRepositoryTestImpl();
    Clock clock = Clock.systemUTC();

    @Test
    @DisplayName("It Should Return Correct Response When User Input Six Numbers In Range")
    public void it_should_return_correct_response_when_user_input_six_numbers_in_range() {
        // Given
        HashGenerable hashGenerator = new HashGeneratorTestImpl();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator(clock);
        LocalDateTime nextDrawDate = drawDateGenerator.getNextDrawDate();

        TicketDto generatedTicket = TicketDto.builder().hash(hashGenerator.getHash()).numbers(numbersFromUser).drawDate(nextDrawDate).build();

        // When
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // Then
        NumberReceiverResponseDto expectedResponse = new NumberReceiverResponseDto(generatedTicket, ValidationResult.INPUT_SUCCESS.info);
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("It Should Return Failed Message When User Input Six Numbers But One Number Is Out Of Range")
    public void it_should_return_failed_message_when_user_input_six_numbers_but_one_number_is_out_of_range() {
        // Given
        HashGenerator hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 100);

        // When
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // Then
        NumberReceiverResponseDto expectedResponse = new NumberReceiverResponseDto(null, ValidationResult.NOT_IN_RANGE.info);
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("It Should Return Failed Message When User Input Six Numbers But One Number Is Out Of Range And Is Negative")
    public void it_should_return_failed_message_when_user_input_six_numbers_but_one_number_is_out_of_range_and_is_negative() {
        // Given
        HashGenerator hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, -4, 5, 6);

        // When
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // Then
        NumberReceiverResponseDto expectedResponse = new NumberReceiverResponseDto(null, ValidationResult.NOT_IN_RANGE.info);
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("It Should Return Failed Message When User Input Less Than Six Numbers")
    public void it_should_return_failed_message_when_user_input_less_than_six_numbers() {
        // Given
        HashGenerator hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5);

        // When
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // Then
        NumberReceiverResponseDto expectedResponse = new NumberReceiverResponseDto(null, ValidationResult.NOT_SIX_NUMBERS_GIVEN.info);
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("It Should Return Failed Message When User Input More Than Six Numbers")
    public void it_should_return_failed_message_when_user_input_more_than_six_numbers() {
        // Given
        HashGenerator hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);

        // When
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // Then
        NumberReceiverResponseDto expectedResponse = new NumberReceiverResponseDto(null, ValidationResult.NOT_SIX_NUMBERS_GIVEN.info);
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("It Should Return Correct Hash")
    public void it_should_return_correct_hash() {
        // Given
        HashGenerable hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        // When
        String response = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().hash();

        // Then
        assertThat(response).hasSize(36);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("It Should Return Correct Draw Date")
    public void it_should_return_correct_draw_date() {
        // Given
        Clock clock = Clock.fixed(LocalDateTime.of(2022, 11, 19, 10, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
        HashGenerable hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        // When
        LocalDateTime testedDrawDate = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().drawDate();

        // Then
        LocalDateTime expectedDrawDate = LocalDateTime.of(2022, 11, 19, 12, 0, 0);
        assertThat(testedDrawDate).isEqualTo(expectedDrawDate);
    }

    @Test
    @DisplayName("It Should Return Next Saturday Draw Date When Date Is Saturday Noon")
    public void it_should_return_next_Saturday_draw_date_when_date_is_Saturday_noon() {
        // Given
        Clock clock = Clock.fixed(LocalDateTime.of(2022, 11, 19, 12, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
        HashGenerable hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        // When
        LocalDateTime testedDrawDate = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().drawDate();

        // Then
        LocalDateTime expectedDrawDate = LocalDateTime.of(2022, 11, 26, 12, 0, 0);
        assertThat(testedDrawDate).isEqualTo(expectedDrawDate);
    }

    @Test
    @DisplayName("It Should Return Next Saturday Draw Date When Date Is Saturday Afternoon")
    public void it_should_return_next_Saturday_draw_date_when_date_is_Saturday_afternoon() {
        // Given
        Clock clock = Clock.fixed(LocalDateTime.of(2022, 11, 19, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
        HashGenerable hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        // When
        LocalDateTime testedDrawDate = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().drawDate();

        // Then
        LocalDateTime expectedDrawDate = LocalDateTime.of(2022, 11, 26, 12, 0, 0);
        assertThat(testedDrawDate).isEqualTo(expectedDrawDate);
    }

    @Test
    @DisplayName("It Should Return Tickets With Correct Draw Date")
    public void it_should_return_tickets_with_correct_draw_date() {
        // Given
        HashGenerable hashGenerator = new HashGenerator();

        Instant fixedInstant = LocalDateTime.of(2022, 12, 15, 12, 0, 0).toInstant(ZoneOffset.UTC);
        ZoneId of = ZoneId.of("Europe/London");
        AdjustableClock clock = new AdjustableClock(fixedInstant, of);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        NumberReceiverResponseDto numberReceiverResponseDto = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        clock.plusDays(1);
        NumberReceiverResponseDto numberReceiverResponseDto1 = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        clock.plusDays(1);
        NumberReceiverResponseDto numberReceiverResponseDto2 = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        clock.plusDays(1);
        NumberReceiverResponseDto numberReceiverResponseDto3 = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        TicketDto ticketDto = numberReceiverResponseDto.ticketDto();
        TicketDto ticketDto1 = numberReceiverResponseDto1.ticketDto();
        LocalDateTime drawDate = numberReceiverResponseDto.ticketDto().drawDate();

        // When
        List<TicketDto> allTicketsByDate = numberReceiverFacade.retrieveAllTicketsByNextDrawDate(drawDate);

        // Then
        assertThat(allTicketsByDate).containsOnly(ticketDto, ticketDto1);
    }

    @Test
    @DisplayName("It Should Return Empty Collections If There Are No Tickets")
    public void it_should_return_empty_collections_if_there_are_no_tickets() {
        // Given
        HashGenerable hashGenerator = new HashGenerator();
        Clock clock = Clock.fixed(LocalDateTime.of(2022, 12, 15, 12, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        LocalDateTime drawDate = LocalDateTime.now(clock);

        // When
        List<TicketDto> allTicketsByDate = numberReceiverFacade.retrieveAllTicketsByNextDrawDate(drawDate);

        // Then
        assertThat(allTicketsByDate).isEmpty();
    }

    @Test
    @DisplayName("It Should Return Empty Collections If Given Date Is After Next DrawDate")
    public void it_should_return_empty_collections_if_given_date_is_after_next_drawDate() {
        // Given
        HashGenerable hashGenerator = new HashGenerator();

        Clock clock = Clock.fixed(LocalDateTime.of(2022, 12, 15, 12, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(hashGenerator, clock, ticketRepository);
        NumberReceiverResponseDto numberReceiverResponseDto = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));

        LocalDateTime drawDate = numberReceiverResponseDto.ticketDto().drawDate();

        // When
        List<TicketDto> allTicketsByDate = numberReceiverFacade.retrieveAllTicketsByNextDrawDate(drawDate.plusWeeks(1L));

        // Then
        assertThat(allTicketsByDate).isEmpty();
    }
}