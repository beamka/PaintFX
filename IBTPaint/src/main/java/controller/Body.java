package controller;

import engine.ContextGraphics;
import engine.SetState;
import engine.JavaFX.JavaFX;
import figures.Sheet;
import figures.utils.impl.Point;
import figures.utils.impl.RGBColor;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

/**
 * @author IrynaTkachova
 *
 */
public class Body {

	private static Body instance;
	private ContextGraphics graphics;

	@FXML
	private StackPane StachPaneID;
	@FXML
	private Canvas CanvasID;

	public void initCanvas(double mainWidth, double mainHeight) {
		StachPaneID.setMaxWidth(mainWidth);
		StachPaneID.setMaxHeight(mainHeight);
		CanvasID.setWidth(mainWidth);
		CanvasID.setHeight(mainHeight);

		GraphicsContext graphicsContext;
		graphicsContext = CanvasID.getGraphicsContext2D();
		JavaFX graphicsFX = new JavaFX(graphicsContext, CanvasID);
		ContextGraphics.getInstance().setGraphicsEngine(graphicsFX);

		graphics = ContextGraphics.getInstance();
		Sheet sheet = new Sheet(graphics);
		graphics.setSheet(sheet);

		graphics.getGraphicsEngine().clearCanvas(new RGBColor(1.0, 1.0, 1.0, 1.0));
		graphics.setState(SetState.StateMove(graphics));
		graphics.setMainWidth(mainWidth);
		graphics.setMainHeight(mainHeight);
	}

	@FXML
	private void initialize() {
		System.out.println("Body controller initialize");
		instance = this;

		StachPaneID.setOnMouseMoved(evt -> {
			Head.getInstance().getCoordinates().setText('\t' + "X: " + evt.getX() + '\t' + "Y: " + evt.getY());
			Point cursor = new Point(evt.getX(), evt.getY());
			graphics.getState().doMouseMoved(graphics, cursor);
		});

		StachPaneID.setOnMousePressed(e -> {
			Point startPoint = new Point(e.getX(), e.getY());
			graphics.getState().doMousePressed(graphics, startPoint);
		});

		StachPaneID.setOnMouseReleased(e -> {
			Point endPoint = new Point(e.getX(), e.getY());
			graphics.getState().doMouseReleased(graphics, endPoint);
		});

	}

	public StackPane getCanvas() {
		return StachPaneID;
	}

	public static Body getInstance() {
		return instance;
	}

	public ContextGraphics getGraphics() {
		return graphics;
	}
}