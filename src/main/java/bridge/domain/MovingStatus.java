package bridge.domain;

public enum MovingStatus {
    FAIL("실패"),
    SUCCESS("성공");

    private final String koreanText;

    MovingStatus(String koreanText) {
        this.koreanText = koreanText;
    }

    public String getKoreanText() {
        return koreanText;
    }
}
