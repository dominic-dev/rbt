/*
 * @Author Michiel de Smet
 */

package robot;

import lejos.hardware.Button;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;
import lejos.utility.Delay;


public class SimpleTouch extends AbstractFilter  {
	//variables 
	private float[] sample;
	SoundPlayer soundPlay = new SoundPlayer();
	
	//constructor
	public SimpleTouch(SampleProvider source) {
		super(source);
		sample = new float[sampleSize];	
	}

	//method isPressed, and used in the 
	public boolean isPressed() {
		super.fetchSample(sample, 0);
		
		if (sample[0] == 0) {
			return false;
		}
		return true;
	}
		
	//executes basic touch method.
	public void basicTouch() {	
		
		//This can be deleted. It's used for testing atm.
		while (!Button.ESCAPE.isDown()) {

			if (isPressed()) { //verander naar if als hierboven veranderd
				
				//calls upon the sound (now basic)
				soundPlay.playBeepsequence(5);
				Delay.msDelay(2000);
			}
		}
	}
}
