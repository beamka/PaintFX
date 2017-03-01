package main;

import java.util.Optional;

import controller.Body;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author IrynaTkachova
 *
 */

public class Main extends Application {

	private static Main instance;
	private Stage primaryStage;
	private BorderPane root;

	public static void main(String[] args) {
		launch(args);
	}

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.instance = this;
		this.primaryStage = primaryStage;
		initViews();
		primaryStage.setOnCloseRequest(e -> {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Exit IBT_Paint");
			alert.setHeaderText("Unsaved data will be lost.");
			alert.setContentText("Are you want to exit?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() != ButtonType.OK) {
				e.consume();
			}
		});
		primaryStage.show();
	}

	private void initViews() throws Exception {
		root = FXMLLoader.load(getClass().getResource("../view/head.fxml"));
		primaryStage.setTitle("IBT_Paint");
		primaryStage.setScene(new Scene(root, 1200, 800));
		FXMLLoader.load(getClass().getResource("../view/body.fxml"));
	}

	public void initNewBody() {
		primaryStage.setTitle("IBT_Paint - Untitled");
		root.setLeft(Body.getInstance().getCanvas());
		Body.getInstance().getGraphics().getSheet().clear();
	}

	public void exit() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Exit IBT_Paint");
		alert.setHeaderText("Unsaved data will be lost.");
		alert.setContentText("Are you want to exit?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			primaryStage.close();
		}
	}

	public BorderPane getRoot() {
		return root;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

}