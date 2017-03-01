package controller;

import java.util.List;
import java.util.Optional;

import engine.ContextGraphics;
import engine.SetState;
import figures.utils.IShape;
import figures.utils.constants.StaticStr;
import figures.utils.impl.RGBColor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.DataControl;
import main.Main;

/**
 * @author IrynaTkachova
 *
 */
public class Head {
	private static Head instance;
	@FXML
	private Button rectangleID;
	@FXML
	private Button triangleID;
	@FXML
	private Button circleID;
	@FXML
	private Button ellipseID;
	@FXML
	private Button lineID;
	@FXML
	private Button polygonID;
	@FXML
	private Spinner<Integer> countPointID;
	@FXML
	private ColorPicker colorPickerID;
	@FXML
	private Rectangle colorLineID;
	@FXML
	private Rectangle colorFillID;
	@FXML
	private Spinner<Integer> lineWidthID;
	@FXML
	private Label coordinates;
	@FXML
	private Label size_select;
	@FXML
	private Label size_canvas;
	@FXML
	private Label labelCenterID;
	@FXML
	private Button moveID;
	@FXML
	private Button resizeID;
	@FXML
	private Button upID;
	@FXML
	private Button downID;
	@FXML
	private Button deleteID;
	@FXML
	private Button combinedID;
	@FXML
	private Button inJoinID;
	@FXML
	private Button outJoinID;
	@FXML
	private Button clearID;
	@FXML
	private Button undoID;
	@FXML
	private Button redoID;
	@FXML
	private BorderPane BorderPaneID;

	private ContextGraphics graphics;

	public static Head getInstance() {
		return instance;
	}

	@FXML
	private void initialize() {
		System.out.println("Head controller initialize");
		instance = this;
		inJoinID.setDisable(true);
		outJoinID.setDisable(true);
		setAllButtonFlag(true);

		lineWidthID.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
		countPointID.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5));

		graphics = ContextGraphics.getInstance();

		lineWidthID.valueProperty().addListener(l -> {
			graphics.setLineWidth(lineWidthID.getValue());
		});
		countPointID.valueProperty().addListener(l -> {
			graphics.setCountPoint(countPointID.getValue());
		});
		colorPickerID.valueProperty().addListener(l -> {
			Color c = (Color) colorPickerID.getValue();
			double r = c.getRed();
			double g = c.getGreen();
			double b = c.getBlue();
			double opacity = c.getOpacity();
			RGBColor rgb = new RGBColor(r, g, b, opacity);
			// System.out.println("Color name = " +
			// colorPickerID.getValue().toString() + "R = " + r + " G = " + g
			// + " B = " + b + " O = " + opacity);
			graphics.setColor(rgb);

			rgb = graphics.getColorLine();
			Color colorl = new Color(rgb.R, rgb.G, rgb.B, rgb.opacity);
			colorLineID.setFill(colorl);
			rgb = graphics.getColorFill();
			Color colorf = new Color(rgb.R, rgb.G, rgb.B, rgb.opacity);
			colorFillID.setFill(colorf);
		});
	}

	@FXML
	private void colorLineClick() {
		graphics.setSetColorType(StaticStr.COLOR_LINE);
	}

	@FXML
	private void colorFillClick() {
		graphics.setSetColorType(StaticStr.COLOR_FILL);
	}

	@FXML
	private void newFile() {
		if (Main.getInstance().getRoot().getLeft().equals(Body.getInstance().getCanvas())) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("New File");
			alert.setHeaderText("New File");
			alert.setContentText(
					"Are you want to exit this file, and create a new file?\nUnsaved changes will be lost.");
			Optional<ButtonType> res = alert.showAndWait();
			if (res.get() != ButtonType.OK) {
				return;
			}
		}
		TextInputDialog dialog = new TextInputDialog("1000x500");
		dialog.setTitle("Select width and height");
		dialog.setHeaderText("Canvas Width-Height");
		dialog.setContentText("Width x Height:");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(w -> {
			String[] dims = w.split("x");
			try {
				Body.getInstance().initCanvas(Double.valueOf(dims[0]), Double.valueOf(dims[1]));
				labelCenterID.setVisible(false);
			} catch (Exception e) {
				new Alert(Alert.AlertType.ERROR, "Error reading dimensions.\nPlease use the correct format.").show();
				return;
			}
			Main.getInstance().initNewBody();
			setAllButtonFlag(false);
		});
	}

	@FXML
	private void openFile() {
		System.out.println("Head controller openFile");
		List<IShape> loadShapes = DataControl.load();
		if (!loadShapes.isEmpty()) {
			int i = loadShapes.size() - 1;
			String srv = loadShapes.get(i).getFigure();
			if ("service".equalsIgnoreCase(srv)) {
				double[] canvasSize = loadShapes.get(i).getDashes();
				Body.getInstance().initCanvas(canvasSize[0], canvasSize[1]);
				loadShapes.remove(i);
				labelCenterID.setVisible(false);
				Main.getInstance().initNewBody();
				setAllButtonFlag(false);
				graphics.getSheet().setShapes(loadShapes);
				graphics.update();
			} else
				new Alert(Alert.AlertType.ERROR, "Error reading file.").show();
		}
	}

	@FXML
	private void saveFile() {
		System.out.println("Head controller saveFile");
		DataControl.save(graphics);
	}

	@FXML
	private void pressExit() {
		Main.getInstance().exit();
	}

	@FXML
	private void clearClick() {
		graphics.getGraphicsEngine().clearCanvas(new RGBColor(1.0, 1.0, 1.0, 1.0));
		graphics.getSheet().clear();
	}

	@FXML
	private void rectangleClick() {
		setAllButtonFlag(false);
		rectangleID.setDisable(true);
		SetState.StateDrowFigure(graphics, StaticStr.RECTANGLE);
	}

	@FXML
	private void triangleClick() {
		setAllButtonFlag(false);
		triangleID.setDisable(true);
		SetState.StateDrowFigure(graphics, StaticStr.TRIANGLE);
	}

	@FXML
	private void circleClick() {
		setAllButtonFlag(false);
		circleID.setDisable(true);
		SetState.StateDrowFigure(graphics, StaticStr.CIRCLE);
	}

	@FXML
	private void lineClick() {
		setAllButtonFlag(false);
		lineID.setDisable(true);
		SetState.StateDrowFigure(graphics, StaticStr.LINE);
	}

	@FXML
	private void ellipseClick() {
		setAllButtonFlag(false);
		ellipseID.setDisable(true);
		SetState.StateDrowFigure(graphics, StaticStr.ELLIPSE);
	}

	@FXML
	private void polygonClick() {
		setAllButtonFlag(false);
		polygonID.setDisable(true);
		SetState.StateDrowFigure(graphics, StaticStr.POLIGON);
	}

	@FXML
	private void moveClick() {
		setAllButtonFlag(false);
		moveID.setDisable(true);
		SetState.StateMove(graphics);
	}

	@FXML
	private void resizeClick() {
		setAllButtonFlag(false);
		resizeID.setDisable(true);
		SetState.StateResize(graphics);
	}

	@FXML
	private void deleteClick() {
		setAllButtonFlag(false);
		deleteID.setDisable(true);
		SetState.StateDelete(graphics);
	}

	@FXML
	private void combinedClick() {
		setAllButtonFlag(false);
		combinedID.setDisable(true);
		SetState.StateCombined(graphics);
		inJoinID.setDisable(false);
		outJoinID.setDisable(false);
	}

	@FXML
	private void inJoinClick() {
		graphics.combine();
		inJoinID.setDisable(true);
	}

	@FXML
	private void outJoinClick() {
		graphics.recombine();
		outJoinID.setDisable(true);
	}

	@FXML
	private void upClick() {
		setAllButtonFlag(false);
		SetState.StateMoveUp(graphics);
	}

	@FXML
	private void downClick() {
		setAllButtonFlag(false);
		SetState.StateMoveDown(graphics);
	}

	@FXML
	private void undoClick() {
		graphics.undo();

	}

	@FXML
	private void redoClick() {
		graphics.redo();
	}

	public Label getCoordinates() {
		return coordinates;
	}

	private void setAllButtonFlag(boolean flag) {
		rectangleID.setDisable(flag);
		triangleID.setDisable(flag);
		circleID.setDisable(flag);
		ellipseID.setDisable(flag);
		lineID.setDisable(flag);
		polygonID.setDisable(flag);
		moveID.setDisable(flag);
		resizeID.setDisable(flag);
		deleteID.setDisable(flag);
		combinedID.setDisable(flag);
		upID.setDisable(flag);
		downID.setDisable(flag);
		inJoinID.setDisable(!flag);
		outJoinID.setDisable(!flag);
		clearID.setDisable(flag);
		undoID.setDisable(flag);
		redoID.setDisable(flag);

	}
}