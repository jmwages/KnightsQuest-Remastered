import processing.core.*;
import processing.event.*;

public class StartScreen implements IWorld {

	Button startButton;
	Button infoButton;
	Button manual;
	
	ScreenWipe w;
	
	int mouseX;
	int mouseY;
	
	boolean gameStarting;
	
	StartScreen() {
		startButton = new Button(Constants.screenW/2, Constants.screenH/2 + 120, 370, 245, Images.startBImg, Images.startBImgPressed);
		infoButton = new Button(Constants.screenW - 50, 50, 80, 80, Images.infoBImg, Images.infoBImgPressed);
		manual = new Button(Constants.screenW/2, Constants.screenH/2, Constants.screenW - 100, Constants.screenH - 100, Images.manualImg, Images.manualImg);
		manual.hide();
		
		gameStarting = false;
		
		w = new ScreenWipe(true);
	}

	@Override
	public PApplet draw(PApplet c) {
		mouseX = c.mouseX;
		mouseY = c.mouseY;
		
		// draws the background
		c.imageMode(3);
		c.image(Images.menuBackground, Constants.screenW/2, Constants.screenH/2, Constants.screenW, Constants.screenH);
		c.image(Images.scroll, Constants.screenW/2, Constants.screenH/2 - 140, 810, 216);
		
		c.fill(0);
		c.textFont(Fonts.main);
		c.textSize(45);
		c.textAlign(c.CENTER, c.CENTER);
		c.text("Knight's Quest", Constants.screenW/2, Constants.screenH/2 - 140);
		
		// draws the startButton
		startButton.render(c);
		infoButton.render(c);
		manual.render(c);
		
		if (gameStarting) {
			w.render(c);
		}
		
		return c;
	}

	@Override
	public IWorld update() {
		Sounds.playMenuMusic();
		
		startButton.updateImg(mouseX, mouseY);
		infoButton.updateImg(mouseX, mouseY);
		
		if (gameStarting) {
			w.grow();
		}
		
		if (w.wipeDone()) {
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
	
	public IWorld mousePressed(MouseEvent mev) {
		
		if (manual.isClickable(mouseX, mouseY)) {
			manual.hide();
		} else if (startButton.isClickable(mouseX, mouseY)) {
			gameStarting = true;
		}
		
		if (infoButton.isClickable(mouseX, mouseY)) {
			manual.show();
		}
		
		
		
		return this;
	}

}
