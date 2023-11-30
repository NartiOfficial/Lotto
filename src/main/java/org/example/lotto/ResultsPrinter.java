package org.example.lotto;

import java.util.Comparator;
import java.util.Set;

public class ResultsPrinter {

    protected void printNumbers(Set<Integer> set) {
        System.out.print("\nWylosowane liczby: ");
        set.stream()
                .sorted(Comparator.naturalOrder())
                .peek(number -> System.out.print(number + " "))
                .forEach(number -> {});
        System.out.println();
    }
}
