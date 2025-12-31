package christmas.dto;

import christmas.domain.Order;

public class BenefitResultDto {

    private int christmasDiscount;
    private int weekdaysDiscount;
    private int weekendDiscount;
    private int specialDiscount;
    private int giftDiscount;

    public BenefitResultDto(int christmasDiscount, int weekdaysDiscount, int weekendDiscount,
                            int specialDiscount, int giftDiscount) {
        this.christmasDiscount = christmasDiscount;
        this.weekdaysDiscount = weekdaysDiscount;
        this.weekendDiscount = weekendDiscount;
        this.specialDiscount = specialDiscount;
        this.giftDiscount = giftDiscount;
    }

    public static BenefitResultDto from(Order order) {
        return new BenefitResultDto(
                order.christmasDiscount(),
                order.weekDaysDiscount(),
                order.weekendDiscount(),
                order.specialDiscount(),
                order.giftDiscount().getPrice()
        );
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public int getWeekdaysDiscount() {
        return weekdaysDiscount;
    }

    public int getWeekendDiscount() {
        return weekendDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public int getGiftDiscount() {
        return giftDiscount;
    }

}
