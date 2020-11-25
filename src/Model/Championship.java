//318247822
package Model;

import java.util.Vector;
import Exception.ChampionshipEndedException;
import Exception.PlayGameException;
import Listeners.ListenersOfModel;

public class Championship {

	public enum GameType {
		Soccer, Tennis, Basketball
	};

	public enum WhichFinal {
		Quarter, Semi, Final
	};

	private final int MAX_PLAYERS = 8;
	private final int MIN_LENGTH_OF_NAME = 2;

	private String winner;
	private Vector<String> StartPlayers;
	private Vector<String> quarterFinalPlayers;
	private Vector<String> semiFinalPlayers;
	private Game curentGameInPlay;
	private boolean championshipFinished;
	private GameType eType;
	private WhichFinal eFinal;
	private int positionForWinner;
	private Vector<ListenersOfModel> listeners;

	public Championship() {
		listeners = new Vector<>();
		this.StartPlayers = new Vector<>();
		quarterFinalPlayers = new Vector<>(4, 0);
		for (int i = 0; i < quarterFinalPlayers.capacity(); i++) {
			quarterFinalPlayers.add("");
		}
		semiFinalPlayers = new Vector<>(2, 0);
		for (int i = 0; i < semiFinalPlayers.capacity(); i++) {
			semiFinalPlayers.add("");
		}
		winner = "";
		championshipFinished = false;
	}

	public void addPlayer(String name) throws Exception {
		if (StartPlayers.size() < MAX_PLAYERS && cheakNameIfEligble(name)) {
			StartPlayers.add(name);
			for (ListenersOfModel l : listeners) {
				l.modleAddedPlayer(name, (StartPlayers.size() - 1));
				;
			}
		} else {
			for (ListenersOfModel l : listeners)
				l.modleUpdateLableOfInGame("Max 8 players cant add more!");
		}
	}

	private boolean cheakNameIfEligble(String name) throws Exception {
		if (name.matches("[a-zA-Z]+") && name.length() >= MIN_LENGTH_OF_NAME) {
			return true;
		} else {
			throw new Exception("Name must contain only letters and be more then 2 letters");
		}
	}

	public void startChampionship(String type) {
		if (StartPlayers.size() == MAX_PLAYERS) {
			if (type.equalsIgnoreCase(GameType.Basketball.toString()))
				eType = GameType.Basketball;
			if (type.equalsIgnoreCase(GameType.Soccer.toString()))
				eType = GameType.Soccer;
			if (type.equalsIgnoreCase(GameType.Tennis.toString()))
				eType = GameType.Tennis;
			for (ListenersOfModel l : listeners)
				l.modleStartedChampionship(eType);
		} else {
			for (ListenersOfModel l : listeners)
				l.modleUpdateLableOfInGame("You must enter all 8 players first!");
		}
	}

	public void startNewGame(int positionForWinner, String whichFinal)
			throws PlayGameException, ChampionshipEndedException {
		if (!championshipFinished) {
			if (canStartThisGame(positionForWinner, whichFinal)) {
				this.positionForWinner = positionForWinner;
				if (whichFinal.equalsIgnoreCase(WhichFinal.Quarter.toString())) {
					eFinal = WhichFinal.Quarter;
					makeGameByGameType(StartPlayers.get(positionForWinner * 2),
							StartPlayers.get((positionForWinner * 2) + 1));
				}
				if (whichFinal.equalsIgnoreCase(WhichFinal.Semi.toString())) {
					eFinal = WhichFinal.Semi;
					makeGameByGameType(quarterFinalPlayers.get((positionForWinner * 2)),
							quarterFinalPlayers.get((positionForWinner * 2) + 1));
				}
				if (whichFinal.equalsIgnoreCase(WhichFinal.Final.toString())) {
					eFinal = WhichFinal.Final;
					makeGameByGameType(semiFinalPlayers.get(0), semiFinalPlayers.get(1));
				}
			} else {
				throw new PlayGameException("you must play the last final to have players who won to play this one!");
			}
		}
		if (championshipFinished) {
			throw new ChampionshipEndedException("The championship has ended " + winner + " already won");
		}
	}

	private boolean canStartThisGame(int positionForWinner, String whichFinal) throws PlayGameException {
		if (whichFinal.equalsIgnoreCase(WhichFinal.Quarter.toString())) {
			if (quarterFinalPlayers.get(positionForWinner).isEmpty()) {
				if (!StartPlayers.get((positionForWinner * 2) + 1).isEmpty()
						&& !StartPlayers.get((positionForWinner * 2)).isEmpty())
					return true;
			} else {
				GamePlayedExp();
			}
		}
		if (whichFinal.equalsIgnoreCase(WhichFinal.Semi.toString())) {
			if (semiFinalPlayers.get(positionForWinner).isEmpty()) {
				if (!quarterFinalPlayers.get((positionForWinner * 2) + 1).isEmpty()
						&& !quarterFinalPlayers.get((positionForWinner * 2)).isEmpty())
					return true;
			} else {
				GamePlayedExp();
			}
		}
		if (whichFinal.equalsIgnoreCase(WhichFinal.Final.toString())) {
			if (winner.isEmpty())// alreay has exeption in startgame
				if (!semiFinalPlayers.get(0).isEmpty() && !semiFinalPlayers.get(1).isEmpty())
					return true;
		}
		return false;
	}

	private void GamePlayedExp() throws PlayGameException {
		throw new PlayGameException("Game already played");
	}

	private void gameHasStarted(String firstPlayer, String secandPlayer) {
		for (ListenersOfModel l : listeners)
			l.modleStartedGame(firstPlayer, secandPlayer, curentGameInPlay.getInstructions(),
					curentGameInPlay.subGameName);
	}

	private void makeGameByGameType(String firstPlayerName, String secandPlayerName) {

		if (eType == GameType.Basketball) {
			curentGameInPlay = new Basketball(firstPlayerName, secandPlayerName);

		}
		if (eType == GameType.Soccer) {
			curentGameInPlay = new Soccer(firstPlayerName, secandPlayerName);

		}
		if (eType == GameType.Tennis) {
			curentGameInPlay = new Tennis(firstPlayerName, secandPlayerName);
		}
		gameHasStarted(firstPlayerName, secandPlayerName);
	}

	public void playHalf(int firstPlayer, int secandPlayer) throws Exception {
		if (curentGameInPlay.playHalf(firstPlayer, secandPlayer)) {// enter only when all half end
			if (eFinal == WhichFinal.Quarter)
				quarterFinalPlayers.set(positionForWinner, curentGameInPlay.getWinner());
			if (eFinal == WhichFinal.Semi)
				semiFinalPlayers.set(positionForWinner, curentGameInPlay.getWinner());
			if (eFinal == WhichFinal.Final) {
				winner = curentGameInPlay.getWinner();
				championshipFinished = true;
			}
			for (ListenersOfModel l : listeners)
				l.modleUpdateUiWinnerOfRound(curentGameInPlay.winner, positionForWinner, eFinal.toString());
		}
		if (!curentGameInPlay.gameEnded) {
			for (ListenersOfModel l : listeners) {
				l.modleHalfPlayed();
			}
		}
	}

	public void registerListener(ListenersOfModel listener) {
		listeners.add(listener);
	}

	public void setSemiFinalPlayers(String semiFinalPlayer, int position) {
		semiFinalPlayers.set(position, semiFinalPlayer);
	}

}
