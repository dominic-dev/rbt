package main;
import lejos.hardware.Button;

import robot.ColorSensor;
import robot.Robot;
import robot.app.hangman.Hangman;
import robot.color.RGB;

public class Main {

	public static void main(String[] args) {
        Robot rufus = new Robot();
        //rufus.followLine();
        //rufus.debugColor();
        //hangman(rufus);
        Hangman hangman = new Hangman(rufus);
        //hangman.calibrateColors();
        //System.out.println("Press enter to continue");
        Button.ENTER.waitForPressAndRelease();
        hangman.readPlaceholdersRobot();
	}

    // Hangman
    public static void hangman(Robot robot){
        //robot.move.forward();
        ColorSensor sensor = new ColorSensor(robot.getBao().getColorSensorPort());
        System.out.println("Calibrate black");
        Button.ENTER.waitForPressAndRelease();
        RGB black = sensor.getRGB();
        System.out.println("Calibrate white");
        Button.ENTER.waitForPressAndRelease();
        RGB white = sensor.getRGB();
        goToNextStop(robot, sensor, black, white);
        //while(!Button.ESCAPE.isDown()){
            //hangmanLoop(robot, sensor, black, white);
        //}
    }

    public static void hangmanLoop(Robot robot, ColorSensor sensor, RGB black, RGB white){ 
        RGB color = sensor.getRGB();
        if(color.matches(black, .05f)){
            System.out.println("Black");
        }
        else if(color.matches(white, .05f)){
            System.out.println("White");
        }
        else{
            System.out.println("XXX");
        }

    }

    public static void goToNextStop(Robot robot, ColorSensor sensor, RGB black, RGB white){ 
        System.out.println("Press enter to continue");
        Button.ENTER.waitForPressAndRelease();
        robot.move.forward();

        RGB color = sensor.getRGB();
        while(!Button.ESCAPE.isDown()){
            if(color.matches(black, .05f)){
                System.out.println("Black");
            }
            else if(color.matches(white, 0.5f)){
                System.out.println("White");
                break;
            }
            else{
                System.out.println("XXX");
                break;
            }
            color = sensor.getRGB();
        }

        //RGB color = sensor.getRGB();
        //while(true){
            //color = sensor.getRGB();
            //if(!color.matches(black, 0.5f)){
                //break;
            //}
        //}
        //robot.move.stop();
    }

}
