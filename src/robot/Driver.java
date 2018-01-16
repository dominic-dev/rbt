package robot;

import robot.access.BAO;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

/**
 * 
 * @author Dominic
 *
 */
public class Driver {
	Wheel leftWheel;
	Wheel rightWheel;
	EV3LargeRegulatedMotor[] leftWheelMotor;
	EV3LargeRegulatedMotor[] rightWheelMotor;

	public Driver(Port leftMotorPort, Port rightMotorPort) {
		leftWheel = new Wheel(leftMotorPort);
		rightWheel = new Wheel(rightMotorPort);		
		EV3LargeRegulatedMotor[] motor1 = {leftWheel};
		EV3LargeRegulatedMotor[] motor2 = {rightWheel};
		this.leftWheelMotor = motor1;
		this.rightWheelMotor = motor2;
	}


	/**
	 * Turn left
     * @param timeMS how long to turn
	 * 
	 */
	public void turnLeft(int timeMS) {
		this.turnLeft();
		delay(timeMS);
	}

    /**
     * Turn right
     * @param timeMS how long to turn
     **/
	public void turnRight(int timeMS) {
		this.turnRight();
		delay(timeMS);
	}

    /**
     * Turn left in place
     * @param timeMS how long to turn
     **/
	public void turnLeftInPlace(int timeMS) {
		this.turnLeftInPlace();
		delay(timeMS);
	}

    /**
     * Turn right in place
     * @param timeMS how long to turn
     **/
	public void turnRightInPlace(int timeMS) {
		this.turnRightInPlace();
		delay(timeMS);
	}

    /**
     * Drive forward
     * @param timeMS how long to drive forward
     **/
	public void forward(int timeMS) {
		this.forward();
		delay(timeMS);
	}

    /**
     * Drive backward 
     * @param timeMS how long to drive backward
     **/
	public void backward(int timeMS) {
		this.backward();
		delay(timeMS);
	}

	/**
     * Turn left in place
     **/
	public void turnLeftInPlace() {
		leftWheel.forward();
		rightWheel.backward();
	}

    /**
     * Turn right in place
     **/
	public void turnRightInPlace() {
		leftWheel.backward();
		rightWheel.forward();
	}

    /**
     * Turn left
     **/
	public void turnLeft() {
		rightWheel.stop();
		leftWheel.forward();
	}

    /**
     * Turn right
     **/
	public void turnRight() {
		rightWheel.forward();
		leftWheel.stop();
	}
	/**
	 * turn Right backwards
	 */
	public void turnRightBack() {
		rightWheel.stop();
		leftWheel.backward();
	}
	
	/**
	 * turn Left backwards
	 */
	public void turnLeftBack() {
		rightWheel.backward();
		leftWheel.stop();
	}

    /**
     * Drive forward
     **/
	public void forward() {
		leftWheel.forward();
		rightWheel.forward();
	}

    /**
     * Drive backward
     **/
	public void backward() {
		leftWheel.backward();
		rightWheel.backward();
	}

    /**
     * Stop moving
     **/
	//		leftWheelMotor[0].synchronizeWith(rightWheelMotor);
	//		leftWheelMotor[0].endSynchronization();

	public void stop() {
		leftWheelMotor[0].synchronizeWith(rightWheelMotor);
		leftWheel.stop();
		rightWheel.stop();
		leftWheelMotor[0].endSynchronization();
	}

    /**
     * Set velocity
     * @param velocity the velocity
     **/
	public void setSpeed(int velocity) {
		leftWheel.setSpeed(velocity);
		rightWheel.setSpeed(velocity);
	}

    /**
     * Delay
     * @param timeMS duration in milliseconds
     **/
	public void delay(int timeMS) {
		Delay.msDelay(timeMS);
	}

}
