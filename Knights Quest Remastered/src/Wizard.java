import java.util.ArrayList;

import processing.core.PApplet;

public class Wizard extends Enemy {

	ArrayList<Spell> spells;
	ArrayList<Enemy> eArray;

	int skeletonInterval;
	long skeletonTimerStart;

	/**
	 * Creates a new Wizard Enemy
	 * @param x
	 * @param y
	 * @param eArray
	 * @param spells
	 */
	Wizard(int x, int y, ArrayList<Enemy> eArray, ArrayList<Spell> spells) {
		super(x, y, 90, 90, 120, 0, 3000, 0);

		redirectInterval = 0;

		skeletonInterval = 10000;
		skeletonTimerStart = System.currentTimeMillis();

		this.spells = spells;
		this.eArray = eArray;
	}

	@Override
	PApplet render(PApplet c) {
		if (facingR) {
			currAnimation = Animations.wizardR;
		} else {
			currAnimation = Animations.wizardL;
		}

		currAnimation.display(x, y, w, h);

		if (health < maxHealth) {
			renderHealth(c);
		}

		return c;
	}

	@Override
	void move() {
		y += gravity;
	}

	@Override
	void attack(Player p) {
		if (System.currentTimeMillis() - attackTimerStart >= attackInt) {

			if (facingR) {
				spells.add(new Spell(b.getRightB(), y, true));
				Sounds.spellCast.play();
			} else {
				spells.add(new Spell(b.getLeftB(), y, false));
				Sounds.spellCast.play();
			}

			attackTimerStart = System.currentTimeMillis();
		}
	}
	
	/**
	 * Spawns three Skeletons on each tick of the spawn timer
	 */
	@Override 
	void spawnSkeletons() {
		if (System.currentTimeMillis() - skeletonTimerStart >= skeletonInterval) {
			eArray.add(new Skeleton(x, y));
			eArray.add(new Skeleton(b.getRightB(), y));
			eArray.add(new Skeleton(b.getLeftB(), y));
			Sounds.skeletonSummon.play();

			skeletonTimerStart = System.currentTimeMillis();
		}
	}

	@Override
	void targetPlayer(Player p) {

		if (p.getBounds().getTopB() <= b.getBottomB() && p.getBounds().getBottomB() >= b.getTopB()) {
			attack(p);
		}

		if (b.checkCollision(p.getAtkBounds())) {
			if (p.getBounds().getX() > x && y > Constants.screenH / 2) {
				x = Constants.screenW / 2 + Constants.rgen.nextInt(Constants.screenW - Constants.screenW / 2);
			} else if (p.getBounds().getX() < x && y > Constants.screenH / 2) {
				x = Constants.rgen.nextInt(Constants.screenW / 2);
			} else if (p.getBounds().getX() < x && y < Constants.screenH / 2) {
				x = 150;
			} else if (p.getBounds().getX() > x && y < Constants.screenH / 2) {
				x = Constants.screenW - 150;
			}
			Sounds.teleport.play();

		} else if (System.currentTimeMillis() - redirectTimerStart >= redirectInterval) {
			if (p.getBounds().getX() > x) {
				facingR = true;
			} else {
				facingR = false;
			}

			redirectTimerStart = System.currentTimeMillis();

		}

	}
	
	/**
	 * Reinitializes the timers 
	 */
	@Override
	void initTimers() {
		attackTimerStart = System.currentTimeMillis();
		redirectTimerStart = System.currentTimeMillis();
		skeletonTimerStart = System.currentTimeMillis();
		}

}
