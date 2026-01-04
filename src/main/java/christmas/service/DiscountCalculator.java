package christmas.service;

import christmas.domain.Order;
import christmas.dto.BenefitResultDto;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class DiscountCalculator {

    private static final long MINIMUM_ORDER_PRICE = 10_000;
    private final List<DiscountPolicy> policies;

    public DiscountCalculator(List<DiscountPolicy> policies) {
        this.policies = new ArrayList<>(policies);
    }

    public BenefitResultDto calculate(Order order) {
        if (order.calculateTotalPrice() < MINIMUM_ORDER_PRICE) {
            return new BenefitResultDto(0, 0, 0, 0, 0);
        }

        EnumMap<DiscountType, Integer> discounts = initDiscounts();
        for (DiscountPolicy policy : policies) {
            discounts.put(policy.type(), policy.discount(order));
        }

        return new BenefitResultDto(
                discounts.get(DiscountType.CHRISTMAS),
                discounts.get(DiscountType.WEEKDAY),
                discounts.get(DiscountType.WEEKEND),
                discounts.get(DiscountType.SPECIAL),
                order.giftDiscount().getPrice()
        );
    }

    private EnumMap<DiscountType, Integer> initDiscounts() {
        EnumMap<DiscountType, Integer> discounts = new EnumMap<>(DiscountType.class);
        for (DiscountType type : DiscountType.values()) {
            discounts.put(type, 0);
        }
        return discounts;
    }
}
