package figures.utils.impl;

import static figures.utils.constants.Constants.E;
import static figures.utils.constants.Constants.ELLIPSE;
import static figures.utils.constants.Constants.N;
import static figures.utils.constants.Constants.NE;
import static figures.utils.constants.Constants.NW;
import static figures.utils.constants.Constants.S;
import static figures.utils.constants.Constants.SE;
import static figures.utils.constants.Constants.SW;
import static figures.utils.constants.Constants.W;

import java.util.ArrayList;
import java.util.List;

import engine.GraphicsEngine;
import figures.utils.IShape;

/**
 * @author IrynaTkachova
 *
 */

public class Ellipse extends Shape {
	private Point sPoint;
	private Point ePoint;

	public Ellipse(String figure, Point startPoint, Point endPoint) {
		super(figure, (endPoint.getX() - startPoint.getX()) / 2 + startPoint.getX(),
				(endPoint.getY() - startPoint.getY()) / 2 + startPoint.getY());
		calcCoordinates(startPoint, endPoint);
	}

	@Override
	public IShape clone() {
		IShape ellipse = new Ellipse(ELLIPSE, sPoint, ePoint);
		return ellipse;
	}

	private void calcCoordinates(Point startPoint, Point endPoint) {
		sPoint = new Point();
		ePoint = new Point();
		double w = endPoint.getX() - startPoint.getX();
		if (w < 0) {
			sPoint.setX(endPoint.getX());
			ePoint.setX(startPoint.getX());
		} else {
			sPoint.setX(startPoint.getX());
			ePoint.setX(endPoint.getX());
		}
		double h = endPoint.getY() - startPoint.getY();
		if (h < 0) {
			sPoint.setY(endPoint.getY());
			ePoint.setY(startPoint.getY());
		} else {
			sPoint.setY(startPoint.getY());
			ePoint.setY(endPoint.getY());
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
		System.out.println("thisShape Ellipse>>>");
		List<Point> points = getPointsList();
		double radiusA = Math.abs((points.get(1).getX() - points.get(0).getX()) / 2);
		double radiusB = Math.abs((points.get(1).getY() - points.get(0).getY()) / 2);
		double k1 = Math.abs(cursor.getX() - getX());
		double k2 = Math.abs(cursor.getY() - getY());
		if ((Math.pow(k1, 2) / Math.pow(radiusA, 2)) + (Math.pow(k2, 2) / Math.pow(radiusB, 2)) <= 1) {
			System.out.println("true = " + (Math.pow(k1, 2) / Math.pow(radiusA, 2))
					+ (Math.pow(k2, 2) / Math.pow(radiusB, 2)) + "<= 1");
			return true;
		}
		System.out.println("false = " + (Math.pow(k1, 2) / Math.pow(radiusA, 2))
				+ (Math.pow(k2, 2) / Math.pow(radiusB, 2)) + "<= 1");
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
		System.out.println("prepareResize ELLIPSE>>>");
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
		Point n = new Point((ne.getX() - nw.getX()) / 2 + nw.getX(), nw.getY());
		Point e = new Point(ne.getX(), (se.getY() - ne.getY()) / 2 + ne.getY());
		Point s = new Point((se.getX() - sw.getX()) / 2 + sw.getX(), sw.getY());
		Point w = new Point(nw.getX(), (sw.getY() - nw.getY()) / 2 + nw.getY());
		List<Shape> resize_points = new ArrayList<Shape>();
		Rectangle recNW = new Rectangle(NW, nw);
		Rectangle recNE = new Rectangle(NE, ne);
		Rectangle recSE = new Rectangle(SE, se);
		Rectangle recSW = new Rectangle(SW, sw);
		Rectangle recN = new Rectangle(N, n);
		Rectangle recE = new Rectangle(E, e);
		Rectangle recS = new Rectangle(S, s);
		Rectangle recW = new Rectangle(W, w);
		// 1
		resize_points.add(recNW);
		resize_points.add(recN);
		resize_points.add(recW);
		// 2
		resize_points.add(recSE);
		resize_points.add(recE);
		resize_points.add(recS);
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