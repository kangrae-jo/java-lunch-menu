package christmas.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {

    private final LocalDate date;
    private final List<OrderItem> orderItems;

    public Order(LocalDate date, List<OrderItem> orderItems) {
        List<OrderItem> copy = new ArrayList<>(orderItems);
        validateUniqueMenu(copy);
        validateMaxOrderAmount(copy);
        validateOnlyDrink(copy);
        this.orderItems = copy;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public List<OrderItem> getOrderItems() {
        return List.copyOf(orderItems);
    }

    private void validateUniqueMenu(List<OrderItem> orderItems) {
        Set<Menu> menus = new HashSet<>();
        for (OrderItem orderItem : orderItems) {
            menus.add(orderItem.getMenu());
        }

        if (menus.size() != orderItems.size()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateMaxOrderAmount(List<OrderItem> orderItems) {
        int orderAmount = orderItems.stream()
                .mapToInt(OrderItem::getAmount)
                .sum();

        if (orderAmount > 20) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateOnlyDrink(List<OrderItem> orderItems) {
        int drinkCount = (int) orderItems.stream()
                .filter(orderItem -> orderItem.getCategory() == Category.DRINK)
                .count();

        if (drinkCount == orderItems.size()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

}
