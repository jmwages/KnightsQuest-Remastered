import processing.core.PApplet;

public class Bull extends Enemy {
	
	/**
	 * Creates a new Bull Enemy
	 * @param x
	 * @param y
	 */
	Bull(int x, int y) {
		super(x, y, Constants.bullSize, Constants.bullSize, 120, 30, 1800, Constants.enemySpeed);
	}
	
	@Override
	PApplet render(PApplet c) {
		
		if (attacking) {
			if (facingR) {
				currAnimation = Animations.bullAtkR;
			} else {
				currAnimation = Animations.bullAtkL;
			}
		} else {
			if (facingR) {
				currAnimation = Animations.bullR;
			} else {
				currAnimation = Animations.bullL;
			}
		}
		
		currAnimation.display(x, y, w, h);
		
		if (health < maxHealth) {
			renderHealth(c);
		}

		return c;
	}

}
