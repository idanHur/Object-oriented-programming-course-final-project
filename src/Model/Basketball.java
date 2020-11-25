//318247822
package Model;

import Exception.GameEndAndTieException;
import Exception.NegativResultException;

public class Basketball extends Game {

	private final int MAX_NUM_OF_HALF = 4;
	private static final String SUB_GAME_NAME = "Quarter";

	public Basketball(String firstPlayer, String secandPlayer) {
		super(firstPlayer, secandPlayer, SUB_GAME_NAME);
	}

	@Override
	public boolean playHalf(int firstPlayer, int secandPlayer) throws Exception {
		if (firstPlayer < 0 || secandPlayer < 0)
			throw new NegativResultException("Half result must be positiv");// negativ result exception

		firstPlayerScore += firstPlayer;
		secandPlayerScore += secandPlayer;
		if (numOfHalfTimes == MAX_NUM_OF_HALF) {
			if (firstPlayerScore == secandPlayerScore) {
				firstPlayerScore -= firstPlayer;
				secandPlayerScore -= secandPlayer;
				throw new GameEndAndTieException(
						"The game result isnt corect cant be tie enter difrent result for 4th quarter");
			}
			if (firstPlayerScore > secandPlayerScore) {// player 1 won
				winner = firstPlayerName;
				gameEnded = true;
				return gameEnded;
			}
			if (firstPlayerScore < secandPlayerScore) {// player 2 won
				winner = secandPlayerName;
				gameEnded = true;
				return gameEnded;
			}
		}
		numOfHalfTimes++;
		return gameEnded;
	}
	@Override
	protected void setInstruction() {
		instructions = "You play 4 quarters and there cant be a tie by the basketball rulse";
	}
}
