package menu.dto;

import java.util.List;
import menu.domain.Coach;

public record CoachNames(List<String> names) {

    public static CoachNames from(List<Coach> names) {
        return new CoachNames(names.stream()
                .map(Coach::name)
                .toList());
    }

}
