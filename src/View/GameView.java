//318247822
package View;

import java.util.Vector;

import Model.Championship.WhichFinal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class GameView {

	private Vector<Button> btnQuarterFinal;
	private Vector<Button> btnSemiFinal;
	private Button btnFinal;
	private Vector<TextField> tfStartPlayers;
	private Vector<TextField> tfQuarterFinal;
	private Vector<TextField> tfSemiFinal;
	private TextField tfFinal;
	private VBox vRootStartTf;
	private VBox vRootQuarterFinalBtn;
	private VBox vRootQuarterFinalTf;
	private VBox vRootSemiFinalBtn;
	private VBox vRootSemiFinalTf;
	private HBox hRoot;
	private BorderPane borderPain;
	private MainView mainView;
	private Vector<Line> lines;

	public GameView(BorderPane bPain, Vector<TextField> tfStartPlayers, MainView mainViewListner) {
		mainView = mainViewListner;
		borderPain = bPain;
		this.tfStartPlayers = tfStartPlayers;
		vRootStartTf = new VBox();
		vRootQuarterFinalBtn = new VBox();
		vRootQuarterFinalTf = new VBox();
		vRootSemiFinalBtn = new VBox();
		vRootSemiFinalTf = new VBox();
		hRoot = new HBox();
		lines = new Vector<>();
		tfSemiFinal = new Vector<>();
		tfQuarterFinal = new Vector<>();
		btnSemiFinal = new Vector<>();
		btnQuarterFinal = new Vector<>();
		btnFinal = new Button("Play a game");
		makeBtn(btnQuarterFinal, 4);
		makeBtn(btnSemiFinal, 2);
		setActionForBtn();
		makeTf(tfQuarterFinal, 4);
		makeTf(tfSemiFinal, 2);
		tfFinal = new TextField();
		tfFinal.setEditable(false);
		tfFinal.setAlignment(Pos.CENTER_LEFT);
		btnFinal.setAlignment(Pos.CENTER_LEFT);
		makeLines();
		enterToVbox(this.tfStartPlayers, vRootStartTf);
		enterToVbox(btnQuarterFinal, vRootQuarterFinalBtn);
		enterToVbox(tfQuarterFinal, vRootQuarterFinalTf);
		enterToVbox(btnSemiFinal, vRootSemiFinalBtn);
		enterToVbox(tfSemiFinal, vRootSemiFinalTf);
		positioningVbox(25, 5, vRootStartTf);
		positioningVbox(81, 15, vRootQuarterFinalBtn);
		positioningVbox(81, 15, vRootQuarterFinalTf);
		positioningVbox(195, 25, vRootSemiFinalBtn);
		positioningVbox(195, 25, vRootSemiFinalTf);
		hRoot.setPadding(new Insets(15));
		hRoot.setSpacing(25);
		hRoot.getChildren().addAll(vRootStartTf, vRootQuarterFinalBtn, vRootQuarterFinalTf, vRootSemiFinalBtn,
				vRootSemiFinalTf, btnFinal, tfFinal);
		hRoot.setAlignment(Pos.CENTER_LEFT);
		borderPain.setCenter(hRoot);
	}

	private void makeBtn(Vector<Button> buttons, int quantety) {
		for (int i = 0; i < quantety; i++) {
			buttons.add(new Button("Play a game"));
		}
	}

	private void makeTf(Vector<TextField> players, int quantety) {
		for (int i = 0; i < quantety; i++) {
			players.add(new TextField());
			players.get(i).setEditable(false);
		}
	}

	private void enterToVbox(Vector<? extends Node> nodeVector, VBox root) {// add lables or buttens to vBox
		for (int i = 0; i < nodeVector.size(); i++) {
			root.getChildren().add(nodeVector.get(i));
		}
	}

	private void positioningVbox(int spaceing, int padding, VBox root) {
		root.setAlignment(Pos.CENTER_LEFT);
		root.setSpacing(spaceing);
		root.setPadding(new Insets(padding));
	}

	public void gameWinner(String name, int position, String whichFinal) {
		if (whichFinal.equalsIgnoreCase(WhichFinal.Quarter.toString())) {
			tfQuarterFinal.get(position).setText(name);
			;
		}
		if (whichFinal.equalsIgnoreCase(WhichFinal.Semi.toString())) {
			tfSemiFinal.get(position).setText(name);
		}
		if (whichFinal.equalsIgnoreCase(WhichFinal.Final.toString())) {
			tfFinal.setText(name);
		}
	}

	private void setActionForBtn() {
		for (int i = 0; i < btnQuarterFinal.size(); i++) {
			eventPressGame(i, WhichFinal.Quarter, btnQuarterFinal.get(i));
		}
		for (int i = 0; i < btnSemiFinal.size(); i++) {
			eventPressGame(i, WhichFinal.Semi, btnSemiFinal.get(i));
		}
		eventPressGame(0, WhichFinal.Final, btnFinal);
	}

	private void eventPressGame(int positionInVector, WhichFinal finalType, Button btn) {
		EventHandler<ActionEvent> pressGame = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainView.playGame(positionInVector, finalType.toString());
			}
		};
		btn.setOnAction(pressGame);
	}

	private void makeLines() {
		int initalStartPosX = 200;
		int initalStartPosY = 217;
		int initalFinalPosX = 300;
		int initalFinalPosY = 235;
		for (int i = 0; i < 8; i++) {
			lines.add(new Line(initalStartPosX, initalStartPosY, initalFinalPosX, initalStartPosY));
			initalStartPosY += 56;
		}
		initalStartPosY = 218;
		initalFinalPosY = 272;
		for (int i = 0; i < 4; i++) {
			lines.add(new Line(initalFinalPosX, initalStartPosY, initalFinalPosX, initalFinalPosY));
			initalStartPosY += 112;
			initalFinalPosY += 112;
		}
		initalStartPosY = 245;
		initalStartPosX = 353;
		initalFinalPosX = 710;
		for (int i = 0; i < 4; i++) {
			lines.add(new Line(initalStartPosX, initalStartPosY, initalFinalPosX, initalStartPosY));
			initalStartPosY += 112;
		}
		initalStartPosY = 245;
		initalFinalPosY = initalStartPosY + 112;
		for (int i = 0; i < 2; i++) {
			lines.add(new Line(initalFinalPosX, initalStartPosY, initalFinalPosX, initalFinalPosY));
			initalFinalPosY += 224;
			initalStartPosY += 224;
		}
		initalStartPosX = initalFinalPosX + 51;
		initalFinalPosX = initalStartPosX + 359;
		initalStartPosY = 301;
		for (int i = 0; i < 2; i++) {
			lines.add(new Line(initalStartPosX, initalStartPosY, initalFinalPosX, initalStartPosY));
			initalStartPosY += 226;
		}
		initalStartPosY = 301;
		initalFinalPosY = initalStartPosY + 225;
		lines.add(new Line(initalFinalPosX, initalStartPosY, initalFinalPosX, initalFinalPosY));
		initalStartPosY = 415;
		initalStartPosX += 330;
		initalFinalPosX += 160;
		lines.add(new Line(initalStartPosX, initalStartPosY, initalFinalPosX, initalStartPosY));
		for (int i = 0; i < lines.size(); i++) {
			borderPain.getChildren().add(lines.get(i));
		}
	}
}
