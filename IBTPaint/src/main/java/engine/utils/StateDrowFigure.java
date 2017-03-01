package engine.utils;

import static figures.utils.constants.Constants.LINE;

import java.util.ArrayList;
import java.util.List;

import engine.ContextGraphics;
import engine.State;
import figures.Figure;
import figures.utils.IShape;
import figures.utils.impl.Line;
import figures.utils.impl.Point;

/**
 * @author IrynaTkachova
 *
 */

public class StateDrowFigure implements State {
	public Point startPoint;
	List<Point> points;
	private static boolean drowPoligone = false;

	public StateDrowFigure(ContextGraphics contextGraphics) {
		contextGraphics.setState(this);
		points = new ArrayList<Point>();
	}

	@Override
	public void doMouseMoved(ContextGraphics contextGraphics, Point startPoint) {

	}

	@Override
	public void doMousePressed(ContextGraphics contextGraphics, Point startPoint) {
		this.startPoint = startPoint;
		points.add(startPoint);
		switch (contextGraphics.getCurrentButton()) {
		case TRIANGLE:
			if (points.size() == 3) {
				drowPoligone = true;
			}
			if (points.size() > 1) {
				int nPoints = points.size();
				for (int i = 0; i < nPoints; i++) {
					if (i + 1 < nPoints) {
						IShape line = new Line(LINE, points.get(i), points.get(i + 1));
						line.setGraphicsFigure(contextGraphics.getGraphicsEngine());
						line.draw(contextGraphics.getGraphicsEngine());
						System.out.println("points>>22 i=" + i);
					}
				}
			}
			break;
		case POLIGON:
			if (points.size() == contextGraphics.getCountPoint()) {
				drowPoligone = true;
			}
			if (points.size() > 1) {
				int nPoints = points.size();
				for (int i = 0; i < nPoints; i++) {
					if (i + 1 < nPoints) {
						IShape line = new Line(LINE, points.get(i), points.get(i + 1));

						line.setGraphicsFigure(contextGraphics.getGraphicsEngine());
						line.draw(contextGraphics.getGraphicsEngine());
					}
				}
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void doMouseReleased(ContextGraphics contextGraphics, Point endPoint) {
		switch (contextGraphics.getCurrentButton()) {
		case LINE:
			contextGraphics.setCurrentGraphics();
			Figure.Line(contextGraphics.getSheet(), startPoint, endPoint);
			contextGraphics.addUndone();
			break;
		case CIRCLE:
			contextGraphics.setCurrentGraphics();
			Figure.Circle(contextGraphics.getSheet(), startPoint, endPoint);
			contextGraphics.addUndone();
			break;
		case ELLIPSE:
			contextGraphics.setCurrentGraphics();
			Figure.Ellipse(contextGraphics.getSheet(), startPoint, endPoint);
			contextGraphics.addUndone();
			break;
		case RECTANGLE:
			contextGraphics.setCurrentGraphics();
			Figure.Rectangle(contextGraphics.getSheet(), startPoint, endPoint);
			contextGraphics.addUndone();
			break;
		case TRIANGLE:
		case POLIGON:
			if (drowPoligone) {
				contextGraphics.setCurrentGraphics();
				Figure.Poligon(contextGraphics.getSheet(), points);
				contextGraphics.addUndone();
				drowPoligone = false;
				points.clear();
			}
			break;
		default:
			break;
		}
	}
}
