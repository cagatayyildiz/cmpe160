package framework;

/**
 * This class represents a player in the game. A player has two fields: its name so that the program
 * can interact with the player and its mark that appears on the board.
 * @author Cagatay Yildiz
 * 
 */
public class Player {
	/**
	 * The name of the player
	 */
	public String name;
	/**
	 * Its mark. 
	 */
	public char mark;
	
	public Player(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}
	
}