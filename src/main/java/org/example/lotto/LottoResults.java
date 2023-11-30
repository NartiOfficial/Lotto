package org.example.lotto;

import lombok.Getter;

import java.util.Set;

@Getter
public class LottoResults {
    private int matchingNumbers;
    protected void matchingNumbersFromSets(Set<Integer> userNumbers, Set<Integer> generatedNumbers) {
        int temp;
        temp = (int) generatedNumbers.stream()
                .filter(userNumbers::contains)
                .count();
        System.out.println(temp);
        this.matchingNumbers = temp;
    }
}
