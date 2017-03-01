package figures.utils.impl;

import static figures.utils.constants.Constants.E;
import static figures.utils.constants.Constants.N;
import static figures.utils.constants.Constants.NE;
import static figures.utils.constants.Constants.NW;
import static figures.utils.constants.Constants.RECTANGLE;
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

public class Rectangle extends Shape {
	private Point sPoint;
	private Point ePoint;

	public Rectangle(String figure, Point startPoint, Point endPoint) {
		super(figure, (endPoint.getX() - startPoint.getX()) / 2 + startPoint.getX(),
				(endPoint.getY() - startPoint.getY()) / 2 + startPoint.getY());
		calcCoordinates(startPoint, endPoint);
	}

	@Override
	public IShape clone() {
		IShape rectangle = new Rectangle(RECTANGLE, sPoint, ePoint);
		return rectangle;
	}

	public Rectangle(String figure, Point centerPoint) {
		super(figure, centerPoint.getX(), centerPoint.getY());
		sPoint = new Point();
		ePoint = new Point();
		this.sPoint.setX(centerPoint.getX() - 3);
		this.sPoint.setY(centerPoint.getY() - 3);
		this.ePoint.setX(centerPoint.getX() + 3);
		this.ePoint.setY(centerPoint.getY() + 3);
		addPoint(sPoint);
		addPoint(ePoint);
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
		graphicsEngine.fillRect(points.get(0).getX(), points.get(0).getY(), points.get(1).getX() - points.get(0).getX(),
				points.get(1).getY() - points.get(0).getY());
		graphicsEngine.strokeRect(points.get(0).getX(), points.get(0).getY(),
				points.get(1).getX() - points.get(0).getX(), points.get(1).getY() - points.get(0).getY());
	}

	@Override
	public boolean thisShape(Point cursor) {
		List<Point> points = getPointsList();
		if ((cursor.getX() > points.get(0).getX() && cursor.getX() < points.get(1).getX())
				&& (cursor.getY() > points.get(0).getY() && cursor.getY() < points.get(1).getY())) {
			return true;
		}
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
		System.out.println("getResizeGrid RECTANGLE>>>");

		List<Point> points = getPointsList();
		setGraphicsMove(graphicsEngine);
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
		graphicsEngine.strokeRect(points.get(0).getX(), points.get(0).getY(),
				points.get(1).getX() - points.get(0).getX(), points.get(1).getY() - points.get(0).getY());
		graphicsEngine.fillRect(points.get(0).getX(), points.get(0).getY(), points.get(1).getX() - points.get(0).getX(),
				points.get(1).getY() - points.get(0).getY());
	}

}