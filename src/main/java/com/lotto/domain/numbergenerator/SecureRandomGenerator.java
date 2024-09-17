package com.lotto.domain.numbergenerator;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class SecureRandomGenerator implements RandomNumberGenerable {

    private static final int LOWER_BAND = 1;
    private static final int UPPER_BAND = 99;

    @Override
    public SixRandomNumbersDto generateSixRandomNumbers() {
        Set<Integer> winningNumbers = new HashSet<>();
        while (isAmountOfNumbersLowerThanSix(winningNumbers)) {
            Random random = new SecureRandom();
            int number = random.nextInt((UPPER_BAND - LOWER_BAND) + 1);
            winningNumbers.add(number);
        }
        return SixRandomNumbersDto.builder()
                .numbers(winningNumbers)
                .build();
    }

    private boolean isAmountOfNumbersLowerThanSix(Set<Integer> winningNumbers) {
        return winningNumbers.size() < 6;
    }
}