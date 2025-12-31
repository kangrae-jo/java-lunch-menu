package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.function.Supplier;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LocalDate date = readDateOfVisit();
        String orderItems = inputView.readOrderItems();

        outputView.writeBenefitPreviewMsg();
    }

    private LocalDate readDateOfVisit() {
        return retryUntilValid(() -> {
            String date = inputView.readDateOfVisit();
            return LocalDate.of(2023, 12, Integer.parseInt(date));
        });
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                outputView.printErrorMsg(e.getMessage());
            }
        }
    }

}
