package christmas.service;

import christmas.domain.Category;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.time.DayOfWeek;

public class WeekendDiscountPolicy implements DiscountPolicy {

    @Override
    public DiscountType type() {
        return DiscountType.WEEKEND;
    }

    @Override
    public int discount(Order order) {
        DayOfWeek day = order.getDate().getDayOfWeek();
        if (day != DayOfWeek.FRIDAY && day != DayOfWeek.SATURDAY) {
            return 0;
        }

        int mainCount = order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getCategory() == Category.MAIN)
                .mapToInt(OrderItem::getAmount)
                .sum();
        return mainCount * 2023;
    }

}
