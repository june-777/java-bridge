package view;

import static view.OutputViewConstants.*;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

	private static String currentGameStateUpMap = OUTPUT_TEXT_OPEN_SQUARE_BRACKETS;
	private static String currentGameStarteDownMap = OUTPUT_TEXT_OPEN_SQUARE_BRACKETS;

	/**
	 * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
	 * <p>
	 * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
	 */
	public void printMap(String moving, int movingResult) {
		updateGameStateMapVerticalBar();
		String currentGameStateMap = combineGameStateUpMapAndDownMap();
	}

	private String combineGameStateUpMapAndDownMap() {
		StringBuilder sb = new StringBuilder();
		String currentGameStateMap = sb.append(currentGameStateUpMap).append("\r\n").append(currentGameStarteDownMap)
				.toString();
		return currentGameStateMap;
	}

	private void updateGameStateMapVerticalBar() {
		currentGameStateUpMap = currentGameStateUpMap.replace(OUTPUT_TEXT_CLOSE_SQUARE_BRACKETS,
				OUTPUT_TEXT_VERTICAL_BAR);
		currentGameStarteDownMap = currentGameStarteDownMap.replace(OUTPUT_TEXT_CLOSE_SQUARE_BRACKETS,
				OUTPUT_TEXT_VERTICAL_BAR);
	}
	
	/**
	 * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
	 * <p>
	 * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
	 */
	public void printResult() {
	}
}
