package org.example.lotto;

import java.util.Comparator;
import java.util.Set;

public class ResultsPrinter {
    private LottoResults results;

    protected void printGenerateNumbers(Set<Integer> set) {
        System.out.print("\nWylosowane liczby: ");
        set.stream()
                .sorted(Comparator.naturalOrder())
                .peek(number -> System.out.print(number + " "))
                .forEach(number -> {});
        System.out.println();
    }

    protected void printUserNumbers(Set<Integer> set) {
        System.out.print("\nLiczby podane przez gracza: ");
        set.stream()
                .sorted(Comparator.naturalOrder())
                .peek(number -> System.out.print(number + " "))
                .forEach(number -> {});
    }

    protected void countMatchingNumbers() {
        System.out.printf("Gracz trafił %s liczb", results.getMatchingNumbers());
    }
}
