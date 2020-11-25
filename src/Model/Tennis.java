//318247822
package Model;

import Exception.ProblamResultException;
import Exception.NegativResultException;

public class Tennis extends Game {

	private final int MAX_SET_ALLOWD = 5;
	private final int MIN_NUM_OF_SET_TO_WIN = 3;
	private final int SET_DIFRENCE_TO_WIN = 3;
	private final int POINT_FOR_GAME_WIN = 6;
	private final int POINT_FOR_GAME_WIN_IF_IN_5 = 7;// by tennis rulse if both player reach 5 points each one player
														// need to get 7 points to winalso is max result that is
														// possible
	private final int POINT_FOR_7_POINTS_GAME = 5;
	private int firstPlayerSetWon;
	private int secandPlayerSetWon;
	private static final String SUB_GAME_NAME = "Set";

	public Tennis(String firstPlayer, String secandPlayer) {

		super(firstPlayer, secandPlayer, SUB_GAME_NAME);
	}

	@Override
	public boolean playHalf(int firstPlayer, int secandPlayer) throws Exception {
		unresnableResult(firstPlayer, secandPlayer);
		setWin(firstPlayer, secandPlayer);

		if (numOfHalfTimes >= MIN_NUM_OF_SET_TO_WIN) {
			if ((firstPlayerSetWon - secandPlayerSetWon) == SET_DIFRENCE_TO_WIN) {
				winner = firstPlayerName;
				gameEnded = true;
				return gameEnded;
			}
			if ((secandPlayerSetWon - firstPlayerSetWon) == SET_DIFRENCE_TO_WIN) {
				winner = secandPlayerName;
				gameEnded = true;
				return gameEnded;
			}
		}
		if (numOfHalfTimes == MAX_SET_ALLOWD) {//in case of one player has 3 and other 2 wins
			if ((firstPlayerSetWon - secandPlayerSetWon) > 0) {
				winner = firstPlayerName;
				gameEnded = true;
				return gameEnded;
			}
			if ((secandPlayerSetWon - firstPlayerSetWon) > 0) {
				winner = secandPlayerName;
				gameEnded = true;
				return gameEnded;
			}
		}
		numOfHalfTimes++;
		return gameEnded;
	}

	private void unresnableResult(int firstPlayer, int secandPlayer) throws Exception {
		if (firstPlayer > POINT_FOR_GAME_WIN_IF_IN_5 || secandPlayer > POINT_FOR_GAME_WIN_IF_IN_5)// max result possible
																									// is passed
			throw new ProblamResultException("Max result possible is 7");
		if (firstPlayer < 0 || secandPlayer < 0)
			throw new NegativResultException("result cant be negative");
		if (firstPlayer == POINT_FOR_GAME_WIN && secandPlayer == POINT_FOR_7_POINTS_GAME
				|| secandPlayer == POINT_FOR_GAME_WIN && firstPlayer == POINT_FOR_7_POINTS_GAME)
			throw new ProblamResultException("If 1 player has 5 points the secand player need 7 points to win");
		if (firstPlayer == POINT_FOR_GAME_WIN_IF_IN_5 && secandPlayer < POINT_FOR_7_POINTS_GAME
				|| secandPlayer == POINT_FOR_GAME_WIN_IF_IN_5 && firstPlayer < POINT_FOR_7_POINTS_GAME)
			throw new ProblamResultException(
					"If one player has 7 points the other player must have 5 point other wise isnt correct by game rulse");
		if (firstPlayer < POINT_FOR_GAME_WIN && secandPlayer < POINT_FOR_GAME_WIN)
			throw new ProblamResultException(
					"Enter difrent result one player must win! to win one player must reach 6 point while the other has less then 5 points or 7 points if the other player has 5 points by tennis game rulse!");
		if (firstPlayer == secandPlayer)
			throw new ProblamResultException("A set cant end in tie by tennis rulse!");
	}

	private void setWin(int firstPlayer, int secandPlayer) {
		if (firstPlayer == POINT_FOR_GAME_WIN && secandPlayer < POINT_FOR_7_POINTS_GAME
				|| firstPlayer == POINT_FOR_GAME_WIN_IF_IN_5
						&& (secandPlayer == POINT_FOR_7_POINTS_GAME || secandPlayer == POINT_FOR_GAME_WIN)) {
			firstPlayerSetWon++;
		}
		if (secandPlayer == POINT_FOR_GAME_WIN && firstPlayer < POINT_FOR_7_POINTS_GAME
				|| secandPlayer == POINT_FOR_GAME_WIN_IF_IN_5
						&& (firstPlayer == POINT_FOR_7_POINTS_GAME || firstPlayer == POINT_FOR_GAME_WIN)) {
			secandPlayerSetWon++;
		}
	}

	@Override
	protected void setInstruction() {
		instructions = "you enter the result of each set each set is played intl one player reach 6 points first or in case of the other player has 5 point then the other player must have 7 points to win a set you play intl you have 3 set win diffrens all by tennis rulse";
	}

}
