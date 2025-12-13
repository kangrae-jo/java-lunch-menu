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

    // TODO: 이전 선택과 겹치는지 등의 validate 필요
    public void makeRecommend(RecommendedResults results, List<Coach> coaches) {
        int index = Randoms.pickNumberInRange(1, 5);
        results.addCategory(index, categoryRepository.get(index));

        for (Coach coach : coaches) {
            List<String> menus = menuRepository.get(index);
            String menu = Randoms.shuffle(menus).get(0);
            results.addMenu(index, coach.name(), menu);
        }
    }

}
