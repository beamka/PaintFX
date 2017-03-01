package engine.JavaFX;

import engine.GraphicsEngine;
import engine.utils.CursorTypes;
import figures.utils.impl.RGBColor;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author IrynaTkachova
 *
 */

public class JavaFX implements GraphicsEngine {
	private GraphicsContext gContext;
	private Canvas canvas;

	public JavaFX(GraphicsContext gContext, Canvas canvas) {
		this.gContext = gContext;
		this.canvas = canvas;
	}

	public void setGraphicsContext(GraphicsContext gContext) {
		this.gContext = gContext;
	}

	@Override
	public void clearCanvas(RGBColor colorSpace) {
		setColorFill(colorSpace);
		fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	@Override
	public void setCursor(CursorTypes cursorTypes) {
		switch (cursorTypes) {
		case DISAPPEAR:
			canvas.setCursor(Cursor.DISAPPEAR);
			break;
		case CROSSHAIR:
			canvas.setCursor(Cursor.CROSSHAIR);
			break;
		case DEFAULT:
			canvas.setCursor(Cursor.DEFAULT);
			break;
		case HAND:
			canvas.setCursor(Cursor.HAND);
			break;
		case CLOSED_HAND:
			canvas.setCursor(Cursor.CLOSED_HAND);
			break;
		case OPEN_HAND:
			canvas.setCursor(Cursor.OPEN_HAND);
			break;
		case NW_RESIZE:
			canvas.setCursor(Cursor.NW_RESIZE);
			break;
		case NE_RESIZE:
			canvas.setCursor(Cursor.NE_RESIZE);
			break;
		case SE_RESIZE:
			canvas.setCursor(Cursor.SE_RESIZE);
			break;
		case SW_RESIZE:
			canvas.setCursor(Cursor.SW_RESIZE);
			break;
		case N_RESIZE:
			canvas.setCursor(Cursor.N_RESIZE);
			break;
		case E_RESIZE:
			canvas.setCursor(Cursor.E_RESIZE);
			break;
		case S_RESIZE:
			canvas.setCursor(Cursor.S_RESIZE);
			break;
		case W_RESIZE:
			canvas.setCursor(Cursor.W_RESIZE);
			break;
		default:
			break;
		}
	}

	@Override
	public RGBColor getColorFill() {
		Color c = (Color) gContext.getFill();
		double r = c.getRed();
		double g = c.getGreen();
		double b = c.getBlue();
		double opacity = c.getOpacity();
		RGBColor rgb = new RGBColor(r, g, b, opacity);
		return rgb;
	}

	@Override
	public RGBColor getColorLine() {
		Color c = (Color) gContext.getStroke();
		double r = c.getRed();
		double g = c.getGreen();
		double b = c.getBlue();
		double opacity = c.getOpacity();
		RGBColor rgb = new RGBColor(r, g, b, opacity);
		return rgb;
	}

	@Override
	public double getLineWidth() {
		return gContext.getLineWidth();
	}

	@Override
	public double[] getLineDashes() {
		return gContext.getLineDashes();
	}

	@Override
	public void setColorFill(RGBColor color) {
		Color c = new Color(color.R, color.G, color.B, color.opacity);
		gContext.setFill(c);
	}

	@Override
	public void setColorLine(RGBColor color) {
		Color c = new Color(color.R, color.G, color.B, color.opacity);
		gContext.setStroke(c);
	}

	@Override
	public void setLineWidth(double lineWidth) {
		gContext.setLineWidth(lineWidth);
	}

	@Override
	public void setLineDashes(double[] lineDashes) {
		gContext.setLineDashes(lineDashes);
	}

	@Override
	public void strokeRect(double x, double y, double w, double h) {
		gContext.strokeRect(x, y, w, h);
	}

	@Override
	public void fillRect(double x, double y, double w, double h) {
		gContext.fillRect(x, y, w, h);
	}

	@Override
	public void strokeOval(double x, double y, double w, double h) {
		gContext.strokeOval(x, y, w, h);
	}

	@Override
	public void fillOval(double x, double y, double w, double h) {
		gContext.fillOval(x, y, w, h);
	}

	@Override
	public void strokeLine(double x1, double y1, double x2, double y2) {
		gContext.strokeLine(x1, y1, x2, y2);
	}

	@Override
	public void strokePolygon(double[] xPoints, double[] yPoints, int nPoints) {
		gContext.strokePolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void fillPolygon(double[] xPoints, double[] yPoints, int nPoints) {
		gContext.fillPolygon(xPoints, yPoints, nPoints);
	}
}
