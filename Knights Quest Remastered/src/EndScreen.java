import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class EndScreen implements IWorld {

	int score;

	ScreenWipe wipe;
	ScreenWipe gameStartWipe;

	Button startButton;

	boolean gameStarting;

	int mouseX;
	int mouseY;

	EndScreen(int score) {
		
		Sounds.loseMusic.play();
		
		this.score = score;

		this.wipe = new ScreenWipe(false);
		this.gameStartWipe = new ScreenWipe(true);

		startButton = new Button(Constants.screenW / 2, Constants.screenH / 2 + 175, 350, 225, Images.startBImg,
				Images.startBImgPressed);

		this.gameStarting = false;
	}

	@Override
	public PApplet draw(PApplet c) {
		mouseX = c.mouseX;
		mouseY = c.mouseY;

		c.imageMode(3);
		c.image(Images.menuBackground, Constants.screenW / 2, Constants.screenH / 2, Constants.screenW,
				Constants.screenH);

		if (!wipe.wipeDone()) {
			wipe.render(c);
		} else {
			c.image(Images.scroll, Constants.screenW / 2, 200, 850, 300);

			c.fill(0);
			c.textFont(Fonts.main);
			c.textSize(45);
			c.textAlign(c.CENTER, c.CENTER);
			c.text("You Died", Constants.screenW / 2, 150);

			c.textSize(30);
			c.text("Score:" + score, Constants.screenW / 2, 210);

			c.textSize(28);
			c.text("Press Start to Play Again", Constants.screenW / 2, 255);
			
			startButton.render(c);

		}
		
		if (gameStarting) {
			gameStartWipe.render(c);
		}
		return c;
	}

	@Override
	public IWorld update() {
		Sounds.playMenuMusic();
		
		startButton.updateImg(mouseY, mouseX);
		
		wipe.grow();

		if (gameStarting) {
			gameStartWipe.grow();
		}

		if (gameStartWipe.wipeDone()) {
			return new GameScreen();
		} else {
			return this;
		}
	}

	@Override
	public IWorld keyPressed(KeyEvent kev) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public IWorld keyReleased(KeyEvent kev) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public IWorld mousePressed(MouseEvent mev) {
		if (startButton.isClickable(mouseX, mouseY)) {
			gameStarting = true;
		}
		return this;
	}

}
