//318247822
package Model;

import Exception.ProblamResultException;
import Exception.GameEndAndTieException;
import Exception.NegativResultException;

public class Soccer extends Game {
	private final int NUM_OF_HALF_TO_PENALTIES_KICK = 4;
	private final int MAX_NUM_OF_HALF = 3;
	private static final String SUB_GAME_NAME = "Half";
	private final int UNLIKLEY_RESULT = 20;// a result that dosnt make sense in this game

	public Soccer(String firstPlayerName, String secandPlayerName) {
		super(firstPlayerName, secandPlayerName, SUB_GAME_NAME);
	}

	@Override
	public boolean playHalf(int firstPlayer, int secandPlayer)
			throws NegativResultException, ProblamResultException, GameEndAndTieException {

		if (!gameEnded) {
			if (firstPlayer < 0 || secandPlayer < 0)
				throw new NegativResultException("half result must be positiv");// negativ result exception
			if (firstPlayer > UNLIKLEY_RESULT || secandPlayer > UNLIKLEY_RESULT)
				throw new ProblamResultException(
						"One of the players result you enterd dosnt seem likley for this game");
			firstPlayerScore += firstPlayer;
			secandPlayerScore += secandPlayer;
			numOfHalfTimes++;
			if (numOfHalfTimes >= MAX_NUM_OF_HALF) {
				if (numOfHalfTimes > NUM_OF_HALF_TO_PENALTIES_KICK)
					if (firstPlayerScore == secandPlayerScore) {
						firstPlayerScore -= firstPlayer;
						secandPlayerScore -= secandPlayer;
						numOfHalfTimes--;
						throw new GameEndAndTieException(
								"The game result isnt corect cant be tie after penalty kick enter difrent result");
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
			return gameEnded;
		}
		return gameEnded;
	}

	@Override
	protected void setInstruction() {
		instructions = "you play 2 halfs and in case of tie you have extra time (third half) and if still tie a penalty kick that cant end with a tie!";
	}

}
