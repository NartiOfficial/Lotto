package org.example;

import org.example.lotto.Lotto;
import org.example.lotto.LottoNumberGenerator;
import org.example.lotto.ResultPrinter;
import org.example.lotto.UserInputManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LottoApplication {
    public static void main(String[] args) {
        Lotto lottoGame = new Lotto(
                new LottoNumberGenerator(),
                new UserInputManager(new BufferedReader(new InputStreamReader(System.in))),
                new ResultPrinter());
        lottoGame.play();
    }
}