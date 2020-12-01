import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Rabbit extends Animal implements Serializable {

	public Rabbit(boolean randomAge) {
		super(randomAge, 20);

		MAX_AGE = 20;
		BREEDING_AGE = 3;
		BREEDING_PROBABILITY = 0.06;
		MAX_LITTER_SIZE = 8;
	}

	public void act(Field currentField, Field updatedField, List<Animal> newRabbits) {
		incrementAge();
		if (alive) {
			int births = breed();
			for (int b = 0; b < births; b++) {
				Rabbit newRabbit = new Rabbit(false);
				newRabbits.add(newRabbit);
				Location loc = updatedField.randomAdjacentLocation(location);
				newRabbit.setLocation(loc);
				updatedField.put(newRabbit, loc);
			}
			Location newLocation = updatedField.freeAdjacentLocation(location);
			if (newLocation != null) {
				setLocation(newLocation);
				updatedField.put(this, newLocation);
			} else {
				alive = false;
			}
		}
	}

	public void setEaten() {
		alive = false;
	}

}