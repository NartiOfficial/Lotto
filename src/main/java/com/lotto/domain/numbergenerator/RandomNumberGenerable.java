package com.lotto.domain.numbergenerator;

public interface RandomNumberGenerable {
    SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBound, int upperBound);
}