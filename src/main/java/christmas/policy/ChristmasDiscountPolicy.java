package christmas.policy;

import christmas.domain.Order;
import java.time.LocalDate;

public class ChristmasDiscountPolicy implements DiscountPolicy {

    private final static LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
    private final static int INIT_DISCOUNT_MONEY = 1_000;
    private final static int ADDITIONAL_DISCOUNT_MONEY = 100;

    @Override
    public DiscountType type() {
        return DiscountType.CHRISTMAS;
    }

    @Override
    public int discount(Order order) {
        LocalDate date = order.getDate();
        if (!date.isAfter(CHRISTMAS)) {
            return INIT_DISCOUNT_MONEY + ADDITIONAL_DISCOUNT_MONEY * (date.getDayOfMonth() - 1);
        }
        return 0;
    }

}
