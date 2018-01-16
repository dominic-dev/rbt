/*
 * @Author Paul Sinterniklaas
 */

package robot;

import java.io.File;

import lejos.hardware.Key;
import lejos.hardware.Sound;

public class SoundPlayer {

	// constructor
	public SoundPlayer () {
		super();
	}

	// plays selected file at selected volume
	public void play(String wavName,int volume){
		File soundFile = new File(wavName);
		Sound.setVolume(volume);
		Sound.playSample(soundFile);
	}
	
	// plays selected file at selected volume when key is pressed
	public void play(String wavName,int volume, Key key){
		key.waitForPress();
		File soundFile = new File(wavName);
		Sound.setVolume(volume);
		Sound.playSample(soundFile);
	}
	
	// plays selected file at selected volume when start is true
	public void play(String wavName,int volume, boolean start){
		if (start) {
			File soundFile = new File(wavName);
			Sound.setVolume(volume);
			Sound.playSample(soundFile);
		}
	}
	
	// plays beepsequence at selected volume
	public void playBeepsequence(int volume){
		Sound.setVolume(volume);
		Sound.beepSequence();
		
	}
	
	
	
}
