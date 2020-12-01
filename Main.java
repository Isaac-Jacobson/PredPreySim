import processing.core.*;

public class Main extends PApplet {
	Simulator simulator;
	String saveFilePath = "c:\\data\\foxesAndRabbitsSaved.txt";
	boolean paused = true;
	int counter = 0;

	@Override
	public void setup() {
		size(640, 550);

		this.simulator = new Simulator(80, 60);
		this.simulator.setGUI(this);
	}

	/* System_base@;
	 * Hello World this is my dying wish.
	 * (non-Javadoc)
	 * @see processing.core.PApplet#draw()
	 */
	
	
	
	
	
	@Override
	public void draw() {
		background(200);

//		if (counter >= 500) {
//			for (int i = 0; i < animals.size(); i++) {
//				Animal bigfoot = anaimals.get[i];
//				System.out.println(bigfoot.getFertilityLevel());
//			}
//		}

		if (!paused)
			simulator.simulateOneStep();
		simulator.drawField();
		simulator.drawGraph();
	}

	// handle key presses
	public void keyReleased() {
		if (key == 's') { // 's' saves the current state to a file
			simulator.writeToFile(saveFilePath);
		}

		if (key == 'l') { // 'l' loads a saved state
			simulator.readFile(saveFilePath);
		}

		if (key == 'p') { // 'p' toggles paused and unpaused
			paused = !paused;
		}

		if (key == 'c') { // 'c' resets the simulator
			simulator.reset();
		}
	}

	// if mouse clicked, let the simulator handle the mouse click
	public void mouseClicked() {
		simulator.handleMouseClick(mouseX, mouseY);
	}

	// if mouse is dragged, let the simulator handle the mouse drag
	public void mouseDragged() {
		simulator.handleMouseDrag(mouseX, mouseY);
	}

	// public static void main(String[] args) {
	// PApplet.main(new String[] { "Main" });
	// }
}