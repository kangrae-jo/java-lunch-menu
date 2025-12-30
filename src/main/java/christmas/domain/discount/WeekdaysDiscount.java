package christmas.domain.discount;

import christmas.domain.OrderItem;

public class WeekdaysDiscount implements DiscountPolicy {

    private static final int DISCOUNT_AMOUNT = 2_023;

    @Override
    public int discount(OrderItem item) {
        return item.calculatePrice() - item.mul(DISCOUNT_AMOUNT);
    }

}
