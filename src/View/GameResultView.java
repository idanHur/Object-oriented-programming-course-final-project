//318247822
package View;

import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameResultView {

	private BorderPane bpRoot;
	private HBox hbRootFirstPlayer;
	private HBox hbRootSecandePlayer;
	private VBox vbRootPlayersName;
	private VBox vbRootPlayersResult;
	private Label lblFirstPlayer;
	private Label lblSecandPlayer;
	private Label lblGameType;
	private Label lblMessages;
	private Vector<TextField> tfFirstPlayerResult;
	private Vector<TextField> tfSecandPlayerResult;
	private StackPane spRootBtn;
	private Button btnDone;
	private Button btnPlayHalf;
	private MainView litsnereOfMainView;
	private String instructions;

	public GameResultView(Stage secandStage, String firstPlayer, String secandPlayer, String instructions,
			MainView litener, Label lblMessage, String subGameName) {
		this.instructions = instructions;
		litsnereOfMainView = litener;
		lblFirstPlayer = new Label(firstPlayer);
		lblSecandPlayer = new Label(secandPlayer);
		lblFirstPlayer.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		lblSecandPlayer.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		lblMessages = lblMessage;
		lblMessages.setText(instructions);
		lblMessages.setWrapText(true);
		lblGameType = new Label(litsnereOfMainView.getCurrentGameType().toString() + " Game");
		lblGameType.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		lblGameType.setTextFill(Color.web("0x3b596d"));
		lblGameType.setEffect(litsnereOfMainView.getShadow());
		tfFirstPlayerResult = new Vector<>();
		tfSecandPlayerResult = new Vector<>();
		btnDone = new Button("Done");
		btnDone.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				secandStage.close();
			}
		});
		btnDone.setVisible(false);// dont show intl game end
		btnPlayHalf = new Button("Play " + subGameName);
		btnPlayHalf.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (cheakInputofTf(tfFirstPlayerResult.get(tfFirstPlayerResult.size() - 1).getText(),
						tfSecandPlayerResult.get(tfSecandPlayerResult.size() - 1).getText())) {
					litsnereOfMainView.playHalf(
							stringToInt(tfFirstPlayerResult.get(tfFirstPlayerResult.size() - 1).getText()),
							stringToInt(tfSecandPlayerResult.get(tfSecandPlayerResult.size() - 1).getText()));
				}
			}
		});
		spRootBtn = new StackPane();
		spRootBtn.getChildren().addAll(btnDone, btnPlayHalf);
		hbRootFirstPlayer = new HBox();
		hbRootSecandePlayer = new HBox();
		hbRootFirstPlayer.setSpacing(20);
		hbRootSecandePlayer.setSpacing(20);
		addHalf();
		vbRootPlayersResult = new VBox();
		vbRootPlayersResult.getChildren().addAll(hbRootFirstPlayer, hbRootSecandePlayer, lblMessages);
		vbRootPlayersResult.setSpacing(30);
		vbRootPlayersResult.setPadding(new Insets(37));
		vbRootPlayersName = new VBox();
		vbRootPlayersName.getChildren().addAll(lblFirstPlayer, lblSecandPlayer);
		vbRootPlayersName.setSpacing(35);
		vbRootPlayersName.setPadding(new Insets(40));
		bpRoot = new BorderPane();
		spRootBtn.setPadding(new Insets(20));
		bpRoot.setTop(lblGameType);
		bpRoot.setAlignment(lblGameType, Pos.TOP_CENTER);
		bpRoot.setLeft(vbRootPlayersName);
		bpRoot.setAlignment(vbRootPlayersName, Pos.CENTER_LEFT);
		bpRoot.setCenter(vbRootPlayersResult);
		bpRoot.setBottom(spRootBtn);
		bpRoot.setAlignment(spRootBtn, Pos.BOTTOM_CENTER);
		secandStage.setScene(new Scene(bpRoot, 1000, 340));
		secandStage.setResizable(false);
		secandStage.show();
	}

	public void addHalf() {
		if (tfFirstPlayerResult.size() != 0 && tfSecandPlayerResult.size() != 0) {
			tfFirstPlayerResult.get(tfFirstPlayerResult.size() - 1).setEditable(false);
			tfSecandPlayerResult.get(tfSecandPlayerResult.size() - 1).setEditable(false);
		}
		lblMessages.setText(instructions);
		TextField temp = new TextField();
		tfFirstPlayerResult.add(temp);
		hbRootFirstPlayer.getChildren().add(temp);
		TextField temp2 = new TextField();
		tfSecandPlayerResult.add(temp2);
		hbRootSecandePlayer.getChildren().add(temp2);
	}

	private boolean cheakInputofTf(String firstPlayer, String secandPlayer) {
		if (!firstPlayer.isEmpty() && !secandPlayer.isEmpty()) {
			String num = "[0-9]+";
			if (firstPlayer.matches(num) && secandPlayer.matches(num)) {
				return true;
			} else {
				lblMessages.setText("The result mustbe only numbers!");
			}
		} else {
			lblMessages.setText("Please enter results first!");
		}
		return false;
	}

	private int stringToInt(String str) {
		return Integer.parseInt(str);
	}

	public void gameEnded() {
		btnPlayHalf.setVisible(false);
		btnDone.setVisible(true);
	}

}
