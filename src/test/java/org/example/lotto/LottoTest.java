package org.example.lotto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LottoTest {
    LottoNumbersManager numbersManager = mock(LottoNumbersManager.class);
    UserInputManager userInputManager = mock(UserInputManager.class);
    ResultsPrinter resultsPrinter = mock(ResultsPrinter.class);

    Lotto testLotto = new Lotto(numbersManager, userInputManager, resultsPrinter);

    @Test
    public void testInteractionWithDependencies() {
        doNothing().when(userInputManager).submitTicket(any());

        testLotto.play();

        verify(userInputManager, times(1)).submitTicket(any());
        verify(numbersManager, times(1)).generateNumbers();
        verify(numbersManager, times(1)).numberDrawProcess();
        verify(resultsPrinter, times(1)).printUserNumbers(any());
        verify(resultsPrinter, times(1)).printGenerateNumbers(any());
        verify(resultsPrinter, times(1)).countMatchingNumbers(any(), any());
    }

    @Test
    public void testErrorHandling() {
        doThrow(new RuntimeException("Test error")).when(userInputManager).submitTicket(any());

        try {
            testLotto.play();
        } catch (RuntimeException e) {
            assertEquals("Test error", e.getMessage());
        }
    }
}
