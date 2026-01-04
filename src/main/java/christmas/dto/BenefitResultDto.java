package christmas.dto;

import christmas.service.DiscountType;
import java.util.EnumMap;
import java.util.StringJoiner;

public record BenefitResultDto(
        EnumMap<DiscountType, Integer> discounts
) {

    public int calculateTotalDiscountPrice() {
        return discounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private boolean hasNoDiscount() {
        return discounts.values().stream()
                .allMatch(value -> value == 0);
    }

    @Override
    public String toString() {
        if (hasNoDiscount()) {
            return "없음";
        }

        StringJoiner joiner = new StringJoiner("\n");
        for (DiscountType type : DiscountType.values()) {
            int discount = discounts.getOrDefault(type, 0);
            if (discount == 0) {
                continue;
            }
            joiner.add(type.getKorean() + ": " + String.format("%,d원", -discount));
        }

        return joiner.toString();
    }

}
