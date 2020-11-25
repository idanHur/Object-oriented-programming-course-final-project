package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WhileInGameViewMessegesView {

	private Label lblMessages;
	private VBox vRoot;
	private Button btnClose;

	public WhileInGameViewMessegesView(Stage stage, Label lable) {
		stage.setTitle("Message");
		lblMessages = lable;
		btnClose = new Button("Close");
		btnClose.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
		});
		vRoot = new VBox();
		vRoot.getChildren().addAll(lblMessages, btnClose);
		vRoot.setAlignment(Pos.CENTER);
		vRoot.setPadding(new Insets(20));
		vRoot.setSpacing(20);
		stage.setScene(new Scene(vRoot, 500, 100));
		stage.setResizable(false);
		stage.show();

	}

}
