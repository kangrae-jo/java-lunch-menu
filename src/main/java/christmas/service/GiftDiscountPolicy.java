package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Order;

public class GiftDiscountPolicy {

    public String type() {
        return "증정 이벤트";
    }

    public Menu discount(Order order) {
        if (order.calculateTotalPrice() >= 120_000) {
            return Menu.CHAMPAGNE;
        }
        return Menu.NONE;
    }

}
