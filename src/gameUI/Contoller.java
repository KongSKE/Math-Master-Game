package gameUI;

/**
 * Superclass for all controller that make easy to change scene.
 * 
 * @author Dacharat Pankong
 *
 */
public class Contoller {

	private String name;
	
	/**
	 * Set player name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Return player name.
	 * 
	 * @return player name.
	 */
	public String getName() {
		return name;
	}
}
