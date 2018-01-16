/*
 * @Author Michiel de Smet
 */

package robot;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class SmallMotorMovement {
	//variables
	EV3MediumRegulatedMotor smallMotor;
	int velocity;
	
	//constructors
	public SmallMotorMovement() {
		super();
	}
	
	public SmallMotorMovement(EV3MediumRegulatedMotor smallMotor) {
		super();
		this.smallMotor = smallMotor;
	}
	
	public SmallMotorMovement(EV3MediumRegulatedMotor smallMotor, int velocity) {
		super();
		this.smallMotor = smallMotor;
		this.velocity = velocity;
	}
	
	public SmallMotorMovement(int velocity) {
		super();
		this.velocity = velocity;
	}
	
	public SmallMotorMovement(Port smallMotorPort) {
		smallMotor = new EV3MediumRegulatedMotor(smallMotorPort);
	}
	
	//Movement methods
	public void rotateForward() {
		smallMotor.forward();
	}
	
	public void rotateForward(int timeMS) {
		smallMotor.forward();
		Delay.msDelay(timeMS);
	}
	
	public void rotateBackward() {
		smallMotor.backward();
	}
	
	public void rotateBackward(int timeMS) {
		smallMotor.backward();
		Delay.msDelay(timeMS);
	}
	
	public void setTime(int timeMS) {
		Delay.msDelay(timeMS);
	}
	
	public void speedTime(int timeMS) {
		smallMotor.forward();
		smallMotor.setSpeed(2000);
		Delay.msDelay(timeMS);
	}
	
	public void slowTime(int timeMS) {
		smallMotor.backward();
		smallMotor.setAcceleration(100);
	}
	
	public void rotationStop() {
		smallMotor.stop();
	}
}

