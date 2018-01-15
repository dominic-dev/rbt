package helpers;

import java.util.Scanner;

public class Console {
    /**
     * Clear the console
     **/
	public static void clear() {
		for (int i=0; i<100; i++) {
		    System.out.print("\n");
		}
	}


    /**
     * Press enter to continue in console.
     **/
    public static void promptEnterKey(){
       System.out.println("Press \"ENTER\" to continue...");
       Scanner scanner = new Scanner(System.in);
       scanner.nextLine();
    }

    public static String promptTextInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String promptTextInput(String prompt){
        System.out.println(prompt);
        return promptTextInput();
    }

}
