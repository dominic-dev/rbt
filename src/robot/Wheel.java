
package robot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

/**
 * 
 * @author Michiel
 *
 */
public class Wheel {

	EV3LargeRegulatedMotor motor;
	int velocity;
	
    public Wheel(EV3LargeRegulatedMotor motor, int velocity) {
        super();
        this.motor = motor;
        this.velocity = velocity;
    }
	
	// @author Vincent
    public Wheel(EV3LargeRegulatedMotor motor) {
        this(motor, 0);
    }
	// @author Vincent
	public Wheel(Port motorPort) {		
        //super(motorPort)
		this(new EV3LargeRegulatedMotor(motorPort));
	}	
	// @author Vincent
	public Wheel(Port motorPort, int velocity) {		
        //this(motorPort);
        //setSpeed(velocity);
		this(new EV3LargeRegulatedMotor(motorPort), velocity);
	}
	// @author Vincent	
	public void setVelocity(int velocity) {
		motor.setAcceleration(velocity);
	}

	public void stop() {
		motor.stop();
	}

	public void backward() {
		motor.backward();
	}
	
	
	public void forward() {
		motor.forward();
	}

}
