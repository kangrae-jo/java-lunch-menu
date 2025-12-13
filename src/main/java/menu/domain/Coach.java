package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class Coach {

    private final static int MIN_NAME_RANGE = 2;
    private final static int MAX_NAME_RANGE = 4;
    private final static int MAX_RESTRICTION_RANGE = 2;

    private final String name;
    private final List<Menu> restrictions;

    public Coach(String name) {
        validateNameRange(name);
        this.name = name;
        this.restrictions = new ArrayList<>();
    }

    public Coach(String name, List<String> restrictions) {
        validateNameRange(name);
        this.name = name;
        validateRestrictionRange(restrictions);
        this.restrictions = Menu.from(restrictions);

    }

    private void validateNameRange(String name) {
        if (name.length() < MIN_NAME_RANGE || name.length() > MAX_NAME_RANGE) {
            throw new IllegalArgumentException("[ERROR] 코치 이름은 2~4자 입니다.");
        }
    }

    private void validateRestrictionRange(List<String> restrictions) {
        if (restrictions.size() > MAX_RESTRICTION_RANGE) {
            throw new IllegalArgumentException("[ERROR] 코치가 먹지 못하는 메뉴는 최대 2개 입니다.");
        }
    }

}
