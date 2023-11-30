package org.example.lotto;

import lombok.Getter;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

import static org.example.lotto.LottoDefaultSettings.*;

@Getter
public class LottoNumbersManager {
    private Set<Integer> generatedNumbers = new HashSet<>();
    private Set<Integer> userNumbers = new HashSet<>();
    void generateNumbers() {
        SecureRandom secureRandom = new SecureRandom();

        while (generatedNumbers.size() < NUMBERS_TO_DRAW_DEFAULT) {
            int randomNumber = secureRandom.nextInt(MIN_VALUE_DEFAULT, (MAX_VALUE_DEFAULT + 1));
            generatedNumbers.add(randomNumber);
        }
    }

    protected void numberDrawProcess() {
        generatedNumbers.stream()
                .peek(number -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println("Trwa losowanie liczby...");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(number -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println("Wylosowana liczba to: " + number);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                });
    }
}
