package robot.app.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import helpers.Console;

import lejos.hardware.Button;
import lejos.utility.Delay;

import robot.ColorSensor;
import robot.Robot;

public class Hangman {

    final int N_LETTER_PLACEHOLDERS = 4;

    WordList wordlist = new WordList();

    ArrayList<Character> allGuesses = new ArrayList<>();
    ArrayList<Character> correctGuesses = new ArrayList<>();
    ArrayList<Character> wrongGuesses  = new ArrayList<>();

    // Positions that have not yet been guessed
    ArrayList<Integer> unGuessedPositions = new ArrayList<>();
    ArrayList<Integer> lastChangedPositions = new ArrayList<>();
    char lastGuess;

    Robot robot;

    ColorSensor sensor;
    int black;
    int white;
    int red;

    public Hangman(Robot robot){
        this();
        this.robot = robot;
        sensor = new ColorSensor(robot.getBao().getColorSensorPort());
    }

    public Hangman(){
        // Initialize unGuessedPositions 
        for(int i=0; i<N_LETTER_PLACEHOLDERS; i++){
            unGuessedPositions.add(i);
        }
    }

    /**
     * The game loop
     **/
    public void run(){
        while(checkWinOrLoose() == false){
            System.out.println("Unguessed positions:");
            System.out.println(Arrays.toString(unGuessedPositions.toArray()));
            //System.out.println("Current options:");
            //System.out.println(Arrays.toString(wordlist.list.toArray()));
            char guess = guessLetter();

            boolean correct = getFeedback();
            if(correct){
                ArrayList<Integer> changedPositions = new ArrayList<>();
                // If there is only 1 place remaining
                if(unGuessedPositions.size() == 1){
                    changedPositions.add(unGuessedPositions.get(0));
                }
                else{
                    changedPositions = getChangedPositionsConsole();
                    System.out.println("Changed positions");
                    System.out.println(Arrays.toString(changedPositions.toArray()));
                    //changedPositions = readPlaceholdersConsole();
                }
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
    private ArrayList<Integer> readPlaceholdersConsole() {
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
        //System.out.println(positions);
        correctGuesses.add(lastGuess);
        wordlist.removeWordsNotMatchingCorrectGuess(lastGuess, positions);
    }

    /**
     * Check if win or loose condition are met
     * @return true if win or loose conditions are met
     **/
    public boolean checkWinOrLoose(){
        if (wordlist.list.size() == 1){
            gameWin();
            return true;
        }
        if (wordlist.list.size() == 0){
           System.out.println("Sorry, I do not know this word."); 
           gameOver();
           return true;
        }
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
        String word = wordlist.guessWord();
        System.out.println(String.format("Is the word %s?", word));
    }

    public void gameOver(){
        System.out.println("I lost.");
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
        System.out.println("Calibrate white");
        white = sensor.getColorID();
        Button.ENTER.waitForPressAndRelease();
        System.out.println("Calibrate red");
        red = sensor.getColorID();
        Button.ENTER.waitForPressAndRelease();
    }

    public ArrayList<Integer> getChangedPositionsConsole(){
        ArrayList<Integer> guessedPositions = readPlaceholdersConsole();
        Iterator<Integer> iterator = guessedPositions.iterator();
        while(iterator.hasNext()){
            Integer i = iterator.next();
            if(lastChangedPositions.contains(i)){
                iterator.remove();
            }
        
        }
        lastChangedPositions = guessedPositions;
        return guessedPositions;
    }
    
    public ArrayList<Integer> readPlaceholdersRobot(){
        // Read colorID
        //while(true){
            //int colorID = sensor.getColorID();
            //System.out.println(colorID);
            //if(Button.ESCAPE.isDown()){
                //break;
            //}
            //Delay.msDelay(10);
        //}

        // TODO for faster testing
        black = 7;
        white = 6;
        red = 0;

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
        for(int i=0; i<4; i++){
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
        robot.move.stop();
        // Drive back
        robot.move.backward();
        Delay.msDelay(3500);
        robot.move.stop();
        System.out.println(Arrays.toString(result.toArray()));
        Button.ESCAPE.waitForPressAndRelease();
        return result;
    }
    // Done


}
