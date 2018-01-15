package robot.app.hangman;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.JSONArray;

import helpers.MapUtil;

public class WordList {
    final String WORDLIST_JSON_PATH = "games/hangman/four_letter_words.json";

    ArrayList<String> list = new ArrayList<>();
    LinkedHashMap<Character, Integer> letterCounts = new LinkedHashMap<>();

    public WordList(){
            //System.out.println("Working Directory = " +
              //System.getProperty("user.dir"));


        this.list = loadJSON(WORDLIST_JSON_PATH);
        analyzeLetterCount();
    }

    /**
     * load JSON file, store it in this.list
     **/
    public ArrayList<String> loadJSON(String path){
        ArrayList<String> list = new ArrayList<>();
        try{
            String content = new Scanner(new File(path)).useDelimiter("\\Z").next();
            JSONArray jsonArray = new JSONArray(content);
            //System.out.println(jsonArray);
            if (jsonArray != null) { 
                for (int i=0;i<jsonArray.length();i++){ 
                    list.add(jsonArray.getString(i));
                }
            } 
        } catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Analyze word list and get the most probable letter
     **/
    public void analyzeLetterCount(){
        // reset
        letterCounts = new LinkedHashMap<>();

        //System.out.println("Counting . . .");
        // For each word
        for (String word : list){
            // For each character
            char[] characters = word.toCharArray();
            for (char c : characters){
                // Letter has been encountered before
                if (letterCounts.containsKey(c)){
                    letterCounts.put(c, letterCounts.get(c) + 1);
                }
                // First occurence of letter
                else{
                    letterCounts.put(c, 1);
                }
            }
        }
        //System.out.println("Done counting.");
        //System.out.println(letterCounts);
        //System.out.println(list);
    }

    /**
     * Return the best letter guess based on the remaining words
     * @param guesses that have been done already
     **/
    public char getBestGuess(ArrayList<Character> guesses){
        // Java 7
        letterCounts = MapUtil.sortByValueDesc(letterCounts);
        System.out.println(letterCounts);

        Iterator<Map.Entry<Character, Integer>> iterator = letterCounts.entrySet().iterator();
        Character character = null;
        while(iterator.hasNext()){
            Map.Entry<Character,Integer> entry = iterator.next();
            character = entry.getKey();
            System.out.println(character);
            if(guesses.contains(character)){
                continue;
            }
            break;
        }
        return character ;
    }


    /**
     * Remove words not containing a character
     **/
    public void removeWordsContaining(char character){
        // TODO implement java 7 solution
        //list.removeIf(s -> s.contains(String.valueOf(character)));
    }

    /**
     * Remove words that do not have the given letter
     * on the given positions
     * @param character the character
     * @param positions the positions at which the character appears
     **/
    public void removeWordsNotMatchingCorrectGuess(char character, ArrayList<Integer> positions){
        // For each word
        for (String word : list){
            // For each character
            char[] characters = word.toCharArray();
            for (int i : positions){
                if (word.charAt(i) != characters[i]){
                    list.remove(word);
                }
            }
        }
    }
}
