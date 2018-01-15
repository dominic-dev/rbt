package robot.app.linefollow;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import robot.ColorSensor;
import robot.Lcd;
import robot.Robot;


/**
 * @author Dominic
 **/

public class LineFollower {

    private Robot robot;
    // PID
    private float midpoint;
    private float kp;
    private int baseSpeed = 500;

    private ColorSensor sensor;
    private EV3LargeRegulatedMotor leftMotor;
    private EV3LargeRegulatedMotor rightMotor;

    public LineFollower(Robot robot){
        this.robot = robot;
        this.sensor = new ColorSensor(robot.getBao().getColorSensorPort());
        leftMotor = robot.getBao().getLeftMotor();
        rightMotor = robot.getBao().getRightMotor();
    }

    /**
     * Start the line follower
     **/
    public void start(){
        // Calibrate white
        float white = sensor.calibrateRed("white");
        // Calibrate black
        float black = sensor.calibrateRed("black");
        midpoint = (white - black) / 2 + black;
        // Ready
        Lcd.clear();
        Lcd.write("READY");
        Lcd.write("Press ENTER to continue");
        Button.ENTER.waitForPressAndRelease();
        Lcd.clear();
        Lcd.write("Following");
        while(!Button.ESCAPE.isDown()){
            loop();
        }

    }

    /**
     * The line follow loop
     * runs untill escape is pressed
     **/
    public void loop(){
        // If button is pressed kp += ?
        Lcd.write(String.format("Pk is: %f", kp));
        // TODO value omzetten in bruikbare waarde
        float value = sensor.getRedValue();
        float correction = kp * (midpoint - value);
        int leftMotorSpeed = (int) (baseSpeed - correction);
        int rightMotorSpeed = (int) (baseSpeed + correction);
        leftMotor.setSpeed(leftMotorSpeed);
        rightMotor.setSpeed(rightMotorSpeed);
    }

}
