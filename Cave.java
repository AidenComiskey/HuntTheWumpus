import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Cave {
	private Map<Integer, Room> rooms = new HashMap<>();
	private Room wumpusLocation;
	private Room pitLocation;
	private Room hunterLocation;

	public Cave() {
		initialiseRooms();
		initialise();
	}

	private void initialise() {
		addRoom(0, hunterLocation);
		placeCharactersRandomly();

	}

	// add room to the caves
	public void addRoom(int roomNumber, Room room) {
		rooms.put(roomNumber, room);
	}

	// get a room by its number
	public Room getRandomRoom() {
		List<Integer> key = new ArrayList<>(rooms.keySet());
		
		Room aRoom = null;
		while (aRoom == null) {
			int randomRoom = new Random().nextInt(key.size());
			aRoom = rooms.get(key.get(randomRoom));
		}
		
		return aRoom;

	}

	// Method to create the cave systems circular structure
	public void initialiseRooms() {
		int totalRooms = 20;
		for (int i = 1; i <= totalRooms; i++) {
			// Create each room and add it to the map
			rooms.put(i, new Room(i));
		}

		// For each room, set it's neighbouring rooms in each direction
		for (int i = 1; i <= totalRooms; i++) {
			Room currentRoom = rooms.get(i);
			
			// Calculate room numbers of neighbouring rooms
			// If the current room is the last room then the room to the east is the first room
			int east = (i % totalRooms) + 1;
			// If current room is first room then west room is last room
			int west = (i == 1) ? totalRooms : i - 1;
			/* If the current room is one of the last two rooms (i.e., i + 2 > totalRooms), 
			then the room to the north is calculated using modulo operation to wrap around to the beginning of the room list.
			Otherwise, the room to the north is two rooms ahead (i.e., room number i + 2). */
			int north = (i + 2) <= totalRooms ? i + 2 : (i + 2) % totalRooms;
			/* If the current room is one of the last three rooms (i.e., i + 3 > totalRooms), 
			then the room to the south is calculated using modulo operation to wrap around to the beginning of the room list.
			Otherwise, the room to the south is three rooms ahead (i.e., room number i + 3). */
			int south = (i + 3) <= totalRooms ? i + 3 : (i + 3) % totalRooms;
			
			currentRoom.setEast(rooms.get(east));
			currentRoom.setWest(rooms.get(west));
			currentRoom.setNorth(rooms.get(north));
			currentRoom.setSouth(rooms.get(south));
		}

	}

	// Method to place a characters and pits at a random location
	private void placeCharactersRandomly() {
		// Create a list of all rooms
		List<Integer> roomNumbers = new ArrayList<>(rooms.keySet());
		Collections.shuffle(roomNumbers); // shuffle the rooms

		//incase the bats, wumpus or pits cannot be assigned, this error highlights the problem then
		if (roomNumbers.size() < 7) {
			System.out.println("Not enough rooms to place all characters and hazards.");
			return; // Exit the method to avoid the exception
		}

		// this.batsLocation = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			int batRoomNumber = roomNumbers.remove(0);
			Room batRoom = rooms.get(batRoomNumber);
			
			if (batRoom != null) {
				batRoom.addBat();
			}
		}

		// used roomNumbers.remove to assign a unique room to each instead of .get as it
		// would assign the same place maybe
		int wumpusRoom = roomNumbers.remove(0);
		this.wumpusLocation = rooms.get(wumpusRoom);
		
		if (wumpusLocation != null) {
			this.wumpusLocation.addWumpus();
		}
		
		int pitRoom = roomNumbers.remove(0);
		this.pitLocation = rooms.get(pitRoom);
		
		if (pitLocation != null) {
			this.pitLocation.addPit();
		}

		int hunterRoom = roomNumbers.remove(0);
		this.hunterLocation = rooms.get(hunterRoom);

	}

}
