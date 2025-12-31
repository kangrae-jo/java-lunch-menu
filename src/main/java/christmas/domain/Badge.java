package christmas.domain;

public enum Badge {

    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String korean;
    private final int threshold;

    Badge(String korean, int threshold) {
        this.korean = korean;
        this.threshold = threshold;
    }

    public static Badge from(int discountAmount) {
        for (Badge badge : Badge.values()) {
            if (badge.threshold >= discountAmount) {
                return badge;
            }
        }
        return NONE;
    }

}
