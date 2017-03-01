package figures;

import static figures.utils.constants.Constants.CIRCLE;
import static figures.utils.constants.Constants.COMBINED;
import static figures.utils.constants.Constants.ELLIPSE;
import static figures.utils.constants.Constants.LINE;
import static figures.utils.constants.Constants.POLIGON;
import static figures.utils.constants.Constants.RECTANGLE;

import java.util.List;

import figures.utils.IShape;
import figures.utils.impl.Circle;
import figures.utils.impl.Combined;
import figures.utils.impl.Ellipse;
import figures.utils.impl.Line;
import figures.utils.impl.Point;
import figures.utils.impl.Polygon;
import figures.utils.impl.Rectangle;

/**
 * @author IrynaTkachova
 *
 */

public class Figure {

	public static IShape Line(Sheet sheet, Point startPoint, Point endPoint) {
		IShape line = new Line(LINE, startPoint, endPoint);
		sheet.addFigure(line);
		return line;
	}

	public static IShape Ellipse(Sheet sheet, Point startPoint, Point endPoint) {
		IShape ellipse = new Ellipse(ELLIPSE, startPoint, endPoint);
		sheet.addFigure(ellipse);
		return ellipse;
	}

	public static IShape Rectangle(Sheet sheet, Point startPoint, Point endPoint) {
		IShape rectangle = new Rectangle(RECTANGLE, startPoint, endPoint);
		sheet.addFigure(rectangle);
		return rectangle;
	}

	public static IShape Circle(Sheet sheet, Point startPoint, Point endPoint) {
		IShape circle = new Circle(CIRCLE, startPoint, endPoint);
		sheet.addFigure(circle);
		return circle;
	}

	public static IShape Poligon(Sheet sheet, List<Point> points) {
		IShape poligon = new Polygon(POLIGON, points);
		sheet.addFigure(poligon);
		return poligon;
	}

	public static IShape Combined(Sheet sheet, List<IShape> tempShapes) {
		Point point = new Point();
		Combined combined = new Combined(COMBINED, point, point);
		combined.addFigures(tempShapes);
		sheet.addFigure((IShape) combined);
		return combined;
	}

	// public static Shape Triangle(GraphicsContext gContext) {
	// return new Triangle(gContext, TRIANGLE);
	// }
	//
	// public static Shape Poligon(GraphicsContext gContext) {
	// return new Poligon(gContext, POLIGON);
	// }
}