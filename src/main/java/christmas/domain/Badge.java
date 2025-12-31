package christmas.domain;

public enum Badge {

    STAR(5_000),
    TREE(10_000),
    SANTA(20_000);

    private final int threshold;

    Badge(int threshold) {
        this.threshold = threshold;
    }

}
