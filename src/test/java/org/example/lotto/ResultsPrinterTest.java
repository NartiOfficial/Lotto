package org.example.lotto;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ResultsPrinterTest {
    @Test
    public void testGetCorrectForm() {
        ResultsPrinter printer = new ResultsPrinter();
        assertEquals("liczba", printer.getCorrectForm(1));
        assertEquals("liczby", printer.getCorrectForm(2));
        assertEquals("liczby", printer.getCorrectForm(3));
        assertEquals("liczby", printer.getCorrectForm(4));
        assertEquals("liczb", printer.getCorrectForm(5));
        assertEquals("liczb", printer.getCorrectForm(0));
    }

    @Test
    public void testPrintMethods() {
        ResultsPrinter printer = new ResultsPrinter();
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);
        assertDoesNotThrow(() -> printer.printGenerateNumbers(numbers));
        assertDoesNotThrow(() -> printer.printUserNumbers(numbers));
        assertDoesNotThrow(() -> printer.countMatchingNumbers(numbers, numbers));
    }
}
