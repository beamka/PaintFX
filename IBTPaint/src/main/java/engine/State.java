package engine;

import figures.utils.impl.Point;

/**
 * @author IrynaTkachova
 *
 */

public interface State {

	public void doMouseMoved(ContextGraphics contextGraphics, Point startPoint);

	public void doMousePressed(ContextGraphics contextGraphics, Point startPoint);

	public void doMouseReleased(ContextGraphics contextGraphics, Point endPoint);
}
