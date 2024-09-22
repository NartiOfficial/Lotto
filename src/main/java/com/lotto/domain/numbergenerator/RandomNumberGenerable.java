package com.lotto.domain.numbergenerator;

import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RandomNumberGenerable {
    SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBound, int upperBound);
}
