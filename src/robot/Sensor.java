/*
 * @Author Michiel de Smet
 */

package robot;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class Sensor {
	EV3ColorSensor colorSensor;
    SampleProvider colorProvider;

	public Sensor(EV3ColorSensor colorSensor){
		this.colorSensor = colorSensor;
	}

    /**
     * Return the name of the color scanned
     * 
     **/
    public String getColorName(){
    	int colorID = colorSensor.getColorID();
    	
    	if (colorID >= 0 && colorID < 18) {
    		 return Color.values()[colorID].getName();
    	}
    	else {
    		return "0";
    	}        
    }
}
