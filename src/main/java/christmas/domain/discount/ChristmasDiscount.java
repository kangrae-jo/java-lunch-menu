package christmas.domain.discount;

import java.time.LocalDate;

public class ChristmasDiscount {

    private final static LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
    private final static int INIT_DISCOUNT_MONEY = 1_000;
    private final static int ADDITIONAL_DISCOUNT_MONEY = 100;

    public static int discount(LocalDate date) {
        if (date.isBefore(CHRISTMAS)) {
            return INIT_DISCOUNT_MONEY + ADDITIONAL_DISCOUNT_MONEY * (date.getDayOfMonth() - 1);
        }
        return 0;
    }

}
