package menu.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import menu.domain.Coach;
import menu.dto.RecommendedResults;
import menu.repository.CategoryRepository;
import menu.repository.MenuRepository;

public class PickService {

    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;

    public PickService(CategoryRepository categoryRepository, MenuRepository menuRepository) {
        this.categoryRepository = categoryRepository;
        this.menuRepository = menuRepository;
    }

    public void makeRecommend(RecommendedResults results, List<Coach> coaches) {
        int index = pickCategory(results);
        results.addCategory(categoryRepository.get(index));

        for (Coach coach : coaches) {
            List<String> menus = menuRepository.get(index);
            String menu = pickMenu(results, coach, menus);
            results.addMenu(coach.name(), menu);
        }
    }

    private int pickCategory(RecommendedResults results) {
        while (true) {
            int index = Randoms.pickNumberInRange(1, 5);
            String category = categoryRepository.get(index);
            if (results.canPickCategory(category)) {
                return index;
            }
        }
    }

    private String pickMenu(RecommendedResults results, Coach coach, List<String> menus) {
        while (true) {
            String menu = Randoms.shuffle(menus).get(0);
            if (results.canPickMenu(coach.name(), menu)) {
                return menu;
            }
        }
    }

}
