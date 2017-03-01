package engine.utils;

import java.util.ArrayList;
import java.util.List;

import engine.ContextGraphics;
import engine.State;
import figures.utils.IShape;
import figures.utils.impl.Point;

/**
 * @author IrynaTkachova
 *
 */

public class StateCombined implements State {
	private List<IShape> tempShapes;

	public StateCombined(ContextGraphics contextGraphics) {
		contextGraphics.setState(this);
		tempShapes = new ArrayList<>();
		contextGraphics.setTempShapes(tempShapes);
	}

	@Override
	public void doMouseMoved(ContextGraphics contextGraphics, Point startPoint) {

	}

	@Override
	public void doMousePressed(ContextGraphics contextGraphics, Point startPoint) {
		tempShapes = contextGraphics.getTempShapes();
		for (int i = contextGraphics.getSheet().size() - 1; i >= 0; i--) {
			if (contextGraphics.getSheet().thisShape(i, startPoint)) {
				IShape shape = contextGraphics.getSheet().getShape(i);
				tempShapes.add(shape);
				contextGraphics.getSheet().remove(i);
			}
		}
		contextGraphics.setTempShapes(tempShapes);
		contextGraphics.update();
		for (IShape shape : tempShapes) {
			shape.setGraphicsMove(contextGraphics.getGraphicsEngine());
		}
	}

	@Override
	public void doMouseReleased(ContextGraphics contextGraphics, Point endPoint) {
		contextGraphics.addUndone();
	}

}
