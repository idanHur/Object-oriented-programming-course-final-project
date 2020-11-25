//318247822
package Model;

public abstract class Game {

	protected String winner;
	protected String firstPlayerName;
	protected int firstPlayerScore;
	protected String secandPlayerName;
	protected int secandPlayerScore;
	protected int numOfHalfTimes;
	protected boolean gameEnded;
	protected String subGameName;
	protected String instructions;

	public Game(String firstPlayer, String secandPlayer, String nameOfSubGame) {
		gameEnded = false;
		firstPlayerName = firstPlayer;
		secandPlayerName = secandPlayer;
		numOfHalfTimes = 1;
		subGameName = nameOfSubGame;
		setInstruction();
	}

	public abstract boolean playHalf(int firstPlayer, int secandPlayer) throws Exception;

	public boolean isGameEnded() {
		return gameEnded;
	}

	public String getWinner() {
		return winner;
	}

	protected abstract void setInstruction();

	public String getInstructions() {
		return instructions;
	}
}
