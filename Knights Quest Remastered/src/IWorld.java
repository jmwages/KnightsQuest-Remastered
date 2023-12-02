import processing.core.PApplet;
import processing.event.*;

/**
 * Represents a view being displayed on the screen (e.g. welcome screen, the game, game over screen)
 */
public interface IWorld {
	/**
	 * Renders this world on the given canvas
	 * @param c - the canvas to render onto
	 * @return the updated canvas
	 */
	PApplet draw(PApplet c);
	
	/**
	 * Produces an updated world
	 */
	IWorld update();
	
	/**
	 * Handles key pressed events for this world
	 * @param kev - the key event to process
	 * @return the updated world
	 */
	IWorld keyPressed(KeyEvent kev);
	
	IWorld keyReleased(KeyEvent kev);
	
	/**
	 * Handles mouse events for this world
	 * @param mev - the mouse event to process
	 * @return the updated world
	 */
	IWorld mousePressed(MouseEvent mev);

	

	
}
