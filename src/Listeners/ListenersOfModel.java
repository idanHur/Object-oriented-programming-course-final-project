//318247822
package Listeners;

import Model.Championship.GameType;

public interface ListenersOfModel {
	void modleUpdateLableOfInGame(String msg);

	void modleUpdateUiWinnerOfRound(String msg, int position, String whichFinal);

	void modleStartedChampionship(GameType gameType);

	void modleAddedPlayer(String name, int positionInVector);

	void modleHalfPlayed();

	void modleStartedGame(String firstPlayer, String secandPlayer, String instruction, String subGameName);
}
