package christmas.domain;

import christmas.domain.discount.ChristmasDiscount;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final LocalDate date;
    private final List<OrderItem> orderItems;

    private Order(int year, int month, int date) {
        this.date = LocalDate.of(year, month, date);
        this.orderItems = new ArrayList<>();
    }

    public static Order from(int year, int month, int date) {
        return new Order(year, month, date);
    }

    public void add(List<OrderItem> orderItems) {
        this.orderItems.addAll(orderItems);
    }

    public long calculateTotalPrice() {
        return orderItems.stream()
                .mapToLong(OrderItem::calculatePrice)
                .sum();
    }

    public int christmasDiscount() {
        return ChristmasDiscount.discount(date);
    }

    public int dayDiscount() {
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (dayOfWeek % 7 >= 4) {
            return WeekDaysDiscount();
        }
        return WeekendDiscount();
    }

    private int WeekDaysDiscount() {
        List<OrderItem> dessertList = orderItems.stream()
                .filter(orderItem -> orderItem.getCategory() == Category.DESSERT)
                .toList();

        return dessertList.stream()
                .mapToInt(OrderItem::getAmount)
                .sum() * 2023;
    }

    private int WeekendDiscount() {
        List<OrderItem> dessertList = orderItems.stream()
                .filter(orderItem -> orderItem.getCategory() == Category.MAIN)
                .toList();

        return dessertList.stream()
                .mapToInt(OrderItem::getAmount)
                .sum() * 2023;
    }

}
