package bridge;

import bridge.configuration.AppConfig;
import bridge.controller.FrontController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        FrontController frontController = appConfig.frontController();

        frontController.process();
    }
}
