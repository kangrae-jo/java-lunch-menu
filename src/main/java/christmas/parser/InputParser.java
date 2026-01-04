package christmas.parser;

import christmas.domain.Menu;
import christmas.domain.OrderItem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public LocalDate parseVisitDate(String input) {
        try {
            int day = Integer.parseInt(input.strip());
            return LocalDate.of(2023, 12, day);
        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public List<OrderItem> parseOrderItems(String input) {
        try {
            String[] tokens = input.split(",");
            return makeOrderItems(tokens);
        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private List<OrderItem> makeOrderItems(String[] tokens) {
        List<OrderItem> items = new ArrayList<>();
        for (String token : tokens) {
            String[] menuAndAmount = token.split("-");
            if (menuAndAmount.length != 2) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            Menu menu = Menu.from(menuAndAmount[0].strip());
            int amount = Integer.parseInt(menuAndAmount[1].strip());
            items.add(new OrderItem(menu, amount));
        }
        return items;
    }

}
