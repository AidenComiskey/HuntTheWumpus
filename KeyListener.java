import java.awt.event.KeyEvent;
import java.util.Scanner;

public class KeyListener {
	private Hunter hunter;
	public boolean upPressed, downPressed, leftPressed, rightPressed;

	public KeyListener(Hunter hunter) {
		this.hunter = hunter;
	}

	// This method is called when a key is pressed
	// Upon the event of the corresponding key being pressed the variable is set to true
	public void keyPressed(int e) {
		switch (e) {
		case KeyEvent.VK_A:
			leftPressed = true;
			break;
		case KeyEvent.VK_W:
			upPressed = true;
			break;
		case KeyEvent.VK_S:
			downPressed = true;
			break;
		case KeyEvent.VK_D:
			rightPressed = true;
			break;
		}
	}

	// This method is called when a key is released
	/* Upon the event of the corresponding key being released the variable is then
	  set to false and then the move method is used to move the hunter in the direction inputted */
	public void keyReleased(Scanner scanner, int e) {
		switch (e) {
		case KeyEvent.VK_A:
			leftPressed = false;
			hunter.moveWest();
			break;
		case KeyEvent.VK_W:
			upPressed = false;
			hunter.moveNorth();
			break;
		case KeyEvent.VK_S:
			downPressed = false;
			hunter.moveSouth();
			break;
		case KeyEvent.VK_D:
			rightPressed = false;
			hunter.moveEast();
			break;
		case KeyEvent.VK_K:
			rightPressed = false;
			System.out.println("In which direction E, W, N, S would you like to shoot? ");
			String direction = scanner.nextLine().trim().toUpperCase();
			hunter.shoot(direction);
			break;
		}
	}

}
