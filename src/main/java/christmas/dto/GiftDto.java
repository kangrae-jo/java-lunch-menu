package christmas.dto;

import christmas.domain.Menu;

public class GiftDto {

    private String menu;
    private int amount;

    public GiftDto(String menu) {
        this.menu = menu;
        this.amount = 1;
    }

    public static GiftDto from(Menu menu) {
        return new GiftDto(menu.getKorean());
    }

    public String getMenu() {
        return menu;
    }

    public int getAmount() {
        return amount;
    }

}
