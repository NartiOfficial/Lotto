package org.example.lotto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.interfaces.Game;

@NoArgsConstructor
@AllArgsConstructor
public class Lotto implements Game {
    private LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
    private UserInputManager ticketSubmitter = new UserInputManager();
    private ResultPrinter resultDisplayer = new ResultPrinter();


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