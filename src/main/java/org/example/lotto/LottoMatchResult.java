package org.example.lotto;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class LottoMatchResult {
    private int matchingNumbers;
    private Set<Integer> temp = new HashSet<>();

    protected void matchingNumbersFromSets(Set<Integer> userNumbers, Set<Integer> generatedNumbers) {
        temp = new HashSet<>(generatedNumbers);
        temp.retainAll(userNumbers);
        setMatchingNumbers(temp.size());
    }

    private void setMatchingNumbers(int matchingNumbers) {
        this.matchingNumbers = matchingNumbers;
    }
}