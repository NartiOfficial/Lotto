package org.example.lotto;

import org.example.Game;

public class Lotto implements Game {
    private LottoNumbersManager numbersManager = new LottoNumbersManager();
    private UserInputManager userInputManager = new UserInputManager();
    private ResultsPrinter resultsPrinter = new ResultsPrinter();

    @Override
    public void play() {
        userInputManager.submitTicket(numbersManager.getUserNumbers());
        numbersManager.generateNumbers();
        numbersManager.numberDrawProcess();
        resultsPrinter.printNumbers(numbersManager.getGeneratedNumbers());
    }

}
