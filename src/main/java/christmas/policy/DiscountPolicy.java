package christmas.policy;

import christmas.domain.Order;

public interface DiscountPolicy {

    DiscountType type();

    int discount(Order order);

}
