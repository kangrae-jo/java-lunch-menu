package christmas.dto;

import christmas.domain.Menu;
import java.util.Objects;

public record GiftDto(
        String type,
        Menu menu
) {

    public int giftDiscount() {
        return menu.getPrice();
    }

    public String toBenefit() {
        if (Objects.equals(menu, Menu.NONE)) {
            return "";
        }
        return type + ": " + String.format("%,d원", -giftDiscount());
    }

    @Override
    public String toString() {
        if (Objects.equals(menu, Menu.NONE)) {
            return Menu.NONE.getKorean();
        }
        return menu.getKorean() + " 1개";
    }

}
