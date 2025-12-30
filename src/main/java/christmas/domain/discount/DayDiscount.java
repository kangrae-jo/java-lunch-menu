package christmas.domain.discount;

import christmas.domain.OrderItem;

public class DayDiscount {

    private static final int DISCOUNT_AMOUNT = 2_023;

    public static int discount(OrderItem item) {
        return item.calculatePrice() - item.mul(DISCOUNT_AMOUNT);
    }

}
