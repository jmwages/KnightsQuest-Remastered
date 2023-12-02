import processing.sound.*;

/*
 * Holds all the sounds in the game
 */
public class Sounds {
	
	public static SoundFile menuMusic;
	public static SoundFile gameMusic;
	public static SoundFile loseMusic;
	
	public static SoundFile walking;
	public static SoundFile swordSwing;
	public static SoundFile attackHit;
	public static SoundFile jump;
	public static SoundFile enemyDeath;
	public static SoundFile skeletonSummon;
	public static SoundFile teleport;
	public static SoundFile spellCast;


	static void playMenuMusic() {
		gameMusic.stop();
		
		if (!menuMusic.isPlaying() && !loseMusic.isPlaying()) {
			menuMusic.play();
		}
	}
	
	static void playGameMusic() {
		menuMusic.stop();
		
		if (!gameMusic.isPlaying()) {
			gameMusic.play();
		}
	}
	
	static void playWalking() {
		if (!walking.isPlaying()) {
			walking.play();
		}
	}

}
