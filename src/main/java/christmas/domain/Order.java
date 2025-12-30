package christmas.domain;

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

}
