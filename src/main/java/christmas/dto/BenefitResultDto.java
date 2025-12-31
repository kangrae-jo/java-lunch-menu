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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (christmasDiscount != 0) {
            sb.append("크리스마스 디데이 할인: ").append(String.format("%,d원", -christmasDiscount)).append("\n");
        }
        if (weekdaysDiscount != 0) {
            sb.append("평일 할인: ").append(String.format("%,d원", -weekdaysDiscount)).append("\n");
        }
        if (weekendDiscount != 0) {
            sb.append("주말 할인: ").append(String.format("%,d원", -weekendDiscount)).append("\n");
        }
        if (specialDiscount != 0) {
            sb.append("특별 할인: ").append(String.format("%,d원", -specialDiscount)).append("\n");
        }
        if (giftDiscount != 0) {
            sb.append("증정 이벤트: ").append(String.format("%,d원", -giftDiscount));
        }
        if (sb.isEmpty()) {
            return "없음";
        }

        return sb.toString();
    }

}
