package robot;

import java.util.ArrayList;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.TextLCD;

/**
 * 
 * @author Vincent
 * Lcd displays textmessages within the boundaries of the screen
 * e.g. clear(); write(message); //do add a delay
 * 
 */
public class Lcd {

	// cellheight = 16
	// maxheightpx = 128
	// cellwidth = 10
	// maxwidthpx = 178

	private final static int MAX_WIDTH_IN_CHAR = 17;
	private final static int MAX_HEIGHT_IN_CHAR = 8;
	private final static int START = 0;
	private static TextLCD screen;
	private static GraphicsLCD graphic;

	public Lcd() {
		super();
		screen = BrickFinder.getDefault().getTextLCD();
		graphic = BrickFinder.getDefault().getGraphicsLCD();
	}
	
	public Lcd(Brick brick) {
		super();
		screen = brick.getTextLCD();
		graphic = brick.getGraphicsLCD();
	}

	public void writeToScreen(TextLCD screen, String message) {
		screen.drawString(message, 0, 0);
	}

	/**
	 * @param message to display
	 * @param w in screen
	 * @param h in screen
	 */
	public void writeToScreen(String message, int w, int h) {
		screen.drawString(message, w, h);
	}
	
	/**	 * 
	 * @param message to display, fitting to screen
	 */
	public void write(String message) {
		ArrayList<String> printset = messageBuilderList(message);
		
		write(printset);
		
		

	}
	
	/**
	 * @param message to display
	 */
	public void write(String[] message) {

		for (int i = 0; i < message.length; i++) {
			writeToScreen(message[i], START, i);
		}
	}
	/**
	 * @param message to display
	 */
	public void write(ArrayList<String> message) {
		
		for (int i = 0; i < message.size(); i++) {
			writeToScreen(message.get(i), START, i);
		}
		
	}

	/**
	 * @param message
	 *            gets cut up in strings that fit the w of the screen
	 * @return a lcd printable array, [i] = row
	 */
	public String[] messageBuilderArray(String message) {
		String[] printable = (String[]) messageBuilderList(message).toArray();
		return printable;
	}

	/**
	 * @param message gets cut up in strings that fit the w of the screen
	 * @return a list of strings that fit the screen
	 */
	public ArrayList<String> messageBuilderList(String message) {

		// load the string in a stringbuilder
		StringBuilder builder = new StringBuilder(message);
		// create a list to print
		ArrayList<String> printable = new ArrayList<String>();

		while (builder.length() > 0) {

			if (builder.length() >= MAX_WIDTH_IN_CHAR) {

				for (int end = MAX_WIDTH_IN_CHAR; end >= 0; end--) {

					// hak de string af bij de spatie en add to array
					// als er geen spatie is en add to array
					if (builder.charAt(end) == ' ') {
						printable.add(builder.substring(START, end + 1));
						String newbuilder = builder.substring(end + 1, builder.length());
						builder = new StringBuilder(newbuilder);
						end = -1;

					} else if (end == 0) {
						printable.add(builder.substring(START, MAX_WIDTH_IN_CHAR - 1));
						String newbuilder = builder.substring(MAX_WIDTH_IN_CHAR, builder.length());
						builder = new StringBuilder(newbuilder);
					}

				}

			} else if (builder.length() < MAX_WIDTH_IN_CHAR) {
				// hak het einde af en stop deze in de array
				// builder kan totaal worden toegevoegd want is minder dan max karakters
				printable.add(builder.toString());
				builder = new StringBuilder("");
			}

		}

		return printable;

	}

	/**
	 * @param fillLines
	 *            to fill
	 * @return
	 */
	public String screenFiller(int fillLines) {
		// string 17 chars wide (max)
		String baseString = "012345678901end";
		String printableScreen = "";

		for (int i = 0; i < fillLines; i++) {
			printableScreen = String.format("%s%d%s ", printableScreen, i, baseString);
		}

		return printableScreen;
	}

    public void clear(){
        for(int i=0; i<9; i++){
            System.out.println("");
        }
    }
}
