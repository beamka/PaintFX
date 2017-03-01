package figures.utils.impl;

import static figures.utils.constants.Constants.RECTANGLE;

import java.util.ArrayList;
import java.util.List;

import engine.GraphicsEngine;
import figures.utils.IShape;

public class Polygon extends Shape {
	private Point sPoint;
	private Point ePoint;

	public Polygon(String figure, List<Point> points) {
		super(figure, points.get(0).getX(), points.get(0).getY());
		// calcCoordinates(startPoint, endPoint);
		for (Point point : points) {
			addPoint(point);
		}
	}

	@Override
	public IShape clone() {
		IShape polygon = new Polygon(RECTANGLE, getPointsList());
		return polygon;
	}

	// private void calcCoordinates(Point startPoint, Point endPoint) {
	// sPoint = new Point();
	// ePoint = new Point();
	// double w = endPoint.getX() - startPoint.getX();
	// if (w < 0) {
	// sPoint.setX(endPoint.getX());
	// ePoint.setX(startPoint.getX());
	// } else {
	// sPoint.setX(startPoint.getX());
	// ePoint.setX(endPoint.getX());
	// }
	// double h = endPoint.getY() - startPoint.getY();
	// if (h < 0) {
	// sPoint.setY(endPoint.getY());
	// ePoint.setY(startPoint.getY());
	// } else {
	// sPoint.setY(startPoint.getY());
	// ePoint.setY(endPoint.getY());
	// }
	// addPoint(sPoint);
	// addPoint(ePoint);
	// }

	@Override
	public void draw(GraphicsEngine graphicsEngine) {
		loadGraphicsEngine(graphicsEngine);
		List<Point> points = getPointsList();
		int npoints = points.size();
		double[] pointsX = getPointsX(points);
		double[] pointsY = getPointsY(points);

		graphicsEngine.fillPolygon(pointsX, pointsY, npoints);
		graphicsEngine.strokePolygon(pointsX, pointsY, npoints);
	}

	private double[] getPointsX(List<Point> points) {
		double[] pointsX = new double[points.size()];
		for (int i = 0; i < points.size(); i++) {
			pointsX[i] = points.get(i).getX();
		}
		return pointsX;
	}

	private double[] getPointsY(List<Point> points) {
		double[] pointsY = new double[points.size()];
		for (int i = 0; i < points.size(); i++) {
			pointsY[i] = points.get(i).getY();
		}
		return pointsY;
	}

	@Override
	public boolean thisShape(Point cursor) {
		List<Point> points = getPointsList();
		int npol = points.size();
		double[] xp = getPointsX(points);
		double[] yp = getPointsY(points);
		double x = cursor.getX();
		double y = cursor.getY();
		int j = npol - 1;
		boolean result = false;
		for (int i = 0; i < npol; i++) {
			if ((((yp[i] <= y) && (y < yp[j])) || ((yp[j] <= y) && (y < yp[i])))
					&& (x > (xp[j] - xp[i]) * (y - yp[i]) / (yp[j] - yp[i]) + xp[i])) {
				result = !result;
			}
			j = i;
		}
		return result;
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
		List<Point> points = getPointsList();
		setGraphicsMove(graphicsEngine);
		int npol = points.size();
		List<Shape> resize_points = new ArrayList<Shape>();
		for (int i = 0; i < npol; i++) {
			Point p = new Point(points.get(i));
			Rectangle recP = new Rectangle(Integer.toString(i), p);
			resize_points.add(recP);
		}

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
		int npoints = points.size();
		double[] pointsX = getPointsX(points);
		double[] pointsY = getPointsY(points);

		graphicsEngine.fillPolygon(pointsX, pointsY, npoints);
		graphicsEngine.strokePolygon(pointsX, pointsY, npoints);
	}

}
