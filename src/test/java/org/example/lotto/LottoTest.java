package org.example.lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LottoTest {
    //given
    LottoNumberGenerator lottoNumberGenerator = mock(LottoNumberGenerator.class);
    UserInputManager ticketSubmitter = mock(UserInputManager.class);
    ResultPrinter resultDisplayer = mock(ResultPrinter.class);
    Lotto testLotto = new Lotto(lottoNumberGenerator, ticketSubmitter, resultDisplayer);

    @Test
    public void shouldInteractWithDependenciesWhenPlayIsCalled() {
        //given
        doNothing().when(ticketSubmitter).submitTicket(any());
        //when
        testLotto.play();
        //then
        verify(ticketSubmitter, times(1)).submitTicket(any());
        verify(lottoNumberGenerator, times(1)).generateNumbers();
        verify(resultDisplayer, times(1)).numberDrawProcess(any());
        verify(resultDisplayer, times(1)).printUserNumbers(any());
        verify(resultDisplayer, times(1)).printGenerateNumbers(any());
        verify(resultDisplayer, times(1)).countMatchingNumbers(any(), any());
    }

    @Test
    public void shouldThrowExceptionWhenErrorOccurs() {
        //given
        doThrow(new RuntimeException("Test error")).when(ticketSubmitter).submitTicket(any());
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