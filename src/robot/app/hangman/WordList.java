package robot.app.hangman;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;

import helpers.MapUtil;

public class WordList {
    //final String WORDLIST_JSON_PATH = "games/hangman/four_letter_words.json";
    final String WORDLIST_JSON_PATH = "src/robot/app/hangman/four_letter_words.json";

    ArrayList<String> list = new ArrayList<>();
    LinkedHashMap<Character, Integer> letterCounts = new LinkedHashMap<>();

    public WordList(){
            System.out.println("Working Directory = " +
              System.getProperty("user.dir"));


        // JSON not working on EV3
        //this.list = loadJSON(WORDLIST_JSON_PATH);
        String[] listAsArray = {"acht", "auto", "baan", "baby", "bang", "bank", "been", "beer", "berg", "best", "blad", "blij", "boek", "boom", "boon", "boos", "bord", "brug", "cool", "daar", "deel", "deze", "diep", "dier", "ding", "doen", "dood", "door", "doos", "dorp", "drie", "druk", "duur", "echt", "eend", "eeuw", "eten", "even", "feit", "fijn", "film", "fles", "foto", "fout", "fris", "gaan", "geel", "geen", "geit", "geld", "geur", "glad", "glas", "goed", "goud", "graf", "grap", "gras", "grof", "haan", "haar", "half", "hand", "hard", "hart", "heel", "heet", "help", "hert", "hier", "hoed", "hoek", "hond", "hoog", "hoop", "huis", "huur", "idee", "iets", "jaar", "jong", "jouw", "kaas", "kans", "kant", "kast", "keus", "kind", "kist", "klas", "klok", "knie", "kort", "kost", "koud", "kuil", "laag", "laat", "lach", "lamp", "land", "lang", "leeg", "leuk", "lijk", "list", "lomp", "lood", "maag", "maal", "maan", "maar", "maat", "mama", "mand", "meel", "meer", "melk", "mijn", "mits", "mond", "mooi", "munt", "naam", "naar", "neer", "neus", "niet", "noch", "nood", "noot", "olie", "onze", "ooit", "oost", "open", "orde", "over", "paar", "papa", "park", "pijn", "plat", "plus", "poes", "punt", "raak", "raam", "rest", "rijk", "ring", "rond", "rood", "rook", "rots", "roze", "slim", "slot", "smal", "snel", "soep", "soms", "spel", "stad", "stap", "stem", "ster", "stil", "stof", "stom", "stop", "stuk", "taal", "tand", "taxi", "team", "teen", "test", "thee", "tien", "tijd", "toen", "tram", "trui", "tuin", "vaak", "vals", "vast", "veel", "veer", "verf", "vers", "vier", "vies", "vijf", "vlag", "voet", "voor", "vork", "vorm", "vrij", "vuur", "waar", "want", "warm", "week", "weer", "wens", "werk", "west", "wiel", "wijn", "wijs", "wild", "wind", "wolf", "wolk", "woud", "zaak", "zand", "zeep", "zeer", "zeil", "ziek", "ziel", "zien", "zijn", "zoet", "zoon", "zorg", "zout", "zuid", "zwak"};
        this.list = new ArrayList<>(Arrays.asList(listAsArray));
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
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String word = iterator.next();
            if (word.contains(String.valueOf(character))){
                iterator.remove();
            }
        
        }
    }

    /**
     * Remove words that do not have the given letter
     * on the given positions
     * @param character the character
     * @param positions the positions at which the character appears
     **/
    public void removeWordsNotMatchingCorrectGuess(char character, ArrayList<Integer> positions){
        // For each word
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String word = iterator.next();
            boolean match = true;
            // Fast but not accurate
            //if(!word.contains(String.valueOf(character))){
                //iterator.remove();
            //}


            // Slow but accurate
            // For each character
            char[] characters = word.toCharArray();
            for (int i : positions){
                if (word.charAt(i) != character){
                    match = false;
                }
            }
            // Iterator cannot appear in for loop
            if(!match){
                iterator.remove();
            }
        }
    }

    public String guessWord(){
        return list.get(0);
    }
}
