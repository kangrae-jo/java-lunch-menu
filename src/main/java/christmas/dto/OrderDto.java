package christmas.dto;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.util.List;

public class OrderDto {

    private final List<String> orderItems;
    private final long totalPrice;

    public OrderDto(List<String> orderItems, long totalPrice) {
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }

    public static OrderDto from(Order order) {
        List<String> orders = order.getOrderItems().stream()
                .map(OrderItem::toString)
                .toList();

        return new OrderDto(orders, order.calculateTotalPrice());
    }

    public List<String> getOrderItems() {
        return orderItems;
    }

    public long getTotalPrice() {
        return totalPrice;
    }
    
}
