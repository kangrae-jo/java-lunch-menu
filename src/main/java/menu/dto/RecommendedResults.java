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
        Map<String, String> coachAndMenu = new LinkedHashMap<>();

        for (Coach coach : coaches) {
            coachAndMenu.put(coach.name(), null);
        }

        menusByDay.add(coachAndMenu);
    }

    public void addCategory(String category) {
        categoriesByDay.add(category);
    }

    public void addMenu(String coach, String menu) {
        menusByDay.add(Map.of(coach, menu));
    }

    public boolean canPickCategory(String category) {
        long count = categoriesByDay.stream()
                .filter(c -> c.equals(category))
                .count();

        return count < 2;
    }

    public boolean canPickMenu(String coach, String menu) {
        return menusByDay.stream()
                .map(map -> map.get(coach))
                .noneMatch(menu::equals);
    }

    public void printResults() {
        for (int i = 0; i < categoriesByDay.size(); i++) {
            System.out.println("[카테고리] " + categoriesByDay.get(i));

            Map<String, String> coachMenus = menusByDay.get(i);
            for (Map.Entry<String, String> entry : coachMenus.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

            System.out.println(); // 회차 구분용 공백
        }
    }

}
