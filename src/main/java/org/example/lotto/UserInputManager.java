package org.example.lotto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

import static org.example.lotto.LottoDefaultSettings.*;

public class UserInputManager {

    protected void submitTicket(Set<Integer> set) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (set.size() < NUMBERS_TO_PICK_DEFAULT) {
            try {
                System.out.print("Podaj liczbę: ");
                String input = reader.readLine();

                if (!input.isEmpty() && Integer.parseInt(input) <= MAX_VALUE_DEFAULT) {
                    int number = Integer.parseInt(input);

                    if (!set.contains(number)) {
                        set.add(number);
                    } else {
                        System.out.println("Podany numer został już podany.");
                    }
                } else {
                    System.out.printf("Podano nieprawidłowy ciąg znaków. Podaj cyfrę z zakresu od %s do %s\n", MIN_VALUE_DEFAULT, MAX_VALUE_DEFAULT);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Podano nieprawidłowy ciąg znaków. Podaj cyfrę z zakresu od %s do %s\n", MIN_VALUE_DEFAULT, MAX_VALUE_DEFAULT);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}