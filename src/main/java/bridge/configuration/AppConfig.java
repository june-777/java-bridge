package bridge.configuration;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.controller.BridgeController;
import bridge.controller.FrontController;
import bridge.view.InputView;
import bridge.view.OutputView;

public class AppConfig {

    public FrontController frontController() {
        return new FrontController(inputView(), outputView(), bridgeController(), bridgeMaker());
    }

    private BridgeController bridgeController() {
        return new BridgeController(inputView(), outputView());
    }

    private InputView inputView() {
        return InputView.getInputView();
    }

    private OutputView outputView() {
        return OutputView.getOutputView();
    }

    private BridgeMaker bridgeMaker() {
        return new BridgeMaker(bridgeRandomNumberGenerator());
    }

    private BridgeNumberGenerator bridgeRandomNumberGenerator() {
        return new BridgeRandomNumberGenerator();
    }

}