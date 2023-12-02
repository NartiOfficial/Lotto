package org.example.lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LottoTest {
    //given
    LottoNumberGenerator numbersManager = mock(LottoNumberGenerator.class);
    UserInputManager userInputManager = mock(UserInputManager.class);
    ResultPrinter resultPrinter = mock(ResultPrinter.class);
    Lotto testLotto = new Lotto(numbersManager, userInputManager, resultPrinter);

    @Test
    public void shouldInteractWithDependenciesWhenPlayIsCalled() {
        //given
        doNothing().when(userInputManager).submitTicket(any());
        //when
        testLotto.play();
        //then
        verify(userInputManager, times(1)).submitTicket(any());
        verify(numbersManager, times(1)).generateNumbers();
        verify(resultPrinter, times(1)).numberDrawProcess(any());
        verify(resultPrinter, times(1)).printUserNumbers(any());
        verify(resultPrinter, times(1)).printGenerateNumbers(any());
        verify(resultPrinter, times(1)).countMatchingNumbers(any(), any());
    }

    @Test
    public void shouldThrowExceptionWhenErrorOccurs() {
        //given
        doThrow(new RuntimeException("Test error")).when(userInputManager).submitTicket(any());
        //when
        try {
            testLotto.play();
        }
        //then
        catch (RuntimeException e) {
            assertEquals("Test error", e.getMessage());
        }
    }
}