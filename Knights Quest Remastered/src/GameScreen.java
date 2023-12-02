import java.util.ArrayList;

import processing.core.PApplet;
import processing.event.*;

public class GameScreen implements IWorld {

	ScreenWipe wipe;
	ScreenWipe gameOverWipe;

	Cloud[] clouds;
	Platform[] platforms;
	Player p;

	ArrayList<Enemy> eArray;
	ArrayList<Spell> spells;

	Levels levels;

	int score;

	/**
	 * creates a new GameScreen object
	 */
	GameScreen() {

		wipe = new ScreenWipe(false);
		gameOverWipe = new ScreenWipe(true);

		clouds = new Cloud[Constants.cloudAmt];
		for (int i = 0; i < clouds.length; i++) {
			clouds[i] = new Cloud();
		}

		platforms = new Platform[3];
		platforms[0] = new Platform(Constants.screenW / 2, Constants.bottomLvl - 160, 300);
		platforms[1] = new Platform(0, Constants.bottomLvl - 320, 450, 80);
		platforms[2] = new Platform(Constants.screenW, Constants.bottomLvl - 320, 450, 80);

		p = new Player();

		eArray = new ArrayList<Enemy>();
		spells = new ArrayList<Spell>();

		levels = new Levels(eArray, spells);

	}

	@Override
	public PApplet draw(PApplet c) {
		
		c.imageMode(3);
		c.image(Images.gameBackground, Constants.screenW / 2, Constants.screenH / 2, Constants.screenW,
				Constants.screenH);

		c.imageMode(c.CORNER);
		c.image(Images.platform, -40, Constants.bottomLvl - 30, Constants.screenW + 80, 300);
		
		for (Cloud cl : clouds) {
			cl.render(c);
		}
		
		c.imageMode(c.CORNER);
		c.image(Images.scroll, 10, 10, 200, 75);
		c.image(Images.scroll, Constants.screenW - 210, 10, 200, 75);

		c.textAlign(c.CORNER, c.CORNER);
		c.fill(0);
		c.textFont(Fonts.main);
		c.textSize(18);

		c.text(levels.getCurrLvl(), 45, 55);

		c.text("Score:" + score, 820, 55);

		for (Platform pl : platforms) {
			pl.render(c);
		}

		p.render(c);

		for (Enemy e : eArray) {
			e.render(c);
		}

		for (Spell s : spells) {
			s.render(c);
		}

		if (!wipe.wipeDone()) {
			wipe.render(c);
			wipe.grow();
		}

		if (!p.isAlive()) {
			gameOverWipe.render(c);
			gameOverWipe.grow();
		}

		return c;
	}

	@Override
	public IWorld update() {
		Sounds.playGameMusic();
		
		if (wipe.wipeDone() && p.isAlive()) {

			levels.update();

			if (eArray.size() == 0) {
				levels.nextLevel();
			}

			for (Cloud cl : clouds) {
				cl.update();
			}

			p.update();
			p.detectFloorLvl(platforms);

			for (Spell s : spells) {
				s.update(p, eArray);

				if (s.hitSomething()) {
					spells.remove(s);
					break;
				}
			}

			int eArrayLen = eArray.size();
			for (Enemy e : eArray) {
				e.update();
				e.detectFloorLvl(platforms);
				e.targetPlayer(p);

				if (e.getHealth() <= 0) {
					score += e.getPointVal();
					eArray.remove(e);
					Sounds.enemyDeath.play();
					break;
				}

				e.spawnSkeletons();
				if (eArray.size() != eArrayLen) {
					break;
				}

			}

		}

		if (gameOverWipe.wipeDone()) {
			return new EndScreen(score);
		} else {
			return this;
		}
	}

	@Override
	public IWorld keyPressed(KeyEvent kev) {
		p.moveStart(kev);
		p.attack(kev, eArray, spells);
		return this;
	}

	@Override
	public IWorld keyReleased(KeyEvent kev) {
		p.moveStop(kev);
		return this;
	}

	@Override
	public IWorld mousePressed(MouseEvent mev) {
		// TODO Auto-generated method stub
		return this;
	}

}
