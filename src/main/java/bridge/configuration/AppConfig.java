package bridge.configuration;

import bridge.controller.BridgeController;
import bridge.view.InputView;
import bridge.view.OutputView;

public class AppConfig {

    public BridgeController bridgeController() {
        return new BridgeController(inputView(), outputView());
    }

    private InputView inputView() {
        return InputView.getInputView();
    }

    private OutputView outputView() {
        return OutputView.getOutputView();
    }

}