//318247822
package Controller;

import Listeners.ListenersOfModel;
import Listeners.ListenersOfUi;
import Model.Championship;
import Model.Championship.GameType;
import View.MainView;

public class ChampionshipController implements ListenersOfModel, ListenersOfUi {

	private MainView theView;
	private Championship theModle;

	public ChampionshipController(Championship modle, MainView view) {
		theModle = modle;
		theView = view;

		theModle.registerListener(this);
		theView.registerListeners(this);
	}

	@Override
	public void uiAddedPlayer(String name) {
		try {
			theModle.addPlayer(name);
		} catch (Exception e) {
			theView.updateMessageLable(e.getMessage());
		}
	}

	@Override
	public void uiPlayGame(int positionForWinner, String whichFinal) {
		try {
			theModle.startNewGame(positionForWinner, whichFinal);
		} catch (Exception e) {
			theView.messegesWhileInGameView(e.getMessage());
		}
	}

	@Override
	public void uiPlayHalf(int firstPlayer, int secandPlayer) {
		try {
			theModle.playHalf(firstPlayer, secandPlayer);
		} catch (Exception e) {
			theView.updateMessageLable(e.getMessage());
		}
	}

	@Override
	public void uiStartedChampionship(String gameType) {
		theModle.startChampionship(gameType);
	}

	@Override
	public void modleUpdateLableOfInGame(String msg) {
		theView.updateMessageLable(msg);
	}

	@Override
	public void modleUpdateUiWinnerOfRound(String msg, int position, String whichFinal) {
		theView.winnerOfRound(msg, position, whichFinal);
	}

	@Override
	public void modleStartedChampionship(GameType gameType) {
		theView.startChampionship(gameType);
	}

	@Override
	public void modleAddedPlayer(String name, int positionInVector) {
		theView.addPlayer(name, positionInVector);
	}

	@Override
	public void modleHalfPlayed() {
		theView.halfPlayed();
	}

	@Override
	public void modleStartedGame(String firstPlayer, String secandPlayer, String instruction, String subGameName) {
		theView.gameStarted(firstPlayer, secandPlayer, instruction, subGameName);
	}
}
