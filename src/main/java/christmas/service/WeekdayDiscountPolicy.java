package christmas.service;

import christmas.domain.Category;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.time.DayOfWeek;

public class WeekdayDiscountPolicy implements DiscountPolicy {

    @Override
    public DiscountType type() {
        return DiscountType.WEEKDAY;
    }

    @Override
    public int discount(Order order) {
        DayOfWeek day = order.getDate().getDayOfWeek();
        if (day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY) {
            return 0;
        }

        int dessertCount = order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getCategory() == Category.DESSERT)
                .mapToInt(OrderItem::getAmount)
                .sum();
        return dessertCount * 2023;
    }

}
