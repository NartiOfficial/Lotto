package org.example.lotto;

import org.junit.jupiter.api.Test;

import static org.example.lotto.LottoDefaultSettings.*;
import static org.junit.jupiter.api.Assertions.*;

public class LottoNumberGeneratorTest {

    @Test
    public void shouldGenerateNumbers() {
        //given
        LottoNumberGenerator manager = new LottoNumberGenerator();
        //when
        manager.generateNumbers();
        //then
        assertEquals(NUMBERS_TO_DRAW_DEFAULT, manager.getGeneratedNumbers().size());
        manager.getGeneratedNumbers().forEach(number -> {
            assertTrue(number >= MIN_VALUE_DEFAULT);
            assertTrue(number <= MAX_VALUE_DEFAULT);
        });
    }
}