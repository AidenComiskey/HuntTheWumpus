import java.util.ArrayList;
import java.util.List;

public class Hunter {
	private Room location;
	private Wumpus wumpus;
	private Cave cave;
	
	public Hunter(Room startingLocation, Wumpus wumpus, Cave cave) {
		this.location = startingLocation;
		this.wumpus = wumpus;
		this.cave = cave;
		
	}

	public void nextMove(Room newRoom) {
		
		System.out.println("You are now in " + newRoom);
		System.out.println("Number of steps to reach the wumpus is " + findWumpus(newRoom, new ArrayList<Room>(), 0));
		
		// check if pit is in the new location
		if (newRoom.hasPit()) {
			System.out.println("You fell into a pit. You lost, game over.");
			System.exit(0);
		}

		if (newRoom.hasWumpus()) {
			System.out.println("This is the Wumpus's room. You lost, game over.");
			System.exit(0);
		}

		if (newRoom.hasBats()) {
			System.out.println("You encounter bats in this room, they relocate you to a random room.");
			this.location = cave.getRandomRoom();
			return;

		} else {
			this.location = newRoom;
			
		}
	}

	/*The following four methods check if the room in the direction from it exists, 
	and if so moves the player into it, otherwise an error message is displayed */
	public void moveWest() {
		if (this.location.getWest() != null) {
			this.nextMove(this.location.getWest());
		} else {
			System.out.println("You cannot move left.");
		}
	}

	public void moveNorth() {
		if (this.location.getNorth() != null) {
			this.nextMove(this.location.getNorth());
		} else {
			System.out.println("You cannot move upwards.");
		}
	}

	public void moveSouth() {
		if (this.location.getSouth() != null) {
			this.nextMove(this.location.getSouth());
		} else {
			System.out.println("You cannot move downwards.");
		}
	}

	public void moveEast() {
		if (this.location.getEast() != null) {
			this.nextMove(this.location.getEast());
		} else {
			System.out.println("You cannot move right.");
		}
	}
	
	/* Shoot method checks if the direction shot into contains the wumpus and if
	 so calls the method shoot, otherwise displays a message telling the player they missed */
	public void shoot(String direction) {
		
		
		if (direction.equals("E") && this.location.getEast() != null) {
			if(this.location.getEast().hasWumpus()) {
				wumpus.shoot();
			} else {
					System.out.println("You missed. Using the commands, you can either move or shoot in a different direction.");
			}
		}
		if (direction.equals("W") && this.location.getWest() != null) {
			if(this.location.getWest().hasWumpus()) {
				wumpus.shoot();
			} else {
				System.out.println("You missed. Using the commands, you can either move or shoot in a different direction.");
			}
		}
		if (direction.equals("S") && this.location.getSouth() != null) {
			if(this.location.getSouth().hasWumpus()) {
				wumpus.shoot();
			} else {
				System.out.println("You missed. Using the commands, you can either move or shoot in a different direction.");
			}
		}
		if (direction.equals("N") && this.location.getNorth() != null) {
			if(this.location.getNorth().hasWumpus()) {
				wumpus.shoot();
			} else {
				System.out.println("You missed. Using the commands, you can either move or shoot in a different direction.");
			}
		}
	}

	public Room getCurrentLocation() {
		return this.location;
	}
	
	/**
	 * method to find the quickest way to the wumpus
	 * @param current
	 * @param visits
	 * @param steps
	 * @return number of steps to get to the wumpus
	 */
	public static int findWumpus(Room current, List<Room> visits, int steps) {
		if (current == null) {
			return -1;
		}
		if (current.hasWumpus()) {
			return steps;
		}
		if (visits.contains(current)) { //if this room has been visited
			return -1;
		}
		
		visits.add(current);
		
		int north = findWumpus(current.getNorth(), new ArrayList<Room>(visits), steps + 1);
		int south = findWumpus(current.getSouth(), new ArrayList<Room>(visits), steps + 1);
		int west = findWumpus(current.getWest(), new ArrayList<Room>(visits), steps + 1);
		int east = findWumpus(current.getEast(), new ArrayList<Room>(visits), steps + 1);
		
		int shortest = -1;
		if (north != -1) {
			if (shortest == -1 || shortest > north) {
				shortest = north;
			}
		}
		if (south != -1) {
			if (shortest == -1 || shortest > south) {
				shortest = south;
			}
		}
		if (west != -1) {
			if (shortest == -1 || shortest > west) {
				shortest = west;
			}
		}
		if (east != -1) {
			if (shortest == -1 || shortest > east) {
				shortest = east;
			}
		}
		return shortest;
	}
}
