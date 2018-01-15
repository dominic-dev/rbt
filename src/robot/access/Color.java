/*
 * @Author Michiel de Smet
 */

package robot.access;
public enum Color {
	WHITE ("Color white"),
	BLACK ("Color black"),
	RED  ("Color red"),
	BLUE ("Color blue"),
	BROWN ("Color brown"),
	GREEN ("Color brown"),
	YELLOW ("Color brown"),
	NONE("Color brown");
	
	private String name;
	
	Color (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
