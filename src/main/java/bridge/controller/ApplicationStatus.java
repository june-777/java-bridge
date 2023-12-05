package bridge.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ApplicationStatus {

    QUIT("Q"), RUNNING("R");

    private static final Map<String, ApplicationStatus> cachedGameCommand = new HashMap<>();
    private final String command;

    static {
        for (ApplicationStatus applicationStatus : values()) {
            cachedGameCommand.put(applicationStatus.command, applicationStatus);
        }
    }

    ApplicationStatus(String command) {
        this.command = command;
    }

    public static ApplicationStatus of(String command) {
        return Optional.ofNullable(cachedGameCommand.get(command))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게임 명령입니다."));
    }
}
