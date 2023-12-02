package org.example.lotto;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ResultsPrinterTest {
    @Test
    public void shouldGetCorrectForm() {
        ResultPrinter printer = new ResultPrinter();
        assertEquals("liczba", printer.getCorrectForm(1));
        assertEquals("liczby", printer.getCorrectForm(2));
        assertEquals("liczby", printer.getCorrectForm(3));
        assertEquals("liczby", printer.getCorrectForm(4));
        assertEquals("liczb", printer.getCorrectForm(5));
        assertEquals("liczb", printer.getCorrectForm(0));
    }

    @Test
    public void shouldPrintGeneratedNumbers() {
        ResultPrinter printer = new ResultPrinter();
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        assertDoesNotThrow(() -> printer.printGenerateNumbers(numbers));
    }

    @Test
    public void shouldPrintUserNumbers() {
        ResultPrinter printer = new ResultPrinter();
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        assertDoesNotThrow(() -> printer.printUserNumbers(numbers));
    }

    @Test
    public void shouldCountMatchingNumbers() {
        ResultPrinter printer = new ResultPrinter();
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        assertDoesNotThrow(() -> printer.countMatchingNumbers(numbers, numbers));
    }
}