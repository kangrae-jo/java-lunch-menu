package christmas.domain.discount;

public class ChristmasDiscount implements DiscountPolicy {

    private final static int INIT_DISCOUNT_MONEY = 1_000;
    private final static int ADDITIONAL_DISCOUNT_MONEY = 100;

    @Override
    public int discount(int date) {
        return INIT_DISCOUNT_MONEY + ADDITIONAL_DISCOUNT_MONEY * (date - 1);
    }

}
