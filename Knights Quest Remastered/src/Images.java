import processing.core.*;

/*
 * Holds all the images included in the game
 */
public class Images {

	public static PImage scroll;

	// images for for the start screen
	public static PImage startBImg;
	public static PImage startBImgPressed;

	public static PImage menuBackground;

	public static PImage infoBImg;
	public static PImage infoBImgPressed;
	public static PImage manualImg;

	// images for the gameScreen
	public static PImage gameBackground;
	public static PImage platform;
	public static PImage cloud;

	// Player sprites
	public static PImage[] standingRImgs = new PImage[4];
	public static PImage[] standingLImgs = new PImage[4];

	public static PImage[] walkingRImgs = new PImage[8];
	public static PImage[] walkingLImgs = new PImage[8];

	public static PImage[] attackRImgs = new PImage[1];
	public static PImage[] attackLImgs = new PImage[1];
	
	public static PImage[] deadImgs = new PImage[1];


	// EvilKnight Sprites
	public static PImage[] ekRImgs = new PImage[7];
	public static PImage[] ekLImgs = new PImage[7];

	public static PImage[] ekAtkRImgs = new PImage[2];
	public static PImage[] ekAtkLImgs = new PImage[2];

	// Bull Sprites
	public static PImage[] bullRImgs = new PImage[8];
	public static PImage[] bullLImgs = new PImage[8];

	public static PImage[] bullAtkRImgs = new PImage[8];
	public static PImage[] bullAtkLImgs = new PImage[8];
	
	// Wizard Sprites
	public static PImage[] wizardRImgs = new PImage[4];
	public static PImage[] wizardLImgs = new PImage[4];
	
	// Spell Sprite
	public static PImage spell;
	
	// Skeleton Sprites
		public static PImage[] skeletonRImgs = new PImage[4];
		public static PImage[] skeletonLImgs = new PImage[4];

		public static PImage[] skeletonAtkRImgs = new PImage[2];
		public static PImage[] skeletonAtkLImgs = new PImage[2];
	

}
