package christmas.domain;

import java.util.List;

public class Order {

    private final List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
