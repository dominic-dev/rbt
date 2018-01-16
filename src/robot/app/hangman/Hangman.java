package robot.app.hangman;

import java.util.ArrayList;
import java.util.Arrays;

import helpers.Console;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.utility.Delay;

import robot.ColorSensor;
import robot.Robot;
import robot.color.RGB;

public class Hangman {

    final int N_LETTER_PLACEHOLDERS = 4;

    WordList wordlist = new WordList();

    ArrayList<Character> allGuesses = new ArrayList<>();
    ArrayList<Character> correctGuesses = new ArrayList<>();
    ArrayList<Character> wrongGuesses  = new ArrayList<>();

    // Positions that have not yet been guessed
    ArrayList<Integer> unGuessedPositions = new ArrayList<>();

    ArrayList<Integer> lastChangedPositions = new ArrayList<>();

    //RGB black;
    //RGB white;
    int black;
    int white;
    int red;
    char lastGuess;
    Robot robot;
    ColorSensor sensor;
    boolean blackState;
    final float ERROR_MARGIN = 0.2f;

    public Hangman(Robot robot){
        this.robot = robot;
        sensor = new ColorSensor(robot.getBao().getColorSensorPort());
        // Initialize unGuessedPositions 
        for(int i=1; i<=N_LETTER_PLACEHOLDERS; i++){
            unGuessedPositions.add(i);
        }
    }

    /**
     * The game loop
     **/
    public void run(){
        while(checkWinOrLoose() == false){
            char guess = guessLetter();

            boolean correct = getFeedback();

            if(correct){
                ArrayList<Integer> changedPositions = getChangedPositions();
                removeFromUnguessedPositions(changedPositions);
                correctGuess(changedPositions);
            } else {
                wrongGuess();
            }

            wordlist.analyzeLetterCount();
        }
    }

    /**
     * Get the postions that were changed since last time
     **/
    private ArrayList<Integer> getChangedPositions() {
        // TODO Get feedback from robot instead of console
        ArrayList<Integer> changedPositions = new ArrayList<>();
        String input = Console.promptTextInput("Which positions does it appear? Give space separated numbers");
        String[] split = input.split(" ");
        for (String s : split) {
            changedPositions.add(Integer.valueOf(s));
        }
        return changedPositions;
    }

    /**
     * Remove changed positions from positions that have not yet been guuessed
     **/
    private void removeFromUnguessedPositions(ArrayList<Integer> changedPositions){
        for (Integer i : changedPositions){
            unGuessedPositions.remove(i);
        }
    }

    /**
     * Guess a new letter based on the correct and wrong guesses.
     **/
    public char guessLetter(){
        lastGuess = wordlist.getBestGuess(allGuesses);
        allGuesses.add(lastGuess);
        return lastGuess;
    }


    /**
     * Process changed placeholders data
     **/
    public void processPlaceholderData(ArrayList<Integer> positions){
        if (positions.size() == 0){
            wrongGuess();
        }
        else {
            correctGuess(positions);
        }
    }

    /**
     * Process wrong guess
     **/
    public void wrongGuess(){
        wrongGuesses.add(lastGuess);
        wordlist.removeWordsContaining(lastGuess);
    }

    /**
     * Process correct guess
     **/
    public void correctGuess(ArrayList<Integer> positions){
        System.out.println(positions);
        correctGuesses.add(lastGuess);
        wordlist.removeWordsNotMatchingCorrectGuess(lastGuess, positions);
    }

    /**
     * Check if win or loose condition are met
     * @return true if win or loose conditions are met
     **/
    public boolean checkWinOrLoose(){
        if(wrongGuesses.size() > 10){
            gameOver();
            return true;
        } 
        else if (unGuessedPositions.size() == 0) {
            gameWin();
            return true;
            
        }
        return false;
    }

    public void gameWin(){
        // TODO win sequence
    
    }

    public void gameOver(){
        // TODO game over sequence
    
    }

    public boolean getFeedback(){
        // TODO Get feedback from robot instead of console
        String answer = Console.promptTextInput(String.format("Does the word contain the letter: %s ? (y)es or (n)o)", lastGuess));
        return answer.toLowerCase().contains("y");
    }


    public void calibrateColors(){
        System.out.println("Calibrate black");
        Button.ENTER.waitForPressAndRelease();
        black = sensor.getColorID();
        //black = sensor.getRGB();
        System.out.println("Calibrate white");
        white = sensor.getColorID();
        Button.ENTER.waitForPressAndRelease();
        //white = sensor.getRGB();
        System.out.println("Calibrate red");
        red = sensor.getColorID();
        Button.ENTER.waitForPressAndRelease();
    }

    public void readPlaceholdersWithRobot(){
        // Read placeholders
        //while(true){
            //int colorID = sensor.getColorID();
            //System.out.println(colorID);
            //if(Button.ESCAPE.isDown()){
                //break;
            //}
            //Delay.msDelay(10);
        //}

        black = 7;
        white = 6;
        red = 0;

        /*
        while(true){
            RGB color = sensor.getRGB();
            if(color.matches(black, ERROR_MARGIN)){
                System.out.println("Black");
            }
            else if(color.matches(white, ERROR_MARGIN)){
                System.out.println("White");
            }
            else{
                System.out.println("XXX");
            }
    
            if(Button.ESCAPE.isDown()){
                break;
            }
        }
        */

        robot.move.forward();
        robot.move.setSpeed(400);
        // Read until first placeholder
        int colorID = sensor.getColorID();
        while(colorID == black){
            colorID = sensor.getColorID();
            Delay.msDelay(10);
        }

        ArrayList<Integer> result = new ArrayList();

        // Read first placeholder
        for(int i=1; i<=4; i++){
            while(colorID != black){
                Delay.msDelay(10);
                colorID = sensor.getColorID();
                System.out.println(colorID);
                if (colorID == red){
                    Delay.msDelay(200);
                }
                // Double check because of transition errors
                colorID = sensor.getColorID();
                if (colorID == red){
                    if(!result.contains(i)){
                        result.add(i);
                    }
                }
            }
            // Drive over separator
            Delay.msDelay(10);
            colorID = sensor.getColorID();
            while(colorID == black){
                colorID = sensor.getColorID();
                Delay.msDelay(10);
            }
        }
        System.out.println(Arrays.toString(result.toArray()));
        robot.move.stop();
        Button.ESCAPE.waitForPressAndRelease();
        

        /*
        RGB color = sensor.getRGB();
        // Beginning
        while(color.matches(black, ERROR_MARGIN)){
            color = sensor.getRGB();
            if(Button.ESCAPE.isDown()){
                break;
            }
        }

        Delay.msDelay(10);

        ArrayList<Integer> result = new ArrayList<>();
        // Read placeholder
        for(int i=0; i<4; i++){
            // Read untill next black
            while(!color.matches(black, ERROR_MARGIN)){
                color = sensor.getRGB();
                // Next placeholder
                if(color.matches(black, ERROR_MARGIN)){
                    int blackCounter = 0;
                    for(int j=0; i<2; j++){
                        RGB tempColor = sensor.getRGB();
                        if (tempColor.matches(black)){
                            blackCounter++;
                        }
                        Delay.msDelay(10);
                    }
                    if(blackCounter == 2){
                        System.out.println("Nothing found");
                        // Next placeholder
                        break;
                    }
                }
                // Item detected
                if(!color.matches(white, ERROR_MARGIN)){
                    System.out.println("Item detected");
                    result.add(i);
                    //Delay.msDelay(10);
                    //int blackCounter = 0;
                    //while(blackCounter < 3){
                    ////while(!color.matches(black, ERROR_MARGIN)){
                        //color = sensor.getRGB();
                        //if(color.matches(black, ERROR_MARGIN)){
                            //System.out.println(String.format("Black detected %d times", blackCounter));
                            //blackCounter++;
                            //Delay.msDelay(10);
                        //}
                        //else{
                            //System.out.println("False black, continuing placeholder");
                            //break;
                        //}
                    //}
                    // Continue to next placeholder
                    break;
                }
                if(Button.ESCAPE.isDown()){
                    break;
                }
            }
            Delay.msDelay(10);
            while(color.matches(black, ERROR_MARGIN)){
                color = sensor.getRGB();
                if(Button.ESCAPE.isDown()){
                    break;
                }
            }
            Delay.msDelay(10);
        }
        System.out.println(Arrays.toString(result.toArray()));
        robot.move.stop();
        Button.ESCAPE.waitForPressAndRelease();

            //color = sensor.getRGB();
            //if(!color.matches(black, 0.2f)){
                //break;
            //}
            //if(Button.ESCAPE.isDown()){
                //break;
            //}
        //}
        //robot.move.stop();
        /*
        ArrayList<Integer> result = new ArrayList<>();
        int blackState = 1;
        int counter = 0;
        RGB color;
        while (true){
            // Beginning
            color = sensor.getRGB();
            if(!color.matches(black, 0.2f)){
                blackState = 0;
                break;
            }
        }

        while(counter < 3){
            color = sensor.getRGB();
            if(color.matches(black, 0.2f)){
                blackState = 1;
                counter++;
                while(blackState == 1){
                    color = sensor.getRGB();
                    if(!color.matches(black)){
                        blackState = 0;
                    }
                }
            }
        }
        */
        
        
    }
    // Done


}
