package christmas.domain;

public class OrderItem {

    private final Menu menu;
    private final int amount;

    public OrderItem(Menu menu, Integer amount) {
        validateAmount(amount);
        this.amount = amount;
        this.menu = menu;
    }

    public Integer calculatePrice() {
        return menu.mul(amount);
    }

    public int mul(int number) {
        return amount * number;
    }

    public Category getCategory() {
        return menu.getCategory();
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return menu.getKorean() + " " + amount + "개";
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("0개 이하는 주문할 수 없습니다.");
        }
    }

}
