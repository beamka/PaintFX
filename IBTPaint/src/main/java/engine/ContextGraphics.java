package engine;

import java.util.List;
import java.util.Stack;

import figures.Figure;
import figures.Sheet;
import figures.utils.IShape;
import figures.utils.constants.StaticStr;
import figures.utils.impl.RGBColor;
import figures.utils.impl.Shape;

/**
 * @author IrynaTkachova
 *
 */

public class ContextGraphics {
	private static ContextGraphics instance;
	private GraphicsEngine graphicsEngine;

	private State state;
	private StaticStr currentButton = StaticStr.MOVE;
	private StaticStr setColorType = StaticStr.COLOR_LINE;
	private RGBColor colorSpace = new RGBColor(1.0, 1.0, 1.0, 1.0);
	private RGBColor colorLine = new RGBColor(0.0, 0.0, 0.0, 1.0);
	private RGBColor colorFill = new RGBColor(1.0, 1.0, 1.0, 1.0);
	private double lineWidth = 1;
	private int countPoint = 5;
	private Sheet sheet;
	private List<Shape> resizeList;
	private List<IShape> tempShapes;

	private double mainWidth;
	private double mainHeight;

	private Stack<Sheet> undone = new Stack<>();
	private Stack<Sheet> redone = new Stack<>();

	private ContextGraphics() {
	}

	public void setCurrentGraphics() {
		graphicsEngine.setColorFill(colorFill);
		graphicsEngine.setColorLine(colorLine);
		graphicsEngine.setLineWidth(lineWidth);
	}

	public static ContextGraphics getInstance() {
		if (instance == null) {
			instance = new ContextGraphics();
		}
		return instance;
	}

	public void setColor(RGBColor colorPicker) {
		switch (setColorType) {
		case COLOR_LINE:
			colorLine = colorPicker;
			break;
		case COLOR_FILL:
			colorFill = colorPicker;
			break;
		default:
			break;
		}
	}

	public void update() {
		graphicsEngine.clearCanvas(colorSpace);
		sheet.draw();
	}

	public void loadGraphics(Shape shape) {
		shape.loadGraphicsEngine(graphicsEngine);
	}

	public void combine() {
		if (!getTempShapes().isEmpty()) {
			Figure.Combined(getSheet(), getTempShapes());
			getTempShapes().clear();
			update();
		}
	}

	public void recombine() {
		if (!getTempShapes().isEmpty()) {
			for (IShape shape : getTempShapes()) {
				getSheet().addReCombineFigure(shape);
			}
		}
	}

	public void undo() {
		System.out.println("undo");
		if (undone.size() > 1) {
			redone.push(undone.pop());
			sheet = undone.peek();
		} else {
			if (!undone.isEmpty()) {
				redone.push(undone.pop());
				sheet.clear();
			}
		}
		update();
	}

	public void redo() {
		System.out.println("redo");
		if (redone.size() > 0) {
			undone.push(redone.pop());
			sheet = undone.peek();
		}
		update();
	}

	public void addUndone() {
		Sheet nsheet = new Sheet(this);
		nsheet = sheet.clone();
		undone.push(nsheet);
		redone.clear();
	}

	// =============================

	public GraphicsEngine getGraphicsEngine() {
		return graphicsEngine;
	}

	public void setGraphicsEngine(GraphicsEngine graphicsEngine) {
		this.graphicsEngine = graphicsEngine;
	}

	public void setCurrentButton(StaticStr currentButton) {
		this.currentButton = currentButton;
	}

	public StaticStr getCurrentButton() {
		return currentButton;
	}

	public RGBColor getColorLine() {
		return colorLine;
	}

	public void setColorLine(RGBColor colorLine) {
		this.colorLine = colorLine;
	}

	public RGBColor getColorFill() {
		return colorFill;
	}

	public void setColorFill(RGBColor colorFill) {
		this.colorFill = colorFill;
	}

	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	public RGBColor getColorSpace() {
		return colorSpace;
	}

	public void setSetColorType(StaticStr setColorType) {
		this.setColorType = setColorType;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public List<Shape> getResizeList() {
		return resizeList;
	}

	public void setResizeList(List<Shape> resizeList) {
		this.resizeList = resizeList;
	}

	public List<IShape> getTempShapes() {
		return tempShapes;
	}

	public void setTempShapes(List<IShape> tempShapes) {
		this.tempShapes = tempShapes;
	}

	public int getCountPoint() {
		return countPoint;
	}

	public void setCountPoint(int countPoint) {
		this.countPoint = countPoint;
	}

	public double getMainWidth() {
		return mainWidth;
	}

	public void setMainWidth(double mainWidth) {
		this.mainWidth = mainWidth;
	}

	public double getMainHeight() {
		return mainHeight;
	}

	public void setMainHeight(double mainHeight) {
		this.mainHeight = mainHeight;
	}

}
