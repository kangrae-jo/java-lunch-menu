package christmas.domain.discount;

import java.time.LocalDate;

public class ChristmasDiscount {

    private final static int INIT_DISCOUNT_MONEY = 1_000;
    private final static int ADDITIONAL_DISCOUNT_MONEY = 100;

    public int discount(LocalDate date) {
        return INIT_DISCOUNT_MONEY + ADDITIONAL_DISCOUNT_MONEY * (date.getDayOfMonth() - 1);
    }

}
