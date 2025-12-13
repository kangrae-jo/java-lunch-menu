package menu.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import menu.domain.Coach;

public class RecommendedResults {

    private final List<String> categoriesByDay = new ArrayList<>();
    private final List<Map<String, String>> menusByDay = new ArrayList<>();

    public void init(List<Coach> coaches) {
        Map<String, String> emptyDay = new LinkedHashMap<>();
        for (Coach coach : coaches) {
            emptyDay.put(coach.name(), null);
        }

        menusByDay.add(emptyDay);
        categoriesByDay.add("");
    }

    public void addCategory(String category) {
        categoriesByDay.add(category);

        // 카테고리 추가 == 새 날짜 시작 임으로 빈 리스트 추가
        Map<String, String> newDay = new LinkedHashMap<>(menusByDay.get(0));
        menusByDay.add(newDay);
    }

    public void addMenu(String coach, String menu) {
        int today = menusByDay.size() - 1;
        menusByDay.get(today).put(coach, menu);
    }

    public boolean canPickCategory(String category) {
        long count = categoriesByDay.stream()
                .filter(c -> c.equals(category))
                .count();
        return count < 2;
    }

    public boolean canPickMenu(String coach, String menu) {
        return menusByDay.stream()
                .map(dayMap -> dayMap.get(coach))
                .noneMatch(menu::equals);
    }

    public String category(int day) {
        return categoriesByDay.get(day);
    }

    public String menu(int day, String name) {
        return menusByDay.get(day).get(name);
    }

}
