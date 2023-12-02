
public class Bounds {
	
	private float w;
	private float h;
	
	private float x;
	private float y;
	
	private float topB;
	private float bottomB;
	private float rightB;
	private float leftB;
	
	/**
	 * Creates a new Bounds object
	 * @param x - x pos
	 * @param y - y pos
	 * @param w - width
	 * @param h - height
	 */
	Bounds(float x, float y, float w, float h) {
		this.w = w;
		this.h = h;
		
		this.x = x;
		this.y = y;
		
		this.topB = y - h/2;
		this.bottomB = y + h/2;
		this.rightB = x + w/2;
		this.leftB = x - w/2;
	}
	
	/**
	 * updates this Bounds object
	 * @param x
	 * @param y
	 */
	void update(float x, float y) {
		this.topB = y - h/2;
		this.bottomB = y + h/2;
		this.rightB = x + w/2;
		this.leftB = x - w/2;
		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * returns true if this bounds is intersecting with that bounds
	 * @param that
	 */
	boolean checkCollision(Bounds that) {
		return (that.getLeftB() <= rightB && that.getRightB() >= leftB && that.getBottomB() >= topB && that.getTopB() <= bottomB);
	}

	public float getTopB() {
		return topB;
	}

	public float getBottomB() {
		return bottomB;
	}

	public float getRightB() {
		return rightB;
	}
	
	public void setRightB(float amt) {
		rightB = amt;
	}

	public float getLeftB() {
		return leftB;
	}
	
	public void setLeftB(float amt) {
		leftB = amt;
	}
	
	public float getH() {
		return h;
	}
	
	public float getW() {
		return w;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

}
