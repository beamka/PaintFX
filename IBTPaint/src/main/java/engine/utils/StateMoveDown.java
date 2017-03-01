package engine.utils;

import engine.ContextGraphics;
import engine.State;
import figures.utils.impl.Point;

/**
 * @author IrynaTkachova
 *
 */

public class StateMoveDown implements State {

	public StateMoveDown(ContextGraphics contextGraphics) {
		contextGraphics.setState(this);
	}

	@Override
	public void doMouseMoved(ContextGraphics contextGraphics, Point startPoint) {

	}

	@Override
	public void doMousePressed(ContextGraphics contextGraphics, Point startPoint) {
		for (int i = contextGraphics.getSheet().size() - 1; i >= 0; i--) {
			if (contextGraphics.getSheet().thisShape(i, startPoint)) {
				contextGraphics.getSheet().downZ(i);
				contextGraphics.update();
				break;
			}
		}
	}

	@Override
	public void doMouseReleased(ContextGraphics contextGraphics, Point endPoint) {
		contextGraphics.addUndone();
	}

}
