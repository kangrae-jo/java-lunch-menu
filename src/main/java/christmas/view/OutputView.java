package christmas.view;

import christmas.domain.Badge;
import christmas.dto.BenefitResultDto;
import christmas.dto.GiftDto;
import christmas.dto.OrderDto;

public class OutputView {

    public void printBenefitInformation(OrderDto orderDto, GiftDto giftDto, BenefitResultDto benefitDto) {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();

        System.out.println("<주문 메뉴>");
        for (String order : orderDto.getOrderItems()) {
            System.out.println(order);
        }
        System.out.println();

        System.out.println("<할인 전 총주문 금액>");
        System.out.println(orderDto.getTotalPrice());
        System.out.println();

        System.out.println("<증정 메뉴>");
        System.out.println(giftDto.getMenu() + " " + giftDto.getAmount() + "개");
        System.out.println();

        System.out.println("<혜택 내역>");
        System.out.println("크리스마스 디데이 할인: " + benefitDto.getChristmasDiscount());
        System.out.println("평일 할인: " + benefitDto.getWeekdaysDiscount());
        System.out.println("주말 할인: " + benefitDto.getWeekendDiscount());
        System.out.println("특별 할인: " + benefitDto.getSpecialDiscount());
        System.out.println("증정 이벤트: " + benefitDto.getGiftDiscount());
        System.out.println();

        int totalBenefitPrice = benefitDto.getChristmasDiscount() +
                benefitDto.getWeekdaysDiscount() +
                benefitDto.getWeekendDiscount() +
                benefitDto.getSpecialDiscount();

        System.out.println("<총 혜택 내역>");
        System.out.println(-totalBenefitPrice - benefitDto.getGiftDiscount());
        System.out.println();

        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(orderDto.getTotalPrice() - totalBenefitPrice);
        System.out.println();

        System.out.println("<12월 이벤트 배지>");
        System.out.println(Badge.from(totalBenefitPrice + benefitDto.getGiftDiscount()).getKorean());
        System.out.println();
    }

    public void printErrorMsg(String msg) {
        System.out.println("[ERROR] " + msg);
    }

}
