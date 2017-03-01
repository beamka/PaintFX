package engine.utils;

import static figures.utils.constants.Constants.E;
import static figures.utils.constants.Constants.N;
import static figures.utils.constants.Constants.NE;
import static figures.utils.constants.Constants.NW;
import static figures.utils.constants.Constants.S;
import static figures.utils.constants.Constants.SE;
import static figures.utils.constants.Constants.SW;
import static figures.utils.constants.Constants.W;

import engine.ContextGraphics;
import engine.State;
import figures.utils.impl.Point;
import figures.utils.impl.RGBColor;
import figures.utils.impl.Shape;

/**
 * @author IrynaTkachova
 *
 */

public class StateResize implements State {
	public static int indexMove;
	public static int indexResize;
	public static boolean chek_resize_figure = false;
	public static boolean resize_move = false;
	public static boolean resize_set = false;
	public Point startPoint;

	public StateResize(ContextGraphics contextGraphics) {
		contextGraphics.setState(this);
	}

	@Override
	public void doMouseMoved(ContextGraphics contextGraphics, Point startPoint) {
		if (chek_resize_figure) {
			if (contextGraphics.getSheet().thisShape(indexMove, startPoint)) {
				contextGraphics.getGraphicsEngine().setCursor(CursorTypes.DEFAULT);
			} else {
				contextGraphics.getGraphicsEngine().setCursor(CursorTypes.HAND);
			}
			String nameFigure = "";
			for (Shape shape : contextGraphics.getResizeList())
				if (shape.thisShape(startPoint)) {
					nameFigure = shape.getFigure();
					setResizeCursor(contextGraphics, nameFigure);
					break;
				}
		}
	}

	@Override
	public void doMousePressed(ContextGraphics contextGraphics, Point startPoint) {
		this.startPoint = startPoint;
		if (resize_move) {
			for (int i = 0; i < contextGraphics.getResizeList().size(); i++)
				if (contextGraphics.getResizeList().get(i).thisShape(startPoint)) {
					indexResize = i;
					resize_set = true;
					chek_resize_figure = true;
					break;
				} else {
					resize_set = false;
					chek_resize_figure = false;
				}
		}
	}

	@Override
	public void doMouseReleased(ContextGraphics contextGraphics, Point endPoint) {
		if (resize_set) {
			double moveX = endPoint.getX() - startPoint.getX();
			double moveY = endPoint.getY() - startPoint.getY();
			String nameMovePoint = contextGraphics.getResizeList().get(indexResize).getFigure();
			setResizeFigure(contextGraphics, nameMovePoint, moveX, moveY);
			contextGraphics.setResizeList(contextGraphics.getSheet().prepareResize(indexMove));
			reDrow(contextGraphics);
			resize_move = false;
		}
		if (chek_resize_figure) {
			contextGraphics.setResizeList(contextGraphics.getSheet().prepareResize(indexMove));
			resize_move = true;
		} else {
			for (int i = contextGraphics.getSheet().size() - 1; i >= 0; i--)
				if (contextGraphics.getSheet().thisShape(i, startPoint)) {
					System.out.println("chek = " + chek_resize_figure);
					chek_resize_figure = true;
					indexMove = i;
					contextGraphics.setResizeList(contextGraphics.getSheet().prepareResize(indexMove));
					reDrow(contextGraphics);
					contextGraphics.getGraphicsEngine().setCursor(CursorTypes.DEFAULT);
					break;
				} else {
					System.out.println("chek = " + chek_resize_figure);
					chek_resize_figure = false;
				}
		}
	}

	private void setResizeCursor(ContextGraphics contextGraphics, String figure) {
		switch (figure) {
		case NW:
			contextGraphics.getGraphicsEngine().setCursor(CursorTypes.NW_RESIZE);
			break;
		case NE:
			contextGraphics.getGraphicsEngine().setCursor(CursorTypes.NE_RESIZE);
			break;
		case SE:
			contextGraphics.getGraphicsEngine().setCursor(CursorTypes.SE_RESIZE);
			break;
		case SW:
			contextGraphics.getGraphicsEngine().setCursor(CursorTypes.SW_RESIZE);
			break;
		case N:
			contextGraphics.getGraphicsEngine().setCursor(CursorTypes.N_RESIZE);
			break;
		case E:
			contextGraphics.getGraphicsEngine().setCursor(CursorTypes.E_RESIZE);
			break;
		case S:
			contextGraphics.getGraphicsEngine().setCursor(CursorTypes.S_RESIZE);
			break;
		case W:
			contextGraphics.getGraphicsEngine().setCursor(CursorTypes.W_RESIZE);
			break;
		default:
			contextGraphics.getGraphicsEngine().setCursor(CursorTypes.NW_RESIZE);
			break;
		}
	}

	private void setResizeFigure(ContextGraphics contextGraphics, String figure, double moveX, double moveY) {
		switch (figure) {
		// move startPoint
		case NW:
			contextGraphics.getSheet().figureMovePoint(indexMove, 0, moveX, moveY);
			break;
		case N:
			contextGraphics.getSheet().figureMovePoint(indexMove, 0, 0, moveY);
			break;
		case W:
			contextGraphics.getSheet().figureMovePoint(indexMove, 0, moveX, 0);
			break;
		// move endPoint
		case SE:
			contextGraphics.getSheet().figureMovePoint(indexMove, 1, moveX, moveY);
			break;
		case E:
			contextGraphics.getSheet().figureMovePoint(indexMove, 1, moveX, 0);
			break;
		case S:
			contextGraphics.getSheet().figureMovePoint(indexMove, 1, 0, moveY);
			break;
		// move startPoint & endPoint
		case NE:
			contextGraphics.getSheet().figureMovePoint(indexMove, 0, 0, moveY);
			contextGraphics.getSheet().figureMovePoint(indexMove, 1, moveX, 0);
			break;
		case SW:
			contextGraphics.getSheet().figureMovePoint(indexMove, 0, moveX, 0);
			contextGraphics.getSheet().figureMovePoint(indexMove, 1, 0, moveY);
			break;
		default:
			int nPoint = Integer.parseInt(figure);
			contextGraphics.getSheet().figureMovePoint(indexMove, nPoint, moveX, moveY);
			break;
		}
	}

	private void reDrow(ContextGraphics contextGraphics) {
		contextGraphics.getGraphicsEngine().clearCanvas(new RGBColor(1.0, 1.0, 1.0, 1.0));
		contextGraphics.getSheet().draw();
		contextGraphics.getSheet().redrowResizeList(indexMove);
		contextGraphics.addUndone();
	}

}
