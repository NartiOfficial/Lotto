package org.example.lotto;

import lombok.Getter;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

import static org.example.lotto.LottoDefaultSettings.*;

@Getter
public class LottoNumberGenerator {
    private Set<Integer> generatedNumbers = new HashSet<>();
    private Set<Integer> userNumbers = new HashSet<>();
    protected void generateNumbers() {
        SecureRandom secureRandom = new SecureRandom();
        while (generatedNumbers.size() < NUMBERS_TO_DRAW_DEFAULT) {
            int randomNumber = secureRandom.nextInt(MIN_VALUE_DEFAULT, (MAX_VALUE_DEFAULT + 1));
            generatedNumbers.add(randomNumber);
        }
    }
}