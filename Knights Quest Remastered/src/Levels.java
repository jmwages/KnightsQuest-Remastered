import java.util.ArrayList;

public class Levels {

	ArrayList<Enemy[]> levels;
	int currLevel;

	ArrayList<Enemy> eArray;
	ArrayList<Spell> sArray;

	boolean endless;

	int spawnInterval;
	long spawnTimerStart;

	/**
	 * creates a new levels object that adds enemies to the given arrays
	 * @param eArray
	 * @param sArray
	 */
	Levels(ArrayList<Enemy> eArray, ArrayList<Spell> sArray) {
		this.eArray = eArray;
		this.sArray = sArray;
		currLevel = 0;

		levels = new ArrayList<Enemy[]>();

		endless = false;

		spawnInterval = 2000;
		spawnTimerStart = System.currentTimeMillis();

		loadLevels();
	}

	/**
	 * adds to the levels ArrayList
	 */
	void loadLevels() {
		// Level 1
		levels.add(new Enemy[] { new EvilKnight(Constants.screenW / 2, Constants.screenH / 2) }); 
		// Level 2 
		levels.add(new Enemy[] { new EvilKnight(100, Constants.bottomLvl - 100),
				new EvilKnight(Constants.screenW - 100, Constants.bottomLvl - 100) });
		// Level 3
		levels.add(new Enemy[] { new Bull(Constants.screenW / 2, Constants.screenH / 2) });
		// Level 4
		levels.add(new Enemy[] { new EvilKnight(100, 50), new Bull(Constants.screenW - 100, 50) });
		// Level 5
		levels.add(new Enemy[] { new Wizard(100, 100, eArray, sArray) });
		// Level 6
		levels.add(new Enemy[] { new EvilKnight(100, 50), new EvilKnight(Constants.screenW - 100, 50),
				new Bull(Constants.screenW / 2, Constants.screenH / 2) });
		// Level 7
		levels.add(new Enemy[] { new Wizard(100, Constants.bottomLvl - 100, eArray, sArray),
				new EvilKnight(Constants.screenW - 100, 50) });
		// Level 8
		levels.add(new Enemy[] { new EvilKnight(100, 50), new Bull(Constants.screenW - 100, 50),
				new Wizard(100, Constants.bottomLvl - 100, eArray, sArray) }); 
	}

	/**
	 * updates the levels
	 */
	void update() {
		if (endless && eArray.size() < Constants.endlessMaxEnemyNum) {
			if (System.currentTimeMillis() - spawnTimerStart >= spawnInterval) {
				spawn(Constants.rgen.nextInt(7));
				spawnTimerStart = System.currentTimeMillis();
			}

		}
	}

	/**
	 * spawns an enemy based on the given integer
	 * @param select
	 */
	void spawn(int select) {
		switch (select) {
		case 0:
		case 1:
		case 2:
			eArray.add(new EvilKnight(Constants.rgen.nextInt(Constants.screenW),
					Constants.rgen.nextInt(Constants.bottomLvl)));
			break;
		case 3:
		case 4:
			eArray.add(
					new Bull(Constants.rgen.nextInt(Constants.screenW), Constants.rgen.nextInt(Constants.bottomLvl)));
			break;
		case 5:
			eArray.add(new Wizard(Constants.rgen.nextInt(Constants.screenW), Constants.bottomLvl-100, eArray, sArray));
			break;
		case 6:
			eArray.add(new Wizard(150, 100, eArray, sArray));
			break;
		}
	}

	/**
	 * adds the enemies in the next level into the Enemy ArrayList
	 */
	void nextLevel() {
		if (currLevel < levels.size()) {
			for (Enemy e : levels.get(currLevel)) {
				e.initTimers();
				eArray.add(e);
			}

			currLevel++;
		} else {
			endless = true;
		}
	}
	
	/**
	 * returns a formatted string of the level information
	 * @return
	 */
	String getCurrLvl() {
		if (!endless) {
			return  ("Level:" + (currLevel));
		} else {
			return ("SURVIVE");
		}
	}

}
