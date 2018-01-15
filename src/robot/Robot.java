package robot;

import java.util.Arrays;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

import robot.access.BAO;
import robot.app.linefollow.LineFollower;
import robot.color.RGB;

public class Robot {
    private BAO bao = new BAO();
    public Driver move;
//    SimpleTouch basicTouch = new SimpleTouch();

    public Robot(){
        move = new Driver(bao.getLeftMotorPort(), bao.getRightMotorPort());
        //move.forward();
    }

    /**
     * @return the bao
     */
    public BAO getBao() {
        return bao;
	}

	/**
	 * @return the move
	 */
	public Driver getMove() {
		return move;
	}

    public void followLine(){
        LineFollower lineFollower = new LineFollower(this);
        lineFollower.start();
    }

    public void debugColor(){
        ColorSensor sensor = new ColorSensor(bao.getColorSensorPort());
        // Calibrate
        RGB white = sensor.calibrateRGB("white");


        while(!Button.ESCAPE.isDown()){
            RGB result = sensor.getRGB();
            //String message = Arrays.toString(result);
            String message = String.valueOf(white.matches(result, 0.1f));
            Lcd.write(message);
            Delay.msDelay(500);
        }
    }


    // @Author Michiel
    public void colorSensorBasic(){
    	
        EV3ColorSensor colorSensor = new EV3ColorSensor(bao.getColorSensorPort());
        Sensor sensor = new Sensor(colorSensor);

        while(!Button.ESCAPE.isDown()){
            Lcd.write(sensor.getColorName());
            Delay.msDelay(500);
        }
    }
    
    /*
     * @Author Michiel de Smet
     * Call upon the touchSensor class & method.
     */
    public void touchSensorBasic() {
       
    	EV3TouchSensor touchSensor = new EV3TouchSensor(bao.getTouchSensorPort());
    	SimpleTouch touch = new SimpleTouch(touchSensor);
    	
    	SimpleTouch test = new SimpleTouch(touch);
    	test.basicTouch();
    }
    


    //public void michielColorSensor(){
        //EV3ColorSensor colorSensor = new EV3ColorSensor(bao.getColorSensorPort());
        //Sensor sensor = new Sensor(colorSensor);

        //while(!Button.ESCAPE.isDown()){
            //Lcd.write(sensor.getColorName());
            //Delay.msDelay(500);
        //}
    //}

}
