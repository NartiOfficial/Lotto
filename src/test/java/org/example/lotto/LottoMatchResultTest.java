package org.example.lotto;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class LottoMatchResultTest {

    @Test
    public void shouldSelectIdenticalNumbers() {
        //given
        LottoMatchResult results = new LottoMatchResult();
        Set<Integer> userNumbers = Set.of(1, 2, 3, 4, 5, 6);
        Set<Integer> generatedNumbers = Set.of(4, 5, 6, 7, 8, 9);
        //when
        results.matchingNumbersFromSets(userNumbers, generatedNumbers);
        //then
        assertEquals(3, results.getMatchingNumbers());
    }
}