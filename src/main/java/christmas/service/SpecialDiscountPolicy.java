package christmas.service;

import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);

    @Override
    public DiscountType type() {
        return DiscountType.SPECIAL;
    }

    @Override
    public int discount(Order order) {
        DayOfWeek day = order.getDate().getDayOfWeek();
        if (day == DayOfWeek.SUNDAY || order.getDate().isEqual(CHRISTMAS)) {
            return 1_000;
        }
        return 0;
    }

}
