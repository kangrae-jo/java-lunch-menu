package christmas.domain;

import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

    private final long price = 55_000 * 3 + 5_000 * 2;
    private final List<OrderItem> orderItems = List.of(
            new OrderItem(Menu.from("티본스테이크"), 3),
            new OrderItem(Menu.from("아이스크림"), 2)
    );

    @Test
    void 총_주문_금액을_올바르게_계산하여_반환한다() {
        Order order = Order.from(LocalDate.of(2023, 12, 26));
        order.add(orderItems);

        long orderPrice = order.calculateTotalPrice();

        Assertions.assertThat(orderPrice).isEqualTo(price);
    }

}
