package engine;
/**
 * @author IrynaTkachova
 *
 */

import engine.utils.CursorTypes;
import figures.utils.impl.RGBColor;

public interface GraphicsEngine {
	public void setCursor(CursorTypes cursorTypes);

	public void clearCanvas(RGBColor colorSpace);

	public RGBColor getColorFill();

	public RGBColor getColorLine();

	public double getLineWidth();

	public double[] getLineDashes();

	public void setColorFill(RGBColor color);

	public void setColorLine(RGBColor color);

	public void setLineWidth(double lineWidth);

	public void setLineDashes(double[] lineDashes);

	public void strokeRect(double x, double y, double w, double h);

	public void fillRect(double x, double y, double w, double h);

	public void strokeOval(double x, double y, double w, double h);

	public void fillOval(double x, double y, double w, double h);

	public void strokeLine(double x1, double y1, double x2, double y2);

	public void strokePolygon(double[] xPoints, double[] yPoints, int nPoints);

	public void fillPolygon(double[] xPoints, double[] yPoints, int nPoints);

}
