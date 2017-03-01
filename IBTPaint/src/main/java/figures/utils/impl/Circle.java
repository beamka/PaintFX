package figures.utils.impl;

import static figures.utils.constants.Constants.CIRCLE;
import static figures.utils.constants.Constants.NE;
import static figures.utils.constants.Constants.NW;
import static figures.utils.constants.Constants.SE;
import static figures.utils.constants.Constants.SW;

import java.util.ArrayList;
import java.util.List;

import engine.GraphicsEngine;
import figures.utils.IShape;

/**
 * @author IrynaTkachova
 *
 */

public class Circle extends Shape {
	private Point sPoint;
	private Point ePoint;

	public Circle(String figure, Point startPoint, Point endPoint) {
		super(figure, (endPoint.getX() - startPoint.getX()) / 2 + startPoint.getX(),
				(endPoint.getY() - startPoint.getY()) / 2 + startPoint.getY());
		calcCoordinates(startPoint, endPoint);
	}

	@Override
	public IShape clone() {
		IShape circle = new Circle(CIRCLE, sPoint, ePoint);
		return circle;
	}

	private void calcCoordinates(Point startPoint, Point endPoint) {
		sPoint = new Point();
		ePoint = new Point();
		double w = endPoint.getX() - startPoint.getX();
		double h = endPoint.getY() - startPoint.getY();
		if (w < 0) {
			sPoint.setX(endPoint.getX());
			ePoint.setX(startPoint.getX());
		} else {
			sPoint.setX(startPoint.getX());
			ePoint.setX(endPoint.getX());
		}
		if (h < 0) {
			sPoint.setY(endPoint.getY());
			ePoint.setY(startPoint.getY());
		} else {
			sPoint.setY(startPoint.getY());
			ePoint.setY(endPoint.getY());
		}
		if (Math.abs(w) > Math.abs(h)) {
			w = Math.abs(h);
			ePoint.setX(sPoint.getX() + Math.abs(w));
		} else {
			h = Math.abs(w);
			ePoint.setY(sPoint.getY() + Math.abs(h));
		}
		addPoint(sPoint);
		addPoint(ePoint);
	}

	@Override
	public void draw(GraphicsEngine graphicsEngine) {
		loadGraphicsEngine(graphicsEngine);
		List<Point> points = getPointsList();
		graphicsEngine.strokeOval(points.get(0).getX(), points.get(0).getY(),
				points.get(1).getX() - points.get(0).getX(), points.get(1).getY() - points.get(0).getY());
		graphicsEngine.fillOval(points.get(0).getX(), points.get(0).getY(), points.get(1).getX() - points.get(0).getX(),
				points.get(1).getY() - points.get(0).getY());
	}

	@Override
	public boolean thisShape(Point cursor) {
		System.out.println("thisShape CIRCLE>>>");
		System.out.println("cursor: X=" + cursor.getX() + " Y=" + cursor.getY());
		System.out.println("center: X=" + getX() + " Y=" + getY());
		List<Point> points = getPointsList();
		System.out.println("points: X1= " + points.get(0).getX() + " Y1=" + points.get(0).getY());
		System.out.println("points: X2= " + points.get(1).getX() + " Y2=" + points.get(1).getY());
		double radius = Math.abs((points.get(1).getX() - points.get(0).getX()) / 2);
		double k1 = Math.abs(cursor.getX() - getX());
		double k2 = Math.abs(cursor.getY() - getY());
		System.out.println("radius = " + radius);
		System.out.println("k1 = " + k1);
		System.out.println("k2 = " + k2);
		if (Math.pow(k1, 2) + Math.pow(k2, 2) < Math.pow(radius, 2)) {
			System.out.println("true = " + (Math.pow(k1, 2) + Math.pow(k2, 2)) + "<" + Math.pow(radius, 2));
			return true;
		}
		System.out.println("false = " + (Math.pow(k1, 2) + Math.pow(k2, 2)) + "<" + Math.pow(radius, 2));
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
		System.out.println("prepareResize CIRCLE>>>");
		List<Point> points = getPointsList();
		setGraphicsMove(graphicsEngine);
		graphicsEngine.strokeOval(points.get(0).getX(), points.get(0).getY(),
				points.get(1).getX() - points.get(0).getX(), points.get(1).getY() - points.get(0).getY());
		graphicsEngine.fillOval(points.get(0).getX(), points.get(0).getY(), points.get(1).getX() - points.get(0).getX(),
				points.get(1).getY() - points.get(0).getY());

		Point nw = new Point(points.get(0));
		Point ne = new Point(points.get(1).getX(), points.get(0).getY());
		Point se = new Point(points.get(1));
		Point sw = new Point(points.get(0).getX(), points.get(1).getY());

		List<Shape> resize_points = new ArrayList<Shape>();
		Rectangle recNW = new Rectangle(NW, nw);
		Rectangle recNE = new Rectangle(NE, ne);
		Rectangle recSE = new Rectangle(SE, se);
		Rectangle recSW = new Rectangle(SW, sw);
		// 1
		resize_points.add(recNW);
		// 2
		resize_points.add(recSE);
		// 12
		resize_points.add(recNE);
		resize_points.add(recSW);

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
		graphicsEngine.strokeOval(points.get(0).getX(), points.get(0).getY(),
				points.get(1).getX() - points.get(0).getX(), points.get(1).getY() - points.get(0).getY());
		graphicsEngine.fillOval(points.get(0).getX(), points.get(0).getY(), points.get(1).getX() - points.get(0).getX(),
				points.get(1).getY() - points.get(0).getY());
	}
}
