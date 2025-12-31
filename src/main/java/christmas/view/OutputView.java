package christmas.view;

import christmas.domain.Badge;
import christmas.dto.BenefitResultDto;
import christmas.dto.GiftDto;
import christmas.dto.OrderDto;

public class OutputView {

    public void printErrorMsg(String msg) {
        System.out.println("[ERROR] " + msg);
    }

    public void printBenefitInformation(OrderDto orderDto, GiftDto giftDto, BenefitResultDto benefitDto) {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();

        printOrderMenus(orderDto);
        long totalPrice = orderDto.totalPrice();
        printTotalPriceBeforeDiscount(totalPrice);
        printGiftMenu(giftDto);
        printBenefit(benefitDto);

        int totalDiscountPrice = benefitDto.calculateTotalDiscountPrice();
        int totalBenefitPrice = totalDiscountPrice + benefitDto.giftDiscount();

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
        System.out.println(totalPrice);
        System.out.println();
    }

    private void printGiftMenu(GiftDto giftDto) {
        System.out.println("<증정 메뉴>");
        System.out.println(giftDto.getMenu() + " " + giftDto.getAmount() + "개");
        System.out.println();
    }

    private void printBenefit(BenefitResultDto benefitDto) {
        System.out.println("<혜택 내역>");
        System.out.println("크리스마스 디데이 할인: " + benefitDto.christmasDiscount());
        System.out.println("평일 할인: " + benefitDto.weekdaysDiscount());
        System.out.println("주말 할인: " + benefitDto.weekendDiscount());
        System.out.println("특별 할인: " + benefitDto.specialDiscount());
        System.out.println("증정 이벤트: " + benefitDto.giftDiscount());
        System.out.println();
    }

    private void printTotalBenefitPrice(int totalBenefitPrice) {
        System.out.println("<총 혜택 내역>");
        System.out.println(-totalBenefitPrice);
        System.out.println();
    }

    private void printTotalPriceAfterDiscount(long totalPrice, int totalDiscountPrice) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(totalPrice - totalDiscountPrice);
        System.out.println();
    }

    private void printBadge(int totalBenefitPrice) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(Badge.from(totalBenefitPrice).getKorean());
        System.out.println();
    }

}
