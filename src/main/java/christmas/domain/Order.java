package christmas.domain;

import christmas.domain.discount.ChristmasDiscount;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final LocalDate date;
    private final List<OrderItem> orderItems;

    private Order(LocalDate localDate) {
        this.date = localDate;
        this.orderItems = new ArrayList<>();
    }

    public static Order from(LocalDate localDate) {
        return new Order(localDate);
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

    public int dayDiscount() {
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (dayOfWeek % 7 >= 4) {
            return WeekDaysDiscount();
        }
        return WeekendDiscount();
    }

    public int specialDiscount() {
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (dayOfWeek == 7 || date.isEqual(LocalDate.of(2023, 12, 25))) {
            return 1_000;
        }
        return 0;
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
