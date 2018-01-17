package main;

import java.util.ArrayList;
import java.util.Arrays;

import lejos.hardware.Button;
import robot.Robot;
import robot.app.hangman.Hangman;

public class MainHangman {
    public static void main(String[] args) {
        Robot rufus = new Robot();
        Hangman hangman = new Hangman(rufus);
        //hangman.calibrateColors();
        hangman.runRobot();
        //Button.ENTER.waitForPressAndRelease();
        //ArrayList<Integer> result = hangman.readPlaceholdersRobot();
        //System.out.println(Arrays.toString(result.toArray()));
        //Button.ESCAPE.waitForPressAndRelease();
    }

}
