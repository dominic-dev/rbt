package robot;

import helpers.Math2;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class Calibrator {
    EV3ColorSensor colorSensor;
    int black;
    int white;

    public Calibrator(EV3ColorSensor sensor){
        this.colorSensor = sensor;
    }


    public float[] fetchRedSample(){
        float[] sample = new float[5];
        colorSensor.getRedMode().fetchSample(sample, 0);
        return sample;
    }

    public float getRedValue(){
        float[] sample = fetchRedSample();
        return Math2.avg(sample);
    
    }
}
