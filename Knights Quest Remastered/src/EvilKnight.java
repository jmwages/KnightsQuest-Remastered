import processing.core.PApplet;

public class EvilKnight extends Enemy {

	/**
	 * creates a new EvilKnight Enemy
	 * @param x
	 * @param y
	 */
	EvilKnight(int x, int y) {
		super(x, y, Constants.playerSize, Constants.playerSize, 60, 10, 1000, Constants.enemySpeed);
	}

	@Override
	void jump(Player p) {
		
		if (p.getBounds().getBottomB() < b.getTopB() && b.getLeftB() > Constants.eJumpZoneL
				&& b.getRightB() < Constants.eJumpZoneR && !onPlatform && gravity == 0) {
			gravity = -Constants.maxGrav;
			Sounds.jump.play();
		}
	}
	
	@Override
	PApplet render(PApplet c) {
		
		if (attacking) {
			if (facingR) {
				currAnimation = Animations.ekAtkR;
			} else {
				currAnimation = Animations.ekAtkL;
			}
		} else {
			if (facingR) {
				currAnimation = Animations.ekR;
			} else {
				currAnimation = Animations.ekL;
			}
		}
		
		currAnimation.display(x, y, w, h);
		
		if (health < maxHealth) {
			renderHealth(c);
		}

		return c;
	}

}
