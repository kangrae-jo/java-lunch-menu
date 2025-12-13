package menu.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import menu.domain.Coach;
import menu.dto.RecommendedResults;
import menu.service.PickService;
import menu.view.InputView;

public class MenuController {

    private final InputView inputView;
    private final PickService pickService;

    public MenuController(InputView inputView, PickService pickService) {
        this.inputView = inputView;
        this.pickService = pickService;
    }

    public void run() {
        List<Coach> coaches = readCoachesName();
        readRestrictions(coaches);

        RecommendedResults results = makeMenuResult(coaches);
        results.printResults();
    }

    private List<Coach> readCoachesName() {
        return retryUntilValid(() -> {
            String names = inputView.readCoachesName();
            return Arrays.stream(names.split(","))
                    .map(Coach::register)
                    .toList();
        });
    }

    private void readRestrictions(List<Coach> coaches) {
        for (Coach coach : coaches) {
            retryUntilValid(() -> {
                String restrictions = inputView.readRestrictions();
                return coach.addRestrictions(Arrays.stream(restrictions.split(",")).toList());
            });
        }
    }

    private RecommendedResults makeMenuResult(List<Coach> coaches) {
        RecommendedResults results = new RecommendedResults();
        results.init(coaches);

        for (int day = 1; day <= 5; day++) {
            pickService.makeRecommend(results, coaches);
        }

        return results;
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
