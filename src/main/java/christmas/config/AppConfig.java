package christmas.config;

import christmas.controller.Controller;
import christmas.parser.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;

public class AppConfig {

    private InputView inputView;
    private OutputView outputView;
    private InputParser inputParser;
    private Controller controller;

    public AppConfig() {
    }

    public InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public InputParser inputParser() {
        if (inputParser == null) {
            inputParser = new InputParser();
        }
        return inputParser;
    }

    public Controller controller() {
        if (controller == null) {
            controller = new Controller(inputView(), outputView(), inputParser());
        }
        return controller;
    }

}
