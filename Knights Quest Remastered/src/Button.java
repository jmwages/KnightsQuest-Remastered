import processing.core.*;

public class Button {
	private int w;
	private int h;
	private int x;
	private int y;
	private PImage reg;
	private PImage pressed;
	private PImage current;
	private Bounds b;
	boolean isHidden;
	
	/**
	 * Creates a new button object
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param reg
	 * @param pressed
	 */
	Button(int x, int y, int w, int h, PImage reg, PImage pressed) {
		this.w = w;
		this.h = h;
		this.x = x;
		this.y = y;
		this.reg = reg;
		this.pressed = pressed;
		this.current = this.reg;
		this.b = new Bounds(x, y, w, h);
		this.isHidden = false;
	}
	
	/*
	 * Draws the button on the screen with the current image
	 */
	PApplet render(PApplet c) {
		
		if (!isHidden) {
		c.imageMode(3);
		c.image(current ,x, y, w, h);
		}
		
		return c;
	}
	
	/**
	 * updates the image if the mouse is hovered over the button
	 * @param mX
	 * @param mY
	 */
	void updateImg(int mX, int mY) {
		
		if ( isClickable(mX, mY) ) {
			current = pressed;
		} else {
			current = reg;
		}
	}
	
	/**
	 * returns whether the mouse is over the button
	 * @param mX
	 * @param mY
	 */
	boolean isClickable(int mX, int mY) {
		return( mX > b.getLeftB() && mX < b.getRightB() && mY > b.getTopB() && mY < b.getBottomB() & !isHidden);
	}
	
	/*
	 * hides the button
	 */
	void hide() {
		isHidden = true;
	}
	
	/*
	 * shows the button
	 */
	void show() {
		isHidden = false;
	}
	
}
