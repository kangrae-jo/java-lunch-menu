package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputView.readDateOfVisit();
        inputView.readOrderItems();
        outputView.writeBenefitPreview();
    }

}
