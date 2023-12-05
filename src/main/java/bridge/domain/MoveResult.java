package bridge.domain;

public record MoveResult(MovingStatus movingStatus, int currentPosition, int tryCount) {
}
