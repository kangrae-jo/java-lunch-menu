package christmas.domain.discount;

import christmas.domain.OrderItem;

public interface DiscountPolicy {

    int discount(OrderItem item);

}
