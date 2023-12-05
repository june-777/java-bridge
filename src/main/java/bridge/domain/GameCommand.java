package bridge.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// TODO: 직관적인 이름으로 고민할 것
public enum GameCommand {

    Q, R;

    private static final Map<String, GameCommand> cachedGameCommand = new HashMap<>();

    static {
        for (GameCommand gameCommand : values()) {
            cachedGameCommand.put(gameCommand.name(), gameCommand);
        }
    }

    public static GameCommand of(String command) {
        return Optional.ofNullable(cachedGameCommand.get(command))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게임 명령입니다."));
    }
}
