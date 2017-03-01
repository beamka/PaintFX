package figures.utils.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import engine.GraphicsEngine;
import figures.utils.IShape;

/**
 * @author IrynaTkachova
 *
 */

public abstract class Shape extends Point implements IShape, Serializable {

	private String figure;
	private RGBColor colorLine;
	private RGBColor colorFill;
	private double lineWidth;
	private double[] dashes;
	private List<Point> points;

	public Shape(String figure, double centerX, double centerY) {
		// figures center
		super(centerX, centerY);
		this.figure = figure;
		this.points = new ArrayList<Point>();
		dashes = new double[] { 0 };
	}

	@Override
	public abstract IShape clone();

	@Override
	public void loadGraphicsEngine(GraphicsEngine graphicsEngine) {
		graphicsEngine.setColorFill(colorFill);
		graphicsEngine.setColorLine(colorLine);
		graphicsEngine.setLineWidth(lineWidth);
		graphicsEngine.setLineDashes(dashes);
		// draw(graphicsEngine);
	}

	@Override
	public void setGraphicsFigure(GraphicsEngine graphicsEngine) {
		colorLine = graphicsEngine.getColorLine();
		colorFill = graphicsEngine.getColorFill();
		lineWidth = graphicsEngine.getLineWidth();
	}

	protected void loadResizeGraphics(GraphicsEngine graphicsEngine) {
		graphicsEngine.setColorFill(colorFill.darker());
		graphicsEngine.setColorLine(new RGBColor(1.0, 1.0, 1.0, 1.0));
		graphicsEngine.setLineWidth(3);
		double[] dashesMove = new double[] { 10, 10 };
		graphicsEngine.setLineDashes(dashesMove);
	}

	@Override
	public String getFigure() {
		return figure;
	}

	@Override
	public RGBColor getColorLine() {
		return colorLine;
	}

	@Override
	public RGBColor getColorFill() {
		return colorFill;
	}

	@Override
	public double getLineWidth() {
		return lineWidth;
	}

	@Override
	public void addPoint(Point point) {
		Point p = new Point(point.getX(), point.getY());
		points.add(p);
	}

	@Override
	public List<Point> getPointsList() {
		return points;
	}

	public void setGraphicsGrid() {
		colorLine = new RGBColor(0.0, 0.0, 0.0, 1.0);
		colorFill = new RGBColor(1.0, 1.0, 1.0, 1.0);
		lineWidth = 3;
	}

	@Override
	public abstract void draw(GraphicsEngine graphicsEngine);

	@Override
	public abstract boolean thisShape(Point cursor);

	@Override
	public abstract void move(double moveX, double moveY);

	@Override
	public abstract void setGraphicsMove(GraphicsEngine graphicsEngine);

	@Override
	public abstract List<Shape> getResizeGrid(GraphicsEngine graphicsEngine);

	public void recalculateCenter() {
		Point sPoint = points.get(0);
		Point ePoint = points.get(1);
		setCoordinates((ePoint.getX() - sPoint.getX()) / 2 + sPoint.getX(),
				(ePoint.getY() - sPoint.getY()) / 2 + sPoint.getY());

	}

	public double[] getDashes() {
		return dashes;
	}

	public void setDashes(double[] dashes) {
		this.dashes = dashes;
	}

}