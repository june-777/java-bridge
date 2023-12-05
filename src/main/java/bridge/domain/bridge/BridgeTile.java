package bridge.domain.bridge;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum BridgeTile {
    U(1),
    D(0);

    private static final Map<Integer, BridgeTile> cachedTile = new HashMap<>();

    static {
        for (BridgeTile tile : values()) {
            cachedTile.put(tile.tileNumber, tile);
        }
    }

    private final int tileNumber;

    BridgeTile(int tileNumber) {
        this.tileNumber = tileNumber;
    }

    public static String of(int tileNumber) {
        BridgeTile bridgeTile = Optional.ofNullable(cachedTile.get(tileNumber))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 타일 번호입니다."));

        return bridgeTile.name();
    }
}
