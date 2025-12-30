package christmas.domain;

public class OrderItem {

    private final Menu menu;
    private final int amount;

    public OrderItem(Menu menu, Integer amount) {
        this.menu = menu;
        this.amount = amount;
    }

    public Integer calculatePrice() {
        return menu.mul(amount);
    }

}
