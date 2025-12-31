package christmas.dto;

import christmas.domain.Menu;
import java.util.Objects;

public record GiftDto(String menu) {

    public static GiftDto from(Menu menu) {
        return new GiftDto(menu.getKorean());
    }

    @Override
    public String toString() {
        if (Objects.equals(menu, Menu.NONE.getKorean())) {
            return Menu.NONE.getKorean();
        }
        return menu + " 1개";
    }

}
