package bridge.domain.moving;

public record MoveResult(MovingStatus movingStatus, int currentPosition, int tryCount) {
}
