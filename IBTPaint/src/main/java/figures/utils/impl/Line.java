package figures.utils.impl;

import static figures.utils.constants.Constants.LINE;
import static figures.utils.constants.Constants.NW;
import static figures.utils.constants.Constants.SE;

import java.util.ArrayList;
import java.util.List;

import engine.GraphicsEngine;
import figures.utils.IShape;

/**
 * @author IrynaTkachova
 *
 */

public class Line extends Shape {
	private Point sPoint;
	private Point ePoint;

	public Line(String figure, Point startPoint, Point endPoint) {
		super(figure, (endPoint.getX() - startPoint.getX()) / 2 + startPoint.getX(),
				(endPoint.getY() - startPoint.getY()) / 2 + startPoint.getY());
		this.sPoint = startPoint;
		this.ePoint = endPoint;
		addPoint(sPoint);
		addPoint(ePoint);
	}

	@Override
	public IShape clone() {
		IShape line = new Line(LINE, sPoint, ePoint);
		return line;
	}

	@Override
	public void draw(GraphicsEngine graphicsEngine) {
		loadGraphicsEngine(graphicsEngine);
		List<Point> points = getPointsList();
		graphicsEngine.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(1).getX(),
				points.get(1).getY());
	}

	@Override
	public boolean thisShape(Point cursor) {
		System.out.println("thisShape Line>>>");
		List<Point> points = getPointsList();
		double x = cursor.getX();
		double y = cursor.getY();
		double x1 = points.get(0).getX();
		double y1 = points.get(0).getY();
		double x2 = points.get(1).getX();
		double y2 = points.get(1).getY();
		double ln = Math.abs((x - x2) / (x1 - x2) - (y - y2) / (y1 - y2));
		// on the line
		if (ln >= 0 && ln <= 0.05) {
			System.out.println("true = " + ln + " <= 0.05");
			double k1 = Math.abs(cursor.getX() - getX());
			double k2 = Math.abs(cursor.getY() - getY());
			double radius = Math.abs((points.get(1).getX() - points.get(0).getX()) / 2);
			if (k1 < radius && k2 < radius) {
				System.out.println("on the line");
				return true;
			} else
				return false;
		}
		System.out.println("false = " + ln + " <= 0.05");
		return false;
	}

	@Override
	public void move(double moveX, double moveY) {
		for (Point point : getPointsList()) {
			point.moveTo(moveX, moveY);
		}
		// move center
		getPoint().moveTo(moveX, moveY);
	}

	@Override
	public List<Shape> getResizeGrid(GraphicsEngine graphicsEngine) {
		System.out.println("prepareResize LINE>>>");
		List<Point> points = getPointsList();
		setGraphicsMove(graphicsEngine);
		graphicsEngine.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(1).getX(),
				points.get(1).getY());
		Point nw = new Point(points.get(0));
		Point se = new Point(points.get(1));
		List<Shape> resize_points = new ArrayList<Shape>();
		Rectangle recNW = new Rectangle(NW, nw);
		Rectangle recSE = new Rectangle(SE, se);
		// 1
		resize_points.add(recNW);
		// 2
		resize_points.add(recSE);

		for (Shape shape : resize_points) {
			shape.setGraphicsGrid();
			shape.draw(graphicsEngine);
		}
		return resize_points;
	}

	@Override
	public void setGraphicsMove(GraphicsEngine graphicsEngine) {
		List<Point> points = getPointsList();
		loadResizeGraphics(graphicsEngine);
		graphicsEngine.strokeLine(points.get(0).getX(), points.get(0).getY(), points.get(1).getX(),
				points.get(1).getY());
	}

}
