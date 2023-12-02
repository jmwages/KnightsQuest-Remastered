import processing.core.PApplet;

public class Skeleton extends Enemy {

	/**
	 * Creates a new Skeleton Enemy
	 * @param x
	 * @param y
	 */
	Skeleton(float x, float y) {
		super(x, y, Constants.skeletonSize, Constants.skeletonSize, 1, 6, 500, Constants.skeletonSpeed);
		
		pointVal = 0;
		
	}
	
	@Override
	PApplet render(PApplet c) {
		
		if (attacking) {
			if (facingR) {
				currAnimation = Animations.skeletonAtkR;
			} else {
				currAnimation = Animations.skeletonAtkL;
			}
		} else {
			if (facingR) {
				currAnimation = Animations.skeletonR;
			} else {
				currAnimation = Animations.skeletonL;
			}
		}
		
		currAnimation.display(x, y, w, h);
		
		if (health < maxHealth) {
			renderHealth(c);
		}
		
		return c;
	}
	
	
}
