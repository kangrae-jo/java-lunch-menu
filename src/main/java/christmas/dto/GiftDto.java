package christmas.dto;

import christmas.domain.Menu;

public class GiftDto {

    private String menu;

    public GiftDto(String menu) {
        this.menu = menu;
    }

    public GiftDto from(Menu menu) {
        return new GiftDto(menu.getKorean());
    }

}
