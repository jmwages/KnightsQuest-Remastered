import processing.core.*;

public class ScreenWipe {
	boolean isGrow;

	int w;
	int h;

	/**
	 * Creates a new ScreenWipe Object that can grow or shrink depending on boolean
	 * @param isGrow
	 */
	ScreenWipe(boolean isGrow) {

		this.isGrow = isGrow;

		if (isGrow) {
			this.w = Constants.screenW / 20;
			this.h = Constants.screenH / 20;
		} else {
			this.w = Constants.screenW;
			this.h = Constants.screenH;
		}
	}

	PApplet render(PApplet c) {
		c.rectMode(3);
		c.fill(0);
		c.rect(Constants.screenW / 2, Constants.screenH / 2, w, h);

		return c;
	}

	void grow() {
		if (isGrow) {
			w += 30;
			h += 20;
		} else {
			w *= .88;
			h *= .88;
		}
	}

	boolean wipeDone() {
		if (isGrow) {
			return (w > Constants.screenW + 250);
		} else {
			return (w < Constants.screenH / 10);
		}
	}

}
