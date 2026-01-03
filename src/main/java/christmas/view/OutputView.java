package christmas.view;

import christmas.domain.Badge;
import christmas.dto.BenefitResultDto;
import christmas.dto.GiftDto;
import christmas.dto.OrderDto;

public class OutputView {

    public void printErrorMsg(String msg) {
        System.out.println("[ERROR] " + msg);
    }

    public void printNoBenefitMsg() {
        System.out.println("<증정 메뉴>");
        System.out.println("없음");
        System.out.println();

        System.out.println("<혜택 내역>");
        System.out.println("없음");
        System.out.println();
    }

    public void printBenefitInformation(OrderDto orderDto, GiftDto giftDto, BenefitResultDto benefitDto) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", orderDto.date());
        System.out.println();

        printOrderMenus(orderDto);

        long totalPrice = orderDto.totalPrice();
        printTotalPriceBeforeDiscount(totalPrice);

        int totalDiscountPrice = benefitDto.calculateTotalDiscountPrice();
        int totalBenefitPrice = totalDiscountPrice + benefitDto.giftDiscount();
        if (totalPrice < 10_000) {
            printNoBenefitMsg();
            totalDiscountPrice = 0;
            totalBenefitPrice = 0;
        } else {
            printGiftMenu(giftDto);
            printBenefit(benefitDto);
        }

        printTotalBenefitPrice(totalBenefitPrice);
        printTotalPriceAfterDiscount(totalPrice, totalDiscountPrice);
        printBadge(totalBenefitPrice);
    }

    private void printOrderMenus(OrderDto orderDto) {
        System.out.println("<주문 메뉴>");
        for (String order : orderDto.orderItems()) {
            System.out.println(order);
        }
        System.out.println();
    }

    private void printTotalPriceBeforeDiscount(long totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원\n", totalPrice);
        System.out.println();
    }

    private void printGiftMenu(GiftDto giftDto) {
        System.out.println("<증정 메뉴>");
        System.out.println(giftDto.toString());
        System.out.println();
    }

    private void printBenefit(BenefitResultDto benefitDto) {
        System.out.println("<혜택 내역>");
        System.out.println(benefitDto.toString());
        System.out.println();
    }

    private void printTotalBenefitPrice(int totalBenefitPrice) {
        System.out.println("<총혜택 금액>");
        System.out.printf("%,d원\n", -totalBenefitPrice);
        System.out.println();
    }

    private void printTotalPriceAfterDiscount(long totalPrice, int totalDiscountPrice) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원\n", totalPrice - totalDiscountPrice);
        System.out.println();
    }

    private void printBadge(int totalBenefitPrice) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(Badge.from(totalBenefitPrice).getKorean());
        System.out.println();
    }

}
