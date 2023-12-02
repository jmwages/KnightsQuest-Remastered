import processing.core.*;
import processing.event.*;
import processing.sound.SoundFile;

/**
 * Provides the scaffolding to launch a Processing application
 */
public class KQApp extends PApplet {
	private IWorld w;

	public void settings() {
		// IF SWITCHING MAKE SURE TO UNCOMMENT CORRECT CONSTANTS //
		
		//this.size(Constants.screenW, Constants.screenH); 
		this.size(Constants.screenW, Constants.screenH, P2D); // better performance
	}

	public void setup() {
		loadSounds();
		loadImages();
		initAnimations();
		loadSounds();
		Fonts.main = createFont("mainFont.ttf", 100);

		w = new StartScreen();
	}

	public void draw() {
		w = w.update();

		w.draw(this);

	}

	public void keyPressed(KeyEvent kev) {
		w = w.keyPressed(kev);
	}

	public void keyReleased(KeyEvent kev) {
		w = w.keyReleased(kev);
	}

	public void mousePressed(MouseEvent mev) {
		w = w.mousePressed(mev);
	}

	public static void main(String[] args) {
		PApplet.runSketch(new String[] { "Knights Quest" }, new KQApp());
	}

	void loadImages() {
		Images.scroll = loadImage("wideScroll.png");
		Images.startBImg = loadImage("startButton.PNG");
		Images.startBImgPressed = loadImage("startButton2.PNG");
		Images.menuBackground = loadImage("titleScreen.PNG");
		Images.infoBImg = loadImage("instructions.PNG");
		Images.infoBImgPressed = loadImage("instructions2.PNG");
		Images.manualImg = loadImage("manual.PNG");

		Images.gameBackground = loadImage("Background.PNG");
		Images.platform = loadImage("Grass1.PNG");
		Images.cloud = loadImage("cloud.PNG");

		Images.spell = loadImage("spell.PNG");

		// For Player
		for (int i = 0; i < Images.standingRImgs.length; i++) {
			Images.standingRImgs[i] = loadImage("standingR" + i + ".png");
		}
		for (int i = 0; i < Images.standingLImgs.length; i++) {
			Images.standingLImgs[i] = loadImage("standingL" + i + ".png");
		}
		for (int i = 0; i < Images.walkingRImgs.length; i++) {
			Images.walkingRImgs[i] = loadImage("walkingR" + i + ".png");
		}
		for (int i = 0; i < Images.walkingLImgs.length; i++) {
			Images.walkingLImgs[i] = loadImage("walkingL" + i + ".png");
		}
		for (int i = 0; i < Images.attackRImgs.length; i++) {
			Images.attackRImgs[i] = loadImage("attackingR" + i + ".png");
		}
		for (int i = 0; i < Images.attackLImgs.length; i++) {
			Images.attackLImgs[i] = loadImage("attackingL" + i + ".png");
		}
		Images.deadImgs[0] = loadImage("playerDead.png");
		
		// For EvilKnight
		for (int i = 0; i < Images.ekRImgs.length; i++) {
			Images.ekRImgs[i] = loadImage("enemyR" + i + ".png");
		}
		for (int i = 0; i < Images.ekLImgs.length; i++) {
			Images.ekLImgs[i] = loadImage("enemyL" + i + ".png");
		}
		for (int i = 0; i < Images.ekAtkRImgs.length; i++) {
			Images.ekAtkRImgs[i] = loadImage("eAttack1R" + i + ".png");
		}
		for (int i = 0; i < Images.ekAtkLImgs.length; i++) {
			Images.ekAtkLImgs[i] = loadImage("eAttack1L" + i + ".png");
		}

		// For Bull
		for (int i = 0; i < Images.bullRImgs.length; i++) {
			Images.bullRImgs[i] = loadImage("bullWalkingR" + i + ".PNG");
		}
		for (int i = 0; i < Images.bullLImgs.length; i++) {
			Images.bullLImgs[i] = loadImage("bullWalkingL" + i + ".PNG");
		}
		for (int i = 0; i < Images.bullAtkRImgs.length; i++) {
			Images.bullAtkRImgs[i] = loadImage("BullAttackR" + i + ".PNG");
		}
		for (int i = 0; i < Images.bullAtkLImgs.length; i++) {
			Images.bullAtkLImgs[i] = loadImage("BullAttackL" + i + ".PNG");
		}

		// For Wizard
		for (int i = 0; i < Images.wizardRImgs.length; i++) {
			Images.wizardRImgs[i] = loadImage("wizardR" + i + ".PNG");
		}
		for (int i = 0; i < Images.wizardLImgs.length; i++) {
			Images.wizardLImgs[i] = loadImage("wizardL" + i + ".PNG");
		}

		// For Skeleton
		for (int i = 0; i < Images.skeletonRImgs.length; i++) {
			Images.skeletonRImgs[i] = loadImage("skeletonR" + i + ".PNG");
		}
		for (int i = 0; i < Images.skeletonLImgs.length; i++) {
			Images.skeletonLImgs[i] = loadImage("skeletonL" + i + ".PNG");
		}
		for (int i = 0; i < Images.skeletonAtkRImgs.length; i++) {
			Images.skeletonAtkRImgs[i] = loadImage("sAttackR" + i + ".PNG");
		}
		for (int i = 0; i < Images.skeletonAtkLImgs.length; i++) {
			Images.skeletonAtkLImgs[i] = loadImage("sAttackL" + i + ".PNG");
		}

	}

	void initAnimations() {
		// For Player
		Animations.walkingR = new Animation(Images.walkingRImgs, 50, this);
		Animations.walkingL = new Animation(Images.walkingLImgs, 50, this);
		Animations.standingR = new Animation(Images.standingRImgs, 200, this);
		Animations.standingL = new Animation(Images.standingLImgs, 200, this);
		Animations.attackR = new Animation(Images.attackRImgs, 0, this);
		Animations.attackL = new Animation(Images.attackLImgs, 0, this);
		Animations.dead = new Animation(Images.deadImgs, 0, this);

		// For EvilKnight
		Animations.ekR = new Animation(Images.ekRImgs, 50, this);
		Animations.ekL = new Animation(Images.ekLImgs, 50, this);
		Animations.ekAtkR = new Animation(Images.ekAtkRImgs, 500, this);
		Animations.ekAtkL = new Animation(Images.ekAtkLImgs, 500, this);

		// For Bull
		Animations.bullR = new Animation(Images.bullRImgs, 50, this);
		Animations.bullL = new Animation(Images.bullLImgs, 50, this);
		Animations.bullAtkR = new Animation(Images.bullAtkRImgs, 75, this);
		Animations.bullAtkL = new Animation(Images.bullAtkLImgs, 75, this);

		// For Wizard
		Animations.wizardR = new Animation(Images.wizardRImgs, 300, this);
		Animations.wizardL = new Animation(Images.wizardLImgs, 300, this);

		// For Skeleton
		Animations.skeletonR = new Animation(Images.skeletonRImgs, 200, this);
		Animations.skeletonL = new Animation(Images.skeletonLImgs, 200, this);
		Animations.skeletonAtkR = new Animation(Images.skeletonAtkRImgs, 150, this);
		Animations.skeletonAtkL = new Animation(Images.skeletonAtkLImgs, 150, this);
	}

	void loadSounds() {
		Sounds.menuMusic = new SoundFile(this, "menuMusic.wav");
		Sounds.menuMusic.amp(.75f);
		Sounds.gameMusic = new SoundFile(this, "mainMusic.wav");
		Sounds.gameMusic.amp(.5f);
		Sounds.loseMusic = new SoundFile(this, "losingSound.wav");
		Sounds.walking = new SoundFile(this, "walking2.wav");
		Sounds.swordSwing = new SoundFile(this, "swoosh.wav");
		Sounds.jump = new SoundFile(this, "jump.wav");
		Sounds.jump.amp(.75f);
		Sounds.enemyDeath = new SoundFile(this, "enemyDeath2.wav");
		Sounds.skeletonSummon = new SoundFile(this, "summoning.wav");
		Sounds.teleport = new SoundFile(this, "teleport.wav");
		Sounds.spellCast = new SoundFile(this, "spellSound.wav");
		Sounds.attackHit = new SoundFile(this, "attack.wav");
		
		
		
		
	}

}
