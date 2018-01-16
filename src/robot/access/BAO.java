package robot.access;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

/**
 * 
 * @author VincentxH
 * access object
 *
 */
public class BAO {
	
	private Brick brick = BrickFinder.getDefault();
	private Port leftMotorPort = brick.getPort("A");
	private Port rightMotorPort = brick.getPort("B");
	private Port smallMotorPort = brick.getPort("C");
	private Port colorSensorPort = brick.getPort("S1");
	private Port touchSensorPort = brick.getPort("S2");
	
	
	public BAO() {
		super();
	}

	/**
	 * getters and setters
	 * @return
	 */
	
	public Brick getBrick() {
		return brick;
	}


	public Port getLeftMotorPort() {
		return leftMotorPort;
	}


	public Port getRightMotorPort() {
		return rightMotorPort;
	}


	//public Port getSmallMotorPort() {
		//return smallMotorPort;
	//}


	public Port getColorSensorPort() {
		return colorSensorPort;
	}

    public EV3LargeRegulatedMotor getLeftMotor(){
        return new EV3LargeRegulatedMotor(getLeftMotorPort());
    }

    public EV3LargeRegulatedMotor getRightMotor(){
        return new EV3LargeRegulatedMotor(getLeftMotorPort());
    }

	public Port getTouchSensorPort() {
		return touchSensorPort;
	}

	public Port getSmallMotorPort() {
		return smallMotorPort;
	}

	public void setSmallMotorPort(Port smallMotorPort) {
		this.smallMotorPort = smallMotorPort;
	}
}
