package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.domain.bridge.BridgeGame;
import bridge.domain.bridge.BridgeGameStatus;
import bridge.domain.moving.MoveCommand;
import bridge.domain.moving.MoveResult;
import bridge.domain.moving.MovingStatus;
import java.util.List;
import org.junit.jupiter.api.Test;

class BridgeGameTest {

    @Test
    void test() {
        List<String> bridge = List.of("U", "U", "D");
        BridgeGame bridgeGame = new BridgeGame(bridge);

        MoveResult status1 = bridgeGame.move(MoveCommand.U);
        MoveResult status2 = bridgeGame.move(MoveCommand.D);

        assertThat(status1.movingStatus()).isEqualTo(MovingStatus.SUCCESS);
        assertThat(status2.movingStatus()).isEqualTo(MovingStatus.FAIL);

        MoveResult status3 = bridgeGame.move(MoveCommand.U);
        MoveResult status4 = bridgeGame.move(MoveCommand.U);
        MoveResult status5 = bridgeGame.move(MoveCommand.U);

        assertThat(status3.movingStatus()).isEqualTo(MovingStatus.SUCCESS);
        assertThat(status4.movingStatus()).isEqualTo(MovingStatus.SUCCESS);
        assertThat(status5.movingStatus()).isEqualTo(MovingStatus.FAIL);

        MoveResult status6 = bridgeGame.move(MoveCommand.U);
        MoveResult status7 = bridgeGame.move(MoveCommand.U);
        MoveResult status8 = bridgeGame.move(MoveCommand.D);

        assertThat(status6.movingStatus()).isEqualTo(MovingStatus.SUCCESS);
        assertThat(status7.movingStatus()).isEqualTo(MovingStatus.SUCCESS);
        assertThat(status8.movingStatus()).isEqualTo(MovingStatus.SUCCESS);

        BridgeGameStatus finalStatus = bridgeGame.getStatus();
        assertThat(finalStatus).isEqualTo(BridgeGameStatus.FINISH);
    }
}