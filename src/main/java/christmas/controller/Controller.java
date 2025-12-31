package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.dto.BenefitResultDto;
import christmas.dto.GiftDto;
import christmas.dto.OrderDto;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LocalDate date = readDateOfVisit();
        List<OrderItem> orderItems = readOrderItems();

        Order order = new Order(date, orderItems);
        if (order.calculateTotalPrice() < 10_000) {
            outputView.printNoBenefitMsg();
            return;
        }

        OrderDto orderDto = OrderDto.from(order);
        GiftDto giftDto = GiftDto.from(order.giftDiscount());
        BenefitResultDto benefitResultDto = BenefitResultDto.from(order);
        outputView.printBenefitInformation(orderDto, giftDto, benefitResultDto);
    }

    private LocalDate readDateOfVisit() {
        return retryUntilValid(() -> {
            String date = inputView.readDateOfVisit();
            return LocalDate.of(2023, 12, Integer.parseInt(date));
        });
    }

    private List<OrderItem> readOrderItems() {
        return retryUntilValid(() -> {
            String rawOrderItems = inputView.readOrderItems();
            String[] splitOrderItems = rawOrderItems.split(",");

            List<OrderItem> orderItems = new ArrayList<>();
            for (String orderItem : splitOrderItems) {
                String[] menuAndAmount = orderItem.split("-");
                Menu menu = Menu.from(menuAndAmount[0].strip());
                Integer amount = Integer.parseInt(menuAndAmount[1]);
                orderItems.add(new OrderItem(menu, amount));
            }

            return orderItems;
        });
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                outputView.printErrorMsg(e.getMessage());
            }
        }
    }

}
