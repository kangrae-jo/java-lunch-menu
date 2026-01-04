package christmas.config;

import christmas.controller.Controller;
import christmas.parser.InputParser;
import christmas.service.ChristmasDiscountPolicy;
import christmas.service.DiscountCalculator;
import christmas.service.SpecialDiscountPolicy;
import christmas.service.WeekdayDiscountPolicy;
import christmas.service.WeekendDiscountPolicy;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class AppConfig {

    private InputView inputView;
    private OutputView outputView;
    private InputParser inputParser;
    private DiscountCalculator discountCalculator;
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
            controller = new Controller(inputView(), outputView(), inputParser(), discountCalculator());
        }
        return controller;
    }

    public DiscountCalculator discountCalculator() {
        if (discountCalculator == null) {
            discountCalculator = new DiscountCalculator(List.of(
                    new ChristmasDiscountPolicy(),
                    new WeekdayDiscountPolicy(),
                    new WeekendDiscountPolicy(),
                    new SpecialDiscountPolicy()
            ));
        }
        return discountCalculator;
    }

}
