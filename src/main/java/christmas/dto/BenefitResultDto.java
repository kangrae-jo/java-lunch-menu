package christmas.dto;

import christmas.domain.Order;

public record BenefitResultDto(
        int christmasDiscount,
        int weekdaysDiscount,
        int weekendDiscount,
        int specialDiscount,
        int giftDiscount
) {

    public static BenefitResultDto from(Order order) {
        return new BenefitResultDto(
                order.christmasDiscount(),
                order.weekDaysDiscount(),
                order.weekendDiscount(),
                order.specialDiscount(),
                order.giftDiscount().getPrice()
        );
    }

    public int calculateTotalDiscountPrice() {
        return christmasDiscount + weekdaysDiscount + weekendDiscount + specialDiscount;
    }

}
