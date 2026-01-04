package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.config.AppConfig;
import christmas.controller.Controller;

public class Application {

    public static void main(String[] args) {
        try {
            AppConfig appConfig = new AppConfig();
            Controller controller = appConfig.controller();
            controller.run();
        } finally {
            Console.close();
        }
    }

}
