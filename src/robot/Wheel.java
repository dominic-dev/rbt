
package robot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

/**
 * 
 * @author Michiel
 *
 */
public class Wheel  extends EV3LargeRegulatedMotor{

	EV3LargeRegulatedMotor motor;
	
	// @author Vincent
	public Wheel(Port motorPort) {		
        super(motorPort);
		//this(new EV3LargeRegulatedMotor(motorPort));
	}	
	// @author Vincent
	public Wheel(Port motorPort, int velocity) {		
        this(motorPort);
        setSpeed(velocity);
		//this(new EV3LargeRegulatedMotor(motorPort), velocity);
	}
	// @author Vincent	
	//public void setVelocity(int velocity) {
		//motor.setAcceleration(velocity);
	//}

	//public void stop() {
		//motor.stop();
	//}

	//public void backward() {
		//motor.backward();
	//}
	
	//public void forward() {
		//motor.forward();
	//}
	
	//public EV3LargeRegulatedMotor getMotor() {
		//return motor;
	//}
	//public void setMotor(EV3LargeRegulatedMotor motor) {
		//this.motor = motor;
	//}
	
	

}
