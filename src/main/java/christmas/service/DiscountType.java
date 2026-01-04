package christmas.service;

public enum DiscountType {

    CHRISTMAS("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인");

    private final String korean;

    DiscountType(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }

}
