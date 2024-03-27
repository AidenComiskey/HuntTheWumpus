import java.util.*;

public class Wumpus{

	/**
	 * wumpus lives if this attribute is positive
	 */
	private int wumpusLives = 2;
	
	// This method randomises a room for the wumpus to start in
	public int getWumpusCave() {
		Random rand = new Random();
		int wumpusCave = rand.nextInt(21);
		return wumpusCave;
	}

	public int getLives() {
		return wumpusLives;
	}

	// When a wumpus is shot the number of lives decreases by 1 and when the lives reach 0 the game ends
	public void shoot() {
		if (this.wumpusLives > 0) {
			this.wumpusLives -= 1;
			System.out.println("Wumpus was shot but it still has " + getLives() + " life left");
			if (this.wumpusLives == 0) {
				System.out.println("Wumpus died! Well done, you won!");
				System.exit(0);;	
			}
		}
		
	}

}