package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.dto.BenefitResultDto;
import christmas.dto.GiftDto;
import christmas.dto.OrderDto;
import christmas.parser.InputParser;
import christmas.service.DiscountCalculator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputParser inputParser;
    private final DiscountCalculator discountCalculator;

    public Controller(InputView inputView, OutputView outputView, InputParser inputParser,
                      DiscountCalculator discountCalculator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
        this.discountCalculator = discountCalculator;
    }

    public void run() {
        LocalDate date = readDateOfVisit();
        Order order = readOrder(date);

        OrderDto orderDto = OrderDto.from(order);
        GiftDto giftDto = discountCalculator.calculateGiftBenefit(order);
        BenefitResultDto benefitResultDto = discountCalculator.calculateDiscounts(order);

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
            } catch (IllegalArgumentException e) {
                outputView.printErrorMsg(e.getMessage());
            }
        }
    }

}
