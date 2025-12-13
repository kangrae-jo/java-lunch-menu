package menu.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import menu.domain.Coach;

public class RecommendedResults {

    private List<String> categories = new ArrayList<>();
    private List<Map<String, String>> menus = new ArrayList<>();

    public void init(List<Coach> coaches) {
        Map<String, String> coachAndMenu = new LinkedHashMap<>();

        for (Coach coach : coaches) {
            coachAndMenu.put(coach.name(), null);
        }

        menus.add(coachAndMenu);
    }

    public void addCategory(int index, String category) {
        categories.add(index, category);
    }

    public void addMenu(int index, String coach, String menu) {
        menus.get(index).put(coach, menu);
    }

    public void printResults() {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("[카테고리] " + categories.get(i));

            Map<String, String> coachMenus = menus.get(i);
            for (Map.Entry<String, String> entry : coachMenus.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

            System.out.println(); // 회차 구분용 공백
        }
    }

}
