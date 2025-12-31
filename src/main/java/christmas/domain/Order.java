package christmas.domain;

import christmas.domain.discount.ChristmasDiscount;
import java.time.LocalDate;
import java.util.List;

public class Order {

    private final LocalDate date;
    private final List<OrderItem> orderItems;

    public Order(LocalDate date, List<OrderItem> orderItems) {
        this.date = date;
        this.orderItems = orderItems;
    }

    public void add(List<OrderItem> orderItems) {
        this.orderItems.addAll(orderItems);
    }

    public long calculateTotalPrice() {
        return orderItems.stream()
                .mapToLong(OrderItem::calculatePrice)
                .sum();
    }

    public Menu giftDiscount() {
        if (calculateTotalPrice() >= 120_000) {
            return Menu.CHAMPAGNE;
        }
        return Menu.NONE;
    }

    public int christmasDiscount() {
        return ChristmasDiscount.discount(date);
    }

    public int specialDiscount() {
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (dayOfWeek == 7 || date.isEqual(LocalDate.of(2023, 12, 25))) {
            return 1_000;
        }
        return 0;
    }

    public int weekDaysDiscount() {
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (dayOfWeek % 7 >= 4) {
            return 0;
        }

        List<OrderItem> dessertList = orderItems.stream()
                .filter(orderItem -> orderItem.getCategory() == Category.DESSERT)
                .toList();

        return dessertList.stream()
                .mapToInt(OrderItem::getAmount)
                .sum() * 2023;
    }

    public int weekendDiscount() {
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (dayOfWeek % 7 < 4) {
            return 0;
        }

        List<OrderItem> dessertList = orderItems.stream()
                .filter(orderItem -> orderItem.getCategory() == Category.MAIN)
                .toList();

        return dessertList.stream()
                .mapToInt(OrderItem::getAmount)
                .sum() * 2023;
    }

}
