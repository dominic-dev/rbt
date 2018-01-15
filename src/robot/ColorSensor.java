package robot;

import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

import robot.color.RGB;

public class ColorSensor extends EV3ColorSensor{

    public ColorSensor(Port port){
        super(port);
    }
    
    /**
     * @return true if color detected is white
     **/
    public boolean isWhite(){
        return true;
    }

    /**
     * @return color name
     **/
    public String getColorName(){
        int colorID = getColorID();
        return Color.values()[colorID].getName();
    }

    /**
     * Default calibration
     **/
    public int calibrate(String colorName){
        return calibrateID(colorName);
    }
    
    /**
     * Calibrate a color by ID
     * @param colorName the name of the color
     * @return the colorID
     **/
    public int calibrateID(String colorName){
        Lcd.write("Put the robot on a white surface\nAnd press Enter");
        Button.ENTER.waitForPressAndRelease();
        int color = getColorID();
        Lcd.clear();
        Lcd.write("Done calibrating");
        Delay.msDelay(1000);
        return color;
    }

    /**
     * Calibrate a color RGB
     * @param colorName the name of the color
     * @return the colorID
     **/
    public RGB calibrateRGB(String colorName){
        Lcd.write("Put the robot on a white surface\nAnd press Enter");
        Button.ENTER.waitForPressAndRelease();
        float[]color = getRGBValue();
        Lcd.clear();
        Lcd.write("Done calibrating");
        Delay.msDelay(1000);
        return new RGB(color);
    }

    /**
     * Calibrate a color using RedMode
     * @param colorName the name of the color
     * @return the red value
     **/
    public float calibrateRed(String colorName){
        Lcd.write("Put the robot on a white surface\nAnd press Enter");
        Button.ENTER.waitForPressAndRelease();
        float color = getRedValue();
        Lcd.clear();
        Lcd.write("Done calibrating");
        Delay.msDelay(1000);
        return color;
    }

    public float[] getRGBValue(){
        float[] result = new float[3];
        SampleProvider sampleProvider = getRGBMode();
        sampleProvider.fetchSample(result, 0);
        return result;
    }

    public RGB getRGB(){
        return new RGB(getRGBValue());
    }

    public float getRedValue(){
        float[] result = new float[1];
        SampleProvider sampleProvider = getRedMode();
        sampleProvider.fetchSample(result, 0);
        return result[0];
    }

}
