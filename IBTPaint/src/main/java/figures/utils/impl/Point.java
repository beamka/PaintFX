package figures.utils.impl;

import java.io.Serializable;

/**
 * @author IrynaTkachova
 *
 */
public class Point implements Serializable {
	private double[] coordinates = new double[2];

	public Point() {
		coordinates = new double[] { 0, 0 };
	}

	public Point(double[] coordinates) {
		this.coordinates = coordinates;
	}

	public Point(double X, double Y) {
		coordinates[0] = X;
		coordinates[1] = Y;
	}

	public Point(Point coordinates) {
		this.coordinates[0] = coordinates.getX();
		this.coordinates[1] = coordinates.getY();
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public double getX() {
		return coordinates[0];
	}

	public double getY() {
		return coordinates[1];
	}

	public void setX(double X) {
		coordinates[0] = X;
	}

	public void setY(double Y) {
		coordinates[1] = Y;
	}

	public void setCoordinates(double[] coordinat) {
		coordinates = coordinat;
	}

	public void setCoordinates(double X, double Y) {
		coordinates[0] = X;
		coordinates[1] = Y;
	}

	public void moveTo(double moveX, double moveY) {
		coordinates[0] = coordinates[0] + moveX;
		coordinates[1] = coordinates[1] + moveY;

	}

	protected Point getPoint() {
		return this;
	}
}
