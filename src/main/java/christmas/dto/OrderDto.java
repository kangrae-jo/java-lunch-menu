package christmas.dto;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.util.List;

public record OrderDto(
        List<String> orderItems,
        int date,
        long totalPrice
) {

    public static OrderDto from(Order order) {
        List<String> orders = order.getOrderItems().stream()
                .map(OrderItem::toString)
                .toList();

        return new OrderDto(orders, order.getDate(), order.calculateTotalPrice());
    }

}
