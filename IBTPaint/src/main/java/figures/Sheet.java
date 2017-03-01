package figures;

import static figures.utils.constants.Constants.COMBINED;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import engine.ContextGraphics;
import engine.GraphicsEngine;
import figures.utils.IShape;
import figures.utils.impl.Combined;
import figures.utils.impl.Point;
import figures.utils.impl.Shape;

/**
 * @author IrynaTkachova
 *
 */

public class Sheet implements Serializable {

	private List<IShape> shapes;
	GraphicsEngine graphicsEngine;

	private Sheet() {
		shapes = new ArrayList<IShape>();
	}

	public Sheet(ContextGraphics graphics) {
		shapes = new ArrayList<IShape>();
		this.graphicsEngine = graphics.getGraphicsEngine();
	}

	public Sheet clone() {
		Sheet nsheet = new Sheet();
		nsheet.graphicsEngine = graphicsEngine;
		for (IShape shape : shapes) {
			// IShape nshape = shape.clone();
			// nsheet.shapes.add(nshape);
			nsheet.shapes.add(shape);
		}
		return nsheet;
	}

	public void draw() {
		for (IShape shape : shapes) {
			shape.draw(graphicsEngine);
		}
	}

	public void clear() {
		shapes.clear();
	}

	public boolean thisShape(int index, Point cursor) {
		return shapes.get(index).thisShape(cursor);
	}

	public int size() {
		return shapes.size();
	}

	public IShape getShape(int index) {
		return shapes.get(index);
	}

	public void remove(int index) {
		shapes.remove(index);
	}

	public void moveShape(int index, double moveX, double moveY) {
		shapes.get(index).move(moveX, moveY);
	}

	public List<Shape> prepareResize(int index) {
		return shapes.get(index).getResizeGrid(graphicsEngine);
	}

	public void figureMovePoint(int indexFigure, int indexPoint, double moveX, double moveY) {
		shapes.get(indexFigure).getPointsList().get(indexPoint).moveTo(moveX, moveY);
		// shapes.get(indexFigure).recalculateCenter();
	}

	public void upZ(int index) {
		if (index < shapes.size() - 1) {
			IShape shape = shapes.get(index);
			shapes.remove(index);
			index++;
			shapes.add(index, shape);
		}
		draw();
	}

	public void downZ(int index) {
		if (index > 0) {
			IShape shape = shapes.get(index);
			shapes.remove(index);
			index--;
			shapes.add(index, shape);
		}
		draw();
	}

	public void redrowResizeList(int index) {
		for (Shape resizeFigure : shapes.get(index).getResizeGrid(graphicsEngine))
			resizeFigure.draw(graphicsEngine);
	}

	public void addFigure(IShape shape) {
		shape.setGraphicsFigure(graphicsEngine);
		shapes.add(shape);
		shape.draw(graphicsEngine);
	}

	public void addReCombineFigure(IShape shape) {
		if (COMBINED.equals(shape.getFigure())) {
			Combined combined = (Combined) shape;
			for (IShape sp : combined.getShape()) {
				shapes.add(sp);
			}
		} else
			shapes.add(shape);
		shape.draw(graphicsEngine);
	}

	public void setGraphicsMove(int index) {
		IShape shape = getShape(index);
		shape.setGraphicsMove(graphicsEngine);
	}

	public List<IShape> getShapes() {
		return shapes;
	}

	public void setShapes(List<IShape> shapes) {
		this.shapes = shapes;
	}

}
