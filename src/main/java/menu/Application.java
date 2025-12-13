package menu;

import menu.controller.MenuController;
import menu.repository.CategoryRepository;
import menu.repository.MenuRepository;
import menu.service.PickService;
import menu.view.InputView;
import menu.view.OutputView;

public class Application {

    public static void main(String[] args) {
        PickService pickService = new PickService(CategoryRepository.init(), MenuRepository.init());
        MenuController controller = new MenuController(new InputView(), new OutputView(), pickService);
        controller.run();
    }

}
