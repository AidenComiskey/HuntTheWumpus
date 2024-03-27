public class Room {
	private Room east, west, north, south;
	private int roomNumber;
	private boolean hasBats = false;
	private boolean hasWumpus = false;;
	private boolean hasPit = false;

	public Room(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public Room getEast() {
		return east;
	}

	public void setEast(Room eastRoom) {
		this.east = eastRoom;
	}

	public Room getWest() {
		return west;
	}

	public void setWest(Room westRoom) {
		this.west = westRoom;
	}

	public Room getNorth() {
		return north;
	}

	public void setNorth(Room northRoom) {
		this.north = northRoom;
	}

	public Room getSouth() {
		return south;
	}

	public void setSouth(Room southRoom) {
		this.south = southRoom;
	}

	public void addBat() {
		this.hasBats = true;
	}

	public boolean hasBats() {
		return this.hasBats;
	}

	public void addPit() {
		this.hasPit = true;
	}

	public boolean hasPit() {
		return this.hasPit;
	}

	public void addWumpus() {
		this.hasWumpus = true;
	}

	public boolean hasWumpus() {
		return this.hasWumpus;
	}

	public String toString() {
		String hazard = "Room " + roomNumber + ".";
		if (hasBats) {
			hazard += " This room has bats.";
		}
		if (hasPit) {
			hazard += " This room has a pit.";
		}
		if (hasWumpus) {
			hazard += " This room has the wumpus.";
		}
		return hazard;
	}
}
