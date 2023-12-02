import processing.core.PApplet;

public abstract class Enemy {

	protected int w;
	protected int h;

	protected float x;
	protected float y;
	
	protected Animation currAnimation;

	protected Bounds b;

	protected float floorLvl;

	protected int strength;
	protected int attackInt;
	protected long attackTimerStart;
	protected boolean attacking;

	protected int health;
	protected int maxHealth;
	
	protected int pointVal;

	protected int speed;

	protected float gravity;

	protected boolean movingL;
	protected boolean movingR;
	protected boolean facingR;

	protected boolean onPlatform;
	protected float currPlatR;
	protected float currPlatL;

	protected int redirectInterval;
	protected long redirectTimerStart;

	/**
	 * Creates a new Enemy
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param health
	 * @param strength
	 * @param attackInt
	 * @param speed
	 */
	Enemy(float x, float y, int w, int h, int health, int strength, int attackInt, int speed) {
		this.w = w;
		this.h = h;

		this.x = x;
		this.y = y;
		
		this.floorLvl = Constants.bottomLvl;

		this.b = new Bounds(x, y, w, h);

		this.strength = strength;
		this.attackInt = attackInt;
		this.attacking = false;

		this.health = health;
		this.maxHealth = health;
		
		this.pointVal = 1;

		this.speed = speed;

		this.gravity = 0;

		if (x < Constants.screenW / 2) {
			movingR = true;
			movingL = false;
		} else {
			movingR = false;
			movingL = true;
		}

		this.redirectInterval = 2500;
		this.redirectTimerStart = redirectTimerStart = System.currentTimeMillis();

	}

	PApplet render(PApplet c) {
		c.rectMode(3);
		c.fill(255, 0, 0);
		c.rect(x, y, w, h);
		
		if (health < maxHealth) {
			renderHealth(c);
		}

		return c;
	}

	void update() {
		handleGravity();
		move();

		b.update(x, y);

		if (b.getBottomB() > floorLvl && gravity >= 0) {
			y = floorLvl - h / 2;
		}
	}

	/**
	 * handles the movement of this Enemy
	 */
	void move() {
		if (b.getRightB() >= Constants.screenW) {
			movingR = false;
			movingL = true;
		} else if (b.getLeftB() <= 0) {
			movingL = false;
			movingR = true;
		}

		if (movingL) {
			x -= speed;
			facingR = false;
		}

		if (movingR) {
			x += speed;
			facingR = true;
		}

		y += gravity;

	}

	/**
	 * attacks the given player on every tick of the attack interval
	 * @param p
	 */
	void attack(Player p) {
		if (System.currentTimeMillis() - attackTimerStart >= attackInt) {
			p.reduceHealth(strength);
			Sounds.attackHit.play();
			attackTimerStart = System.currentTimeMillis();
		}
	}

	/**
	 * Handles following and checking collision of the given player
	 * @param p
	 */
	void targetPlayer(Player p) {

		if (b.checkCollision(p.getBounds())) {
			movingR = false;
			movingL = false;
			attack(p);
			attacking = true;

		} else if (System.currentTimeMillis() - redirectTimerStart >= redirectInterval) {
			if (p.getBounds().getX() > x) {
				movingR = true;
				movingL = false;
			} else {
				movingR = false;
				movingL = true;
			}
			
			attacking = false;

			redirectTimerStart = System.currentTimeMillis();

		}

		jump(p);
	}

	/**
	 * controls the gravity of this Enemy
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
	 * handles jumping to follow the player
	 * @param p
	 */
	void jump(Player p) {
		if (gravity == 0) {
			if (p.getBounds().getBottomB() < b.getTopB() && b.getLeftB() > Constants.eJumpZoneL
					&& b.getRightB() < Constants.eJumpZoneR && !onPlatform) {
				gravity = -Constants.maxGrav;
				Sounds.jump.play();
			} else if (p.getBounds().getBottomB() < b.getTopB()
					&& ( (b.getLeftB() <= currPlatL && movingL) || (b.getRightB() >= currPlatR && movingR) )
					&& b.getBottomB() > Constants.screenH / 2 && onPlatform) {
				gravity = -Constants.maxGrav;
				Sounds.jump.play();
			}
		}
	}

	/**
	 * chooses the correct floor level from the given platforms
	 * @param platforms
	 */
	void detectFloorLvl(Platform[] platforms) {
		for (Platform pl : platforms) {
			Bounds plB = pl.getBounds();

			if (b.checkCollision(plB) && b.getBottomB() <= plB.getTopB() + plB.getH()/2) {
				floorLvl = plB.getTopB();
				currPlatR = plB.getRightB();
				currPlatL = plB.getLeftB();
				break;
			} else {
				floorLvl = Constants.bottomLvl;
			}

			if (b.getBottomB() < Constants.bottomLvl) {
				onPlatform = true;
			} else {
				onPlatform = false;
			}
		}
	}
	
	/**
	 * renders the health bar of the enemy
	 * @param c
	 * @return
	 */
	PApplet renderHealth(PApplet c) {
		c.rectMode(c.CORNER);

		float barW = c.map(health, 0, maxHealth, 0, w);

		if (health > maxHealth * .75) {
			c.fill(0, 255, 0);
		} else if (health > maxHealth * .5) {
			c.fill(255, 255, 0);
		} else {
			c.fill(255, 0, 0);
		}

		c.rect(b.getLeftB(), b.getTopB() - 10, barW, 5, 10);

		return c;
	}
	
	void spawnSkeletons() {
		
	}
	
	/**
	 * handles the effects of being hit by the player
	 * @param amt
	 * @param p
	 */
	void hit(int amt, Player p) {
		health -= amt;
		
		if (p.getBounds().getX() < x) {
			x += Constants.hitKnockback;
		} else if (p.getBounds().getX() > x) {
			x -= Constants.hitKnockback;
		}
	}
	
	/**
	 * Reinitializes the timers 
	 */
	void initTimers() {
		attackTimerStart = System.currentTimeMillis();
		redirectTimerStart = System.currentTimeMillis();
	}
	
	/**
	 * handles the affects of being hit by something other than the player
	 * @param amt
	 */
	void hit(int amt) {
		health -= amt;
	}

	public int getPointVal() {
		return pointVal;
	}
	
	public Bounds getBounds() {
		return b;
	}
	
	public int getHealth() {
		return health;
	}
}
