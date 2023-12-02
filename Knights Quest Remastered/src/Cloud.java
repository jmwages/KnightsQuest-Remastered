import processing.core.PApplet;

public class Cloud {
	
	int x;
	int y;
	
	int w;
	int h;

	int speed;
	
	/**
	 * creates a new Cloud object
	 */
	Cloud() {
		this.x = Constants.rgen.nextInt(Constants.screenW+1);
		this.y = 100 + Constants.rgen.nextInt(150);
		
		this.w = 256;
		this.h = 128;
		
		this.speed = 1;
		
	}
	
	PApplet render(PApplet c) {
		c.imageMode(3);
		c.image(Images.cloud, x, y, w, h);
		
		return c;
	}
	
	/**
	 * updates this cloud
	 */
	void update() {
		x += speed;
		
		if (x > Constants.screenW + w/2) {
			x = -w/2;
			y = 100 + Constants.rgen.nextInt(150);
		}
	}
}
