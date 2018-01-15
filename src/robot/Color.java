/*
 * @Author Michiel de Smet
 */

package robot;


public enum Color {
	
	RED ("This is red."),
	GREEN ("This is green"),
	YELLOWBLUE  ("Either yellow or blue"),
	BROWN ("This is Brown"),
	NOTHING ("0"),
	NOTHING1 ("0"),
	WHITE ("This is white"),
	BLACK ("This is black"),

	UNKNOWN1("9"),
	UNKNOWN2("10"),
	UNKNOWN3("11"),
	UNKNOWN4("12"),
	UNKNOWN5("13"),
	UNKNOWN6("14"),
	UNKNOWN7("15"),
	UNKNOWN8("16"),
	UNKNOWN9("17");
	
	
	private String name;
	
	Color (String name) {
		this.name = name;
	}
	
	//gives String
	public String getName() {
		return name;
	}

}
