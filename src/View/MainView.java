//318247822
package View;

import java.util.Vector;

import Listeners.ListenersOfUi;
import Model.Championship.GameType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainView {

	private Vector<TextField> tfParticipante;
	private RadioButton rdoTennis;
	private RadioButton rdoBasketball;
	private RadioButton rdoSoccer;
	private Label lblParticipanteName;
	private Label lblChampionship;
	private Label lblMessages;
	private TextField tfName;
	private Button btnAdd;
	private Button btnStart;
	private VBox vbRootParticipante;
	private VBox vbRootSports;
	private VBox vbCenter;
	private BorderPane bpRoot;
	private GridPane gpRoot;
	private Vector<ListenersOfUi> listeners;
	private GameType eType;
	private GameView theGameView;
	private GameResultView theResultView;
	private DropShadow dropShadow;

	public MainView(Stage theStage) {
		theStage.setTitle("Championship");
		listeners = new Vector<>();
		bpRoot = new BorderPane();
		rdoBasketball = new RadioButton("Basketball");
		rdoSoccer = new RadioButton("Soccer");
		rdoTennis = new RadioButton("Tennis");
		ToggleGroup sports = new ToggleGroup();
		rdoBasketball.setToggleGroup(sports);
		rdoSoccer.setToggleGroup(sports);
		rdoTennis.setToggleGroup(sports);
		tfParticipante = new Vector<>();
		vbRootSports = new VBox();
		vbRootSports.setSpacing(5);
		vbRootSports.getChildren().addAll(rdoBasketball, rdoSoccer, rdoTennis);
		bpRoot.setRight(vbRootSports);
		lblParticipanteName = new Label("Participant name: ");
		tfName = new TextField();
		lblMessages = new Label("");
		btnAdd = new Button("Add participant");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!tfName.getText().isEmpty()) {
					for (ListenersOfUi l : listeners)
						l.uiAddedPlayer(tfName.getText());
					tfName.clear();
				} else {
					lblMessages.setText("You must enter a name before you press the add butten!");
				}
			}
		});
		btnStart = new Button("Start Championship");
		btnStart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (sports.getSelectedToggle() != null) {
					for (ListenersOfUi l : listeners)
						l.uiStartedChampionship(((RadioButton) sports.getSelectedToggle()).getText());
				} else {
					lblMessages.setText("Choose which sport you want to play first");
				}
			}
		});
		vbCenter = new VBox();
		vbRootParticipante = new VBox();
		makeTfForParticpent(tfParticipante, vbRootParticipante);
		vbRootParticipante.setSpacing(20);
		vbRootParticipante.setPadding(new Insets(30));
		gpRoot = new GridPane();
		gpRoot.setVgap(20);
		gpRoot.setHgap(20);
		gpRoot.setPadding(new Insets(20));
		gpRoot.add(lblParticipanteName, 0, 0);
		gpRoot.add(tfName, 1, 0);
		gpRoot.add(btnAdd, 0, 1);
		gpRoot.add(btnStart, 1, 1);
		vbCenter.getChildren().addAll(gpRoot, lblMessages);
		vbCenter.setAlignment(Pos.CENTER);
		bpRoot.setCenter(vbCenter);
		bpRoot.setLeft(vbRootParticipante);
		dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
		lblChampionship = new Label("Championship");
		lblChampionship.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		lblChampionship.setTextFill(Color.web("0x3b596d"));
		lblChampionship.setEffect(dropShadow);
		lblChampionship.setCache(true);
		bpRoot.setTop(lblChampionship);
		bpRoot.setAlignment(lblChampionship, Pos.TOP_CENTER);
		gpRoot.setAlignment(Pos.CENTER);
		vbRootSports.setAlignment(Pos.CENTER_LEFT);
		vbRootSports.setPadding(new Insets(40));
		vbRootParticipante.setAlignment(Pos.CENTER);
		theStage.setScene(new Scene(bpRoot, 1500, 750));
		theStage.setResizable(false);
		theStage.show();
	}

	public void registerListeners(ListenersOfUi listiner) {
		listeners.add(listiner);
	}

	public void addPlayer(String name, int placeInVector) {
		tfParticipante.get(placeInVector).setText(name);
	}

	public void updateMessageLable(String msg) {
		lblMessages.setText(msg);
	}
	public void messegesWhileInGameView(String str) {
		lblMessages.setText(str);
		Stage messegesStage = new Stage();
		WhileInGameViewMessegesView messegesView = new WhileInGameViewMessegesView(messegesStage, lblMessages);
	}

	public void startChampionship(GameType gameType) {
		eType = gameType;
		bpRoot.setRight(null);
		bpRoot.setCenter(null);
		bpRoot.setLeft(null);
		theGameView = new GameView(bpRoot, tfParticipante, this);
	}

	public void playGame(int positionForWinner, String gameType) {
		for (ListenersOfUi l : listeners)
			l.uiPlayGame(positionForWinner, gameType);
	}

	public void gameStarted(String firstPlayer, String secandPlayer, String instruction, String subGameName) {
		Stage secandry = new Stage();
		theResultView = new GameResultView(secandry, firstPlayer, secandPlayer, instruction, this, lblMessages,
				subGameName);
	}

	public void playHalf(int firstPlayer, int secandPlayer) {// view tell result of half to modle
		for (ListenersOfUi l : listeners)
			l.uiPlayHalf(firstPlayer, secandPlayer);
	}

	public void halfPlayed() {// modle tell view that half is played
		theResultView.addHalf();
	}

	public void winnerOfRound(String name, int position, String whichFinal) {// game round has winner
		theResultView.gameEnded();
		theGameView.gameWinner(name, position, whichFinal);
	}

	private void makeTfForParticpent(Vector<TextField> playersTf, VBox root) {
		for (int i = 0; i < 8; i++) {
			tfParticipante.add(new TextField());
			tfParticipante.get(tfParticipante.size() - 1).setEditable(false);
			vbRootParticipante.getChildren().add(tfParticipante.get(tfParticipante.size() - 1));
		}
	}

	public DropShadow getShadow() {
		return dropShadow;
	}

	public GameType getCurrentGameType() {
		return eType;
	}
}
