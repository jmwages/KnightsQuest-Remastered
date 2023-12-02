import java.util.ArrayList;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class Player {

	float x;
	float y;

	int w;
	int h;

	int health;

	int speed;
	float gravity;

	boolean movingR;
	boolean movingL;
	boolean facingR;

	Animation currAnimation;

	float floorLvl;

	Bounds b;
	Bounds atkBounds;

	boolean attackRdy;

	/**
	 * creates a new player object
	 */
	Player() {
		this.w = Constants.playerSize;
		this.h = Constants.playerSize;

		this.x = Constants.screenW / 2;
		this.y = Constants.bottomLvl - h / 2;

		this.health = Constants.playerMaxHealth;

		this.speed = Constants.playerSpeed;
		this.gravity = 0;

		this.movingR = false;
		this.movingL = false;

		this.currAnimation = Animations.standingR;
		this.facingR = true;

		floorLvl = Constants.bottomLvl;

		b = new Bounds(x, y, w, h);
		atkBounds = new Bounds(x, y, w, h);

		this.attackRdy = true;
	}

	PApplet render(PApplet c) {
		// c.rectMode(3);
		// c.fill(190);
		// c.rect(x, y, w, h);

		currAnimation.display(x, y, w, h);

		if (health < Constants.playerMaxHealth) {
			renderHealth(c);
		}

		return c;
	}

	/**
	 * updates the player
	 */
	void update() {
		handleGravity();
		move();

		b.update(x, y);
		setAtkBounds();

		if (b.getBottomB() > floorLvl && gravity >= 0) {
			y = floorLvl - h / 2;
		}
	}

	/**
	 * handles the affects of gravity on this player
	 */
	void handleGravity() {
		if (b.getBottomB() >= floorLvl && gravity >= 0) {
			gravity = 0;
		} else if (gravity < Constants.maxGrav) {
			gravity += Constants.gravRate;
		} else if (gravity >= Constants.maxGrav) {
			gravity = Constants.maxGrav;
		}
	}

	/**
	 * sets the floor level to the correct platform from the given platforms
	 * @param platforms
	 */
	void detectFloorLvl(Platform[] platforms) {
		for (Platform pl : platforms) {
			Bounds plB = pl.getBounds();

			if (b.checkCollision(plB) && b.getBottomB() <= plB.getTopB() + plB.getH() / 2) {
				floorLvl = plB.getTopB();
				break;
			} else {
				floorLvl = Constants.bottomLvl;
			}
		}
	}

	/**
	 * handles movement of the player
	 */
	void move() {

		if (b.getRightB() >= Constants.screenW) {
			movingR = false;
			currAnimation = Animations.standingR;
		} else if (b.getLeftB() <= 0) {
			movingL = false;
			currAnimation = Animations.standingL;
		}

		if (movingL) {
			x -= speed;
		}

		if (movingR) {
			x += speed;
		}

		y += gravity;
	}

	/**
	 * handles key presses for the player
	 * @param kev
	 */
	void moveStart(KeyEvent kev) {
		char key = kev.getKey();

		if (key == 'a' || key == 'A') {
			movingL = true;
			facingR = false;
			currAnimation = Animations.walkingL;
			Sounds.playWalking();
		}

		if (key == 'd' || key == 'D') {
			movingR = true;
			facingR = true;
			currAnimation = Animations.walkingR;
			Sounds.playWalking();
		}

		if ((key == 'w' || key == 'W') && gravity == 0) {
			Sounds.jump.play();
			gravity = -Constants.maxGrav;
		}

	}

	/**
	 * handles key releases for the player
	 * @param kev
	 */
	void moveStop(KeyEvent kev) {
		char key = kev.getKey();

		if (key == 'a' || key == 'A') {
			movingL = false;
			currAnimation = Animations.standingL;
			Sounds.walking.stop();
		}

		if (key == 'd' || key == 'D') {
			movingR = false;
			currAnimation = Animations.standingR;
			Sounds.walking.stop();

		}

		if (key == ' ') {
			attackRdy = true;
			
			if (facingR) {
				currAnimation = Animations.standingR;
			} else {
				currAnimation = Animations.standingL;
			}
		}
	}

	/**
	 * handles the affects of attacking on the given enemies and spells 
	 * @param kev
	 * @param eArray
	 * @param sArray
	 */
	void attack(KeyEvent kev, ArrayList<Enemy> eArray, ArrayList<Spell> sArray) {
		if (kev.getKey() == ' ' && attackRdy) {
			Sounds.swordSwing.play();

			if (facingR) {
				currAnimation = Animations.attackR;
			} else {
				currAnimation = Animations.attackL;
			}

			for (Enemy e : eArray) {

				if (atkBounds.checkCollision(e.getBounds())) {
					e.hit(Constants.playerStrength, this);
					Sounds.attackHit.play();
				}
			}

			for (Spell s : sArray) {

				if (atkBounds.checkCollision(s.getBounds())) {
					s.deflect();;
					Sounds.attackHit.play(); 
				}
			}
		}
	}

	/**
	 * sets the attack bounds to be extending in the correct direction
	 */
	void setAtkBounds() {
		atkBounds.update(x, y);

		if (facingR) {
			atkBounds.setRightB(atkBounds.getRightB() + Constants.playerAtkDist);
		} else {
			atkBounds.setLeftB(atkBounds.getLeftB() - Constants.playerAtkDist);
		}
	}

	PApplet renderHealth(PApplet c) {
		c.rectMode(c.CORNER);

		float barW = c.map(health, 0, Constants.playerMaxHealth, 0, w);

		if (health > Constants.playerMaxHealth * .75) {
			c.fill(0, 255, 0);
		} else if (health > Constants.playerMaxHealth * .5) {
			c.fill(255, 255, 0);
		} else {
			c.fill(255, 0, 0);
		}

		c.rect(b.getLeftB(), b.getTopB() - 10, barW, 5, 10);

		return c;
	}

	/**
	 * reduces health by the given amt
	 * @param amt
	 */
	void reduceHealth(int amt) {
		health -= amt;
	}

	Bounds getBounds() {
		return b;
	}
	
	Bounds getAtkBounds() {
		return atkBounds;
	}

	boolean isAlive() {
		if (health <= 0) {
			currAnimation = Animations.dead;
		}
		return (health > 0);
	}
	

}
