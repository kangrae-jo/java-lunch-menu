package menu.repository;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private final List<String> categories = new ArrayList<>();

    private CategoryRepository() {
        categories.add("");
        categories.add("일식");
        categories.add("한식");
        categories.add("중식");
        categories.add("아시안");
        categories.add("양식");
    }

    public static CategoryRepository init() {
        return new CategoryRepository();
    }

    public String get(int index) {
        return categories.get(index);
    }

}
