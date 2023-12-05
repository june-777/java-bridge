package bridge.domain.moving;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum MoveCommand {

    U("U"),
    D("D");

    private static final Map<String, MoveCommand> cachedMoveCommand = new HashMap<>();
    private final String command;

    static {
        for (MoveCommand moveCommand : values()) {
            cachedMoveCommand.put(moveCommand.command, moveCommand);
        }
    }

    MoveCommand(String command) {
        this.command = command;
    }

    public static MoveCommand of(String command) {
        return Optional.ofNullable(cachedMoveCommand.get(command))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 이동입니다."));
    }

    public String getCommand() {
        return command;
    }
}
