package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.domain.bridge.BridgeMaker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class BridgeMakerTest {

    @Test
    void createBridgeTest() {
        // given
        List<Integer> customTiles = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 0, 0, 0));
        int size = customTiles.size();
        BridgeMaker bridgeMaker = new BridgeMaker(() -> customTiles.remove(0));
        // when
        List<String> bridge = bridgeMaker.makeBridge(size);
        // then
        assertThat(bridge).containsExactly("U", "U", "U", "U", "D", "D", "D");
    }
}