package org.example.lotto;

import java.util.Comparator;
import java.util.Set;

public class ResultsPrinter {
    private LottoResults results = new LottoResults();

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

    protected void countMatchingNumbers(Set<Integer> generatedNumbers, Set<Integer> userNumbers) {
        results.matchingNumbersFromSets(generatedNumbers, userNumbers);

        System.out.printf("\nGracz trafił %d %s, ", results.getMatchingNumbers(), getCorrectForm(results.getMatchingNumbers()));
        switch (results.getMatchingNumbers()) {
            case 1 -> System.out.printf("%s wygrywająca: %s", getCorrectForm(1), results.getTemp());
            case 2, 3, 4, 5 -> System.out.printf("%s wygrywające: %s", getCorrectForm(results.getMatchingNumbers()), results.getTemp());
            case 6 -> System.out.printf("wszystkie %s wygrywają! Liczby wygrywające: %s", getCorrectForm(results.getMatchingNumbers()), results.getTemp());
            default -> System.out.printf("brak wygrywających %s", getCorrectForm(0));
        }
    }

    private String getCorrectForm(int number) {
        if (number == 1) {
            return "liczba";
        } else if (number >= 2 && number <= 4) {
            return "liczby";
        } else {
            return "liczb";
        }
    }
}
