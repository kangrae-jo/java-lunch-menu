package menu.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import menu.domain.Coach;
import menu.view.InputView;

public class MenuController {

    private final InputView inputView;

    public MenuController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        List<Coach> names = readCoachesName();
    }

    private List<Coach> readCoachesName() {
        return retryUntilValid(() -> {
            String names = inputView.readCoachesName();
            return Arrays.stream(names.split(","))
                    .map(Coach::new)
                    .toList();
        });
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
