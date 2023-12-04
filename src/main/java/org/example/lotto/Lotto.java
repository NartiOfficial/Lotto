package org.example.lotto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.interfaces.Game;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@AllArgsConstructor
public class Lotto implements Game {
    private final LottoNumberGenerator lottoNumberGenerator;
    private final UserInputManager ticketSubmitter;
    private final ResultPrinter resultDisplayer;

    @Override
    public void play() {
        ticketSubmitter.submitTicket(lottoNumberGenerator.getUserNumbers());
        lottoNumberGenerator.generateNumbers();
        resultDisplayer.numberDrawProcess(lottoNumberGenerator.getGeneratedNumbers());
        resultDisplayer.printUserNumbers(lottoNumberGenerator.getUserNumbers());
        resultDisplayer.printGenerateNumbers(lottoNumberGenerator.getGeneratedNumbers());
        resultDisplayer.countMatchingNumbers(lottoNumberGenerator.getGeneratedNumbers(), lottoNumberGenerator.getUserNumbers());
    }
}
