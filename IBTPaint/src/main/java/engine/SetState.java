package engine;

import engine.utils.CursorTypes;
import engine.utils.StateCombined;
import engine.utils.StateDelete;
import engine.utils.StateDrowFigure;
import engine.utils.StateMove;
import engine.utils.StateMoveDown;
import engine.utils.StateMoveUp;
import engine.utils.StateResize;
import figures.utils.constants.StaticStr;

/**
 * @author IrynaTkachova
 *
 */

public class SetState {

	public static State StateMove(ContextGraphics contextGraphics) {
		contextGraphics.update();
		contextGraphics.getGraphicsEngine().setCursor(CursorTypes.OPEN_HAND);
		State stateMove = new StateMove(contextGraphics);
		return stateMove;
	}

	public static State StateMoveUp(ContextGraphics contextGraphics) {
		contextGraphics.update();
		contextGraphics.getGraphicsEngine().setCursor(CursorTypes.HAND);
		State stateMoveUp = new StateMoveUp(contextGraphics);
		return stateMoveUp;
	}

	public static State StateMoveDown(ContextGraphics contextGraphics) {
		contextGraphics.update();
		contextGraphics.getGraphicsEngine().setCursor(CursorTypes.HAND);
		State stateMoveDown = new StateMoveDown(contextGraphics);
		return stateMoveDown;
	}

	public static State StateDrowFigure(ContextGraphics contextGraphics, StaticStr figure) {
		contextGraphics.setCurrentButton(figure);
		contextGraphics.update();
		contextGraphics.getGraphicsEngine().setCursor(CursorTypes.CROSSHAIR);
		State stateDrowFigure = new StateDrowFigure(contextGraphics);
		return stateDrowFigure;
	}

	public static State StateDelete(ContextGraphics contextGraphics) {
		contextGraphics.update();
		contextGraphics.getGraphicsEngine().setCursor(CursorTypes.HAND);
		State stateDelete = new StateDelete(contextGraphics);
		return stateDelete;
	}

	public static State StateResize(ContextGraphics contextGraphics) {
		contextGraphics.update();
		contextGraphics.getGraphicsEngine().setCursor(CursorTypes.HAND);
		State stateResize = new StateResize(contextGraphics);
		return stateResize;
	}

	public static State StateCombined(ContextGraphics contextGraphics) {
		contextGraphics.update();
		contextGraphics.getGraphicsEngine().setCursor(CursorTypes.DISAPPEAR);
		State stateCombined = new StateCombined(contextGraphics);
		return stateCombined;
	}
}
