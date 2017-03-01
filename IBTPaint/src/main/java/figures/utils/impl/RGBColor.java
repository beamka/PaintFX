package figures.utils.impl;

import java.io.Serializable;

public class RGBColor implements Serializable {
	public final double R;
	public final double G;
	public final double B;
	public final double opacity;

	public RGBColor(double R, double G, double B, double opacity) {
		this.R = R;
		this.G = G;
		this.B = B;
		this.opacity = opacity;
	}

	public RGBColor darker() {
		double r = R / 2;
		double g = G / 2;
		double b = B / 2;
		double o = opacity / 2;
		RGBColor rgb = new RGBColor(r, g, b, o);
		return rgb;
	}
}
