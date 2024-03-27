import java.awt.event.KeyEvent;
import java.util.*;

public class Game implements Runnable {
	private Thread gameThread;
	private static final String name = "Hunt the Wumpus";
	private boolean running = false;
	private Scanner scanner;
	private Hunter hunter;
	private Cave cave = new Cave();
	private Wumpus wumpus = new Wumpus();

	public Game() {
		scanner = new Scanner(System.in);
		Room startRoom = cave.getRandomRoom();
		hunter = new Hunter(startRoom, wumpus, cave);
	}

	public void start(boolean restart) {
		restart = true;
		if (gameThread == null) {
			gameThread = new Thread(this);
			gameThread.start();
		}
	}

	public void run() {
		running = true;
		System.out.println("Welcome to " + name);
		System.out.println("Using the commands W/A/S/D, " + "please press one of these keys to make your next move.");
		System.out.println("Press K to shoot or X to exit game.");

		while (running) {
			System.out.println("You are in room " + hunter.getCurrentLocation().getRoomNumber());
			
			String command = scanner.nextLine().trim().toUpperCase();
			int keyEvent = -1;
					
			KeyListener keyListener = new KeyListener(hunter);

			switch (command) {
			case "W":
				keyEvent = KeyEvent.VK_W;
				break;
			case "A":
				keyEvent = KeyEvent.VK_A;
				break;
			case "S":
				keyEvent = KeyEvent.VK_S;
				break;
			case "D":
				keyEvent = KeyEvent.VK_D;
				break;
			case "K":
				keyEvent = KeyEvent.VK_K;
				break;
			case "X":
				keyEvent = KeyEvent.VK_X;
				break;
			default:
				System.out.println("Invalid command. Please enter again.");
				break;
			}
			
			if (keyEvent == KeyEvent.VK_X) {
				System.out.println("Game over! Thank you for playing.");
				System.exit(0);;
			}
			
			if (keyEvent != -1) {
				keyListener.keyPressed(keyEvent);
				keyListener.keyReleased(scanner, keyEvent);
			}
		}

		System.out.println("Game Over.");
	}

	public static void main(String[] args) {
		new Game().start(true);

	}

}
