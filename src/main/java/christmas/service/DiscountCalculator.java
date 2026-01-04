package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.dto.BenefitResultDto;
import christmas.dto.GiftDto;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class DiscountCalculator {

    private static final long MINIMUM_ORDER_PRICE = 10_000;
    private final List<DiscountPolicy> policies;

    public DiscountCalculator(List<DiscountPolicy> policies) {
        this.policies = new ArrayList<>(policies);
    }

    public GiftDto calculateGiftBenefit(Order order) {
        GiftDiscountPolicy giftDiscountPolicy = new GiftDiscountPolicy();
        Menu menu = giftDiscountPolicy.discount(order);
        return new GiftDto(giftDiscountPolicy.type(), menu);
    }

    public BenefitResultDto calculateDiscounts(Order order) {
        EnumMap<DiscountType, Integer> discounts = initDiscounts();
        if (order.calculateTotalPrice() < MINIMUM_ORDER_PRICE) {
            return new BenefitResultDto(discounts);
        }

        for (DiscountPolicy policy : policies) {
            discounts.put(policy.type(), policy.discount(order));
        }
        return new BenefitResultDto(discounts);
    }

    private EnumMap<DiscountType, Integer> initDiscounts() {
        EnumMap<DiscountType, Integer> discounts = new EnumMap<>(DiscountType.class);
        for (DiscountType type : DiscountType.values()) {
            discounts.put(type, 0);
        }
        return discounts;
    }

}
