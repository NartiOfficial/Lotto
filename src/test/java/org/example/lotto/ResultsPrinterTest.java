package org.example.lotto;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ResultsPrinterTest {
    @Test
    public void shouldGetCorrectForm() {
        //given
        ResultPrinter printer = new ResultPrinter();
        //when & then
        assertEquals("liczba", printer.getCorrectForm(1));
        assertEquals("liczby", printer.getCorrectForm(2));
        assertEquals("liczby", printer.getCorrectForm(3));
        assertEquals("liczby", printer.getCorrectForm(4));
        assertEquals("liczb", printer.getCorrectForm(5));
        assertEquals("liczb", printer.getCorrectForm(0));
    }

    @Test
    public void shouldPrintGeneratedNumbers() {
        //given
        ResultPrinter printer = new ResultPrinter();
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        //when & then
        assertDoesNotThrow(() -> printer.printGenerateNumbers(numbers));
    }

    @Test
    public void shouldPrintUserNumbers() {
        //given
        ResultPrinter printer = new ResultPrinter();
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        //when & then
        assertDoesNotThrow(() -> printer.printUserNumbers(numbers));
    }

    @Test
    public void shouldCountMatchingNumbers() {
        //given
        ResultPrinter printer = new ResultPrinter();
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        //when & then
        assertDoesNotThrow(() -> printer.countMatchingNumbers(numbers, numbers));
    }
}