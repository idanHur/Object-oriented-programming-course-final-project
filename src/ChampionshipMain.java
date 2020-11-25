
//318247822
import Controller.ChampionshipController;
import Model.Championship;
import View.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class ChampionshipMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Championship theModle = new Championship();
		MainView theView = new MainView(primaryStage);
		ChampionshipController controller = new ChampionshipController(theModle, theView);

	}

}
