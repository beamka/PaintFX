package figures.utils;

import java.util.List;

import engine.GraphicsEngine;
import figures.utils.impl.Point;
import figures.utils.impl.RGBColor;
import figures.utils.impl.Shape;

/**
 * @author IrynaTkachova
 *
 */

public interface IShape {

	public abstract IShape clone();

	public void setGraphicsFigure(GraphicsEngine graphicsEngine);

	public void loadGraphicsEngine(GraphicsEngine graphicsEngine);

	public String getFigure();

	public void addPoint(Point point);

	public List<Point> getPointsList();

	public double getLineWidth();

	public RGBColor getColorFill();

	public RGBColor getColorLine();

	public abstract void draw(GraphicsEngine graphicsEngine);

	public abstract boolean thisShape(Point cursor);

	public abstract void move(double moveX, double moveY);

	public abstract List<Shape> getResizeGrid(GraphicsEngine graphicsEngine);

	public abstract void setGraphicsMove(GraphicsEngine graphicsEngine);

	public double[] getDashes();

}
