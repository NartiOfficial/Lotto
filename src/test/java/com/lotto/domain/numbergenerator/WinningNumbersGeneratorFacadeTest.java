package com.lotto.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import com.lotto.domain.numberreceiver.NumberReceiverFacade;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WinningNumbersGeneratorFacadeTest {

    private final WinningNumbersRepository winningNumbersRepository = new WinningNumbersRepositoryTestImpl();
    NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);

    @Test
    @DisplayName("It Should Return Set Of Required Size")
    public void it_should_return_set_of_required_size() {
        // Given
        RandomNumberGenerable generator = new RandomGenerator();
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.now());
        WinningNumbersGeneratorFacade numbersGenerator = new NumberGeneratorConfiguration().createForTest(generator, winningNumbersRepository, numberReceiverFacade);

        // When
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();

        // Then
        assertThat(generatedNumbers.getWinningNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("It Should Return Set Of Required Size Within Required Range")
    public void it_should_return_set_of_required_size_within_required_range() {
        // Given
        RandomNumberGenerable generator = new RandomGenerator();
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.now());
        WinningNumbersGeneratorFacade numbersGenerator = new NumberGeneratorConfiguration().createForTest(generator, winningNumbersRepository, numberReceiverFacade);

        // When
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();

        // Then
        int upperBand = 99;
        int lowerBand = 1;
        Set<Integer> winningNumbers = generatedNumbers.getWinningNumbers();
        boolean numbersInRange = winningNumbers.stream().allMatch(number -> number >= lowerBand && number <= upperBand);
        assertThat(numbersInRange).isTrue();

    }

    @Test
    @DisplayName("It Should Throw An Exception When Number Not In Range")
    public void it_should_throw_an_exception_when_number_not_in_range() {
        // Given
        Set<Integer> numbersOutOfRange = Set.of(1, 2, 3, 4, 5, 100);
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl(numbersOutOfRange);
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.now());
        WinningNumbersGeneratorFacade numbersGenerator = new NumberGeneratorConfiguration().createForTest(generator, winningNumbersRepository, numberReceiverFacade);

        // When

        // Then
        assertThrows(IllegalStateException.class, numbersGenerator::generateWinningNumbers, "Number out of range!");
    }

    @Test
    @DisplayName("It Should Return Collection Of Unique Values")
    public void it_should_return_collection_of_unique_values() {
        // Given
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl();
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.now());
        WinningNumbersGeneratorFacade numbersGenerator = new NumberGeneratorConfiguration().createForTest(generator, winningNumbersRepository, numberReceiverFacade);

        // When
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();

        // Then
        int generatedNumbersSize = new HashSet<>(generatedNumbers.getWinningNumbers()).size();
        assertThat(generatedNumbersSize).isEqualTo(6);
    }

    @Test
    @DisplayName("It Should Return Winning Numbers By Given Date")
    public void it_should_return_winning_numbers_by_given_date() {
        // Given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        Set<Integer> generatedWinningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        String id = UUID.randomUUID().toString();
        WinningNumbers winningNumbers = WinningNumbers.builder()
                .id(id)
                .date(drawDate)
                .winningNumbers(generatedWinningNumbers)
                .build();
        winningNumbersRepository.save(winningNumbers);
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl();
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(drawDate);
        WinningNumbersGeneratorFacade numbersGenerator = new NumberGeneratorConfiguration().createForTest(generator, winningNumbersRepository, numberReceiverFacade);

        // When
        WinningNumbersDto winningNumbersDto = numbersGenerator.retrieveWinningNumberByDate(drawDate);

        // Then
        WinningNumbersDto expectedWinningNumbersDto = WinningNumbersDto.builder()
                .date(drawDate)
                .winningNumbers(generatedWinningNumbers)
                .build();
        assertThat(expectedWinningNumbersDto).isEqualTo(winningNumbersDto);
    }

    @Test
    @DisplayName("It Should Throw An Exception When Fail To Retrieve Numbers By Given Date")
    public void it_should_throw_an_exception_when_fail_to_retrieve_numbers_by_given_date() {
        // Given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl();
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(drawDate);
        WinningNumbersGeneratorFacade winningNumbersGeneratorFacade = new NumberGeneratorConfiguration().createForTest(generator, winningNumbersRepository, numberReceiverFacade);

        // When

        // Then
        assertThrows(WinningNumbersNotFoundException.class, () -> winningNumbersGeneratorFacade.retrieveWinningNumberByDate(drawDate), "Not Found");
    }

    @Test
    @DisplayName("It Should Return True If Numbers Are Generated By Given Date")
    public void it_should_return_true_if_numbers_are_generated_by_given_date() {
        // Given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        Set<Integer> generatedWinningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        String id = UUID.randomUUID().toString();
        WinningNumbers winningNumbers = WinningNumbers.builder()
                .id(id)
                .date(drawDate)
                .winningNumbers(generatedWinningNumbers)
                .build();
        winningNumbersRepository.save(winningNumbers);
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl();
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(drawDate);
        WinningNumbersGeneratorFacade numbersGenerator = new NumberGeneratorConfiguration().createForTest(generator, winningNumbersRepository, numberReceiverFacade);

        // When
        boolean areWinningNumbersGeneratedByDate = numbersGenerator.areWinningNumbersGeneratedByDate();

        // Then
        assertTrue(areWinningNumbersGeneratedByDate);

    }
}