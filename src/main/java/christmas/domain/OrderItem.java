package christmas.domain;

public class OrderItem {

    private final Menu menu;
    private final Integer amount;

    public OrderItem(Menu menu, Integer amount) {
        this.menu = menu;
        this.amount = amount;
    }

}
