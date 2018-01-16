/*
 * @Author Michiel de Smet
 */

package robot;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import robot.access.BAO;

public class Sensor {
	SampleProvider colorProvider;
	EV3ColorSensor colorSensor;
//	private BAO bao = new BAO();
//	EV3ColorSensor colorSensor = new EV3ColorSensor(bao.getColorSensorPort());

	public Sensor() {
		super();
	}

	public Sensor(EV3ColorSensor colorSensor) {
		super();
		this.colorSensor = colorSensor;
	}
	
	/**
	 * Return the name of the color scanned
	 **/
	public String getColorName() {
		int colorID = colorSensor.getColorID();

		if (colorID >= 0 && colorID < 18) {
			return Color.values()[colorID].getName();
		} else {
			return "0";
		}
	}

}
