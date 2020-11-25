//318247822
package Listeners;

public interface ListenersOfUi {
	void uiAddedPlayer(String name);

	void uiPlayGame(int positionForWinner, String whichFinal);

	void uiPlayHalf(int firstPlayer, int secandPlayer);

	void uiStartedChampionship(String gameType);
}
