/*
 * @Author Michiel de Smet
 */

package robot.trick;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.utility.Delay;
import robot.SmallMotorMovement;
import robot.SoundPlayer;
import robot.access.BAO;

public class Fishing {

	private BAO bao = new BAO();
	SoundPlayer soundPlay = new SoundPlayer();
	EV3MediumRegulatedMotor smallMotor = new EV3MediumRegulatedMotor(bao.getSmallMotorPort());
	SmallMotorMovement fish = new SmallMotorMovement(smallMotor);

	/*
	 * first goes backwards a bit, to simulate the fishing motion. THan moves
	 * forward, remains there for a x-amount of time, then goes backwards again,
	 * makes a sound of celebration.
	 */
	public void fishingTrick() {

		fish.rotateBackward(200);
		fish.rotationStop();
		Delay.msDelay(500);
		fish.rotateForward(350);
		fish.rotationStop();
		Delay.msDelay(2000);
		fish.rotateBackward(150);
		fish.rotationStop();
		soundPlay.playBeepsequence(10);
	}

}
