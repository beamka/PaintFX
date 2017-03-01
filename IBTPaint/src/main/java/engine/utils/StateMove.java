package engine.utils;

import engine.ContextGraphics;
import engine.State;
import figures.utils.impl.Point;

/**
 * @author IrynaTkachova
 *
 */

public class StateMove implements State {
	public static boolean go_move = false;
	public static int indexMove;
	public Point startPoint;

	public StateMove(ContextGraphics contextGraphics) {
		contextGraphics.setState(this);
	}

	@Override
	public void doMouseMoved(ContextGraphics contextGraphics, Point startPoint) {

	}

	@Override
	public void doMousePressed(ContextGraphics contextGraphics, Point startPoint) {
		this.startPoint = startPoint;
		for (int i = contextGraphics.getSheet().size() - 1; i >= 0; i--) {
			if (contextGraphics.getSheet().thisShape(i, startPoint)) {
				go_move = true;
				indexMove = i;
				contextGraphics.getSheet().setGraphicsMove(i);
				contextGraphics.getGraphicsEngine().setCursor(CursorTypes.CLOSED_HAND);
				break;
			}
		}
	}

	@Override
	public void doMouseReleased(ContextGraphics contextGraphics, Point endPoint) {
		if (go_move) {
			double moveX = endPoint.getX() - startPoint.getX();
			double moveY = endPoint.getY() - startPoint.getY();
			contextGraphics.getSheet().moveShape(indexMove, moveX, moveY);
			contextGraphics.getGraphicsEngine().clearCanvas(contextGraphics.getColorSpace());
			contextGraphics.getSheet().draw();
			contextGraphics.getGraphicsEngine().setCursor(CursorTypes.OPEN_HAND);
			go_move = false;
			contextGraphics.addUndone();
		} else
			System.out.println("MOVE-false");
	}

}
