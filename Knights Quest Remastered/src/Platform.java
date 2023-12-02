import processing.core.PApplet;

public class Platform {
	
	private int x;
	private int y;
	
	private int w;
	private int h;
	
	private Bounds b;

	/**
	 * creates a new platform with a specific width and height
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	Platform(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
		
		this.b = new Bounds(x, y, w, h);
	}
	
	/**
	 * creates a new platform
	 * @param x
	 * @param y
	 * @param w
	 */
	Platform(int x, int y, int w) {
		this(x, y, w, 100);
	}
	
	PApplet render(PApplet c) {
		c.imageMode(3);
		c.image(Images.platform, x, y, w, h);
		
		return c;
	}
	
	Bounds getBounds() {
		return b;
	}

}
