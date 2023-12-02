import java.util.ArrayList;
import processing.core.PApplet;

public class Spell {
	
	private float x; 
	private float y;
	
	private int w;
	private int h;
	
	private Bounds b;
	
	private int speed;
	private int strength;
	
	private boolean beenDeflected;
	private boolean movingR;
	private boolean hitSomething;
	
	Player p;
	ArrayList<Enemy> e;
	
	/**
	 * Creates a new Spell object
	 * @param x
	 * @param y
	 * @param movingR
	 * @param p
	 * @param e
	 */
	Spell(float x, float y, boolean movingR) {	
		this.x = x;
		this.y = y;
		
		this.w = Constants.spellSize;
		this.h = Constants.spellSize;
		
		this.b = new Bounds(x, y, w, h);
		
		this.speed = Constants.spellSpeed;
		this.strength = 30;
		
		this.movingR = movingR;
		this.beenDeflected = false;
		this.hitSomething = false;

		
	}
	
	PApplet render(PApplet c) {
		c.imageMode(3);
		c.image(Images.spell, x, y, w, h);
		
		return c;
	}
	
	/**
	 * updates this spell
	 * @param p
	 * @param e
	 */
	void update(Player p, ArrayList<Enemy> e) {
		this.p = p;
		this.e = e;
		
		move();
		targetGroup();

		
		b.update(x, y);
		
		if (b.getLeftB() >= Constants.screenW || b.getRightB() <= 0) {
			hitSomething = true;
		}
		
	}
	
	void move() {
		if (movingR) {
			x += speed;
		} else {
			x -= speed;
		}
	}
	
	/**
	 * handles damaging the correct objects depending on whether the spell has been deflected
	 */
	void targetGroup() {
		if (!beenDeflected && b.checkCollision(p.getBounds())) {
			p.reduceHealth(strength);
			hitSomething = true;
		} else if (beenDeflected) {
			for (Enemy en : e) {
				if (b.checkCollision(en.getBounds())) {
					en.hit(strength);
					hitSomething = true;
				}
			}
		}
	}
	
	void deflect() {
		
		if (!beenDeflected) {
			beenDeflected = true;
			movingR = !movingR;
		}
	}
	
	
	
	Bounds getBounds() {
		return b;
	}
	
	boolean hitSomething() {
		return hitSomething;
	}
 
}
