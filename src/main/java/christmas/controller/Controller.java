package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.dto.BenefitResultDto;
import christmas.dto.GiftDto;
import christmas.dto.OrderDto;
import christmas.parser.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputParser inputParser;

    public Controller(InputView inputView, OutputView outputView, InputParser inputParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
    }

    public void run() {
        LocalDate date = readDateOfVisit();
        Order order = readOrder(date);

        OrderDto orderDto = OrderDto.from(order);
        GiftDto giftDto = GiftDto.from(order.giftDiscount());
        BenefitResultDto benefitResultDto = BenefitResultDto.from(order);

        outputView.printBenefitInformation(orderDto, giftDto, benefitResultDto);
    }

    private LocalDate readDateOfVisit() {
        return retryUntilValid(() ->
                inputParser.parseVisitDate(inputView.readDateOfVisit())
        );
    }

    private Order readOrder(LocalDate date) {
        return retryUntilValid(() -> {
            List<OrderItem> orderItems = readOrderItems();
            return new Order(date, orderItems);
        });
    }

    private List<OrderItem> readOrderItems() {
        return retryUntilValid(() ->
                inputParser.parseOrderItems(inputView.readOrderItems())
        );
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
