package org.example.lotto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.Game;

@NoArgsConstructor
@AllArgsConstructor
public class Lotto implements Game {
    private LottoNumbersManager numbersManager = new LottoNumbersManager();
    private UserInputManager userInputManager = new UserInputManager();
    private ResultsPrinter resultsPrinter = new ResultsPrinter();


    @Override
    public void play() {
        userInputManager.submitTicket(numbersManager.getUserNumbers());
        numbersManager.generateNumbers();
        numbersManager.numberDrawProcess();
        resultsPrinter.printUserNumbers(numbersManager.getUserNumbers());
        resultsPrinter.printGenerateNumbers(numbersManager.getGeneratedNumbers());
        resultsPrinter.countMatchingNumbers(numbersManager.getGeneratedNumbers(), numbersManager.getUserNumbers());
    }

}
