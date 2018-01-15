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

	public Driver(Port leftMotorPort, Port rightMotorPort) {
		leftWheel = new Wheel(leftMotorPort);
		rightWheel = new Wheel(rightMotorPort);
	}

    public Driver(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor) {
        leftWheel = new Wheel(leftMotor);
        rightWheel = new Wheel(rightMotor);
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
	public void stop() {
		leftWheel.stop();
		rightWheel.stop();
	}

    /**
     * Set velocity
     * @param velocity the velocity
     **/
	public void setVelocity(int velocity) {
		leftWheel.setVelocity(velocity);
		rightWheel.setVelocity(velocity);
	}

    /**
     * Delay
     * @param timeMS duration in milliseconds
     **/
	public void delay(int timeMS) {
		Delay.msDelay(timeMS);
	}

}
