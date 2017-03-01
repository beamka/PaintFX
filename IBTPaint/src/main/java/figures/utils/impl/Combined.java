package figures.utils.impl;

import static figures.utils.constants.Constants.COMBINED;

import java.util.ArrayList;
import java.util.List;

import engine.GraphicsEngine;
import figures.utils.IShape;

/**
 * @author IrynaTkachova
 *
 */

public class Combined extends Shape {
	private List<IShape> shapes;

	public Combined(String figure, Point startPoint, Point endPoint) {
		super(figure, (endPoint.getX() - startPoint.getX()) / 2 + startPoint.getX(),
				(endPoint.getY() - startPoint.getY()) / 2 + startPoint.getY());
		// calcCoordinates(startPoint, endPoint);
		shapes = new ArrayList<>();
	}

	@Override
	public IShape clone() {
		Point point = new Point();
		Combined combined = new Combined(COMBINED, point, point);
		return combined;
	}

	public List<IShape> getShape() {
		return shapes;
	}

	public void addFigures(List<IShape> tempShapes) {
		for (IShape shape : tempShapes) {
			shapes.add(shape);
		}
	}

	public void addShape(IShape shape) {
		shapes.add(shape);
	}

	public void removeShape(int index) {
		shapes.remove(index);
	}

	@Override
	public void draw(GraphicsEngine graphicsEngine) {
		for (IShape shape : shapes) {
			shape.draw(graphicsEngine);
		}
	}

	@Override
	public boolean thisShape(Point cursor) {
		boolean result = false;
		for (IShape shape : shapes) {
			if (shape.thisShape(cursor)) {
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public void move(double moveX, double moveY) {
		for (IShape shape : shapes) {
			shape.move(moveX, moveY);
		}
	}

	@Override
	public List<Shape> getResizeGrid(GraphicsEngine graphicsEngine) {
		return null;
	}

	@Override
	public void setGraphicsMove(GraphicsEngine graphicsEngine) {
		loadResizeGraphics(graphicsEngine);
		for (IShape shape : shapes) {
			shape.setGraphicsMove(graphicsEngine);
		}
	}

}
