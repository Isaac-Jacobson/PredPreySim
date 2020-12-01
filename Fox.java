import java.io.Serializable;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class Fox extends Animal implements Serializable {
	private int RABBIT_FOOD_VALUE = 6;
	private Random rand = new Random();

	private int foodLevel;

	public Fox(boolean randomAge) {
		super(randomAge, 60);
		BREEDING_AGE = 3;
		MAX_AGE = 60;
		BREEDING_PROBABILITY = 0.01;
		MAX_LITTER_SIZE = 6;
		foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
		foodLevel = RABBIT_FOOD_VALUE;

	}

	public void act(Field currentField, Field updatedField, List<Animal> newFoxes) {
		incrementAge();
		incrementHunger();
		if (alive) {
			int births = breed();
			for (int b = 0; b < births; b++) {
				Fox newFox = new Fox(false);
				newFox.setFoodLevel(this.foodLevel);
				newFoxes.add(newFox);
				Location loc = updatedField.randomAdjacentLocation(location);
				newFox.setLocation(loc);
				updatedField.put(newFox, loc);
			}
			Location newLocation = findFood(currentField, location);
			if (newLocation == null) { // no food found - move randomly
				newLocation = updatedField.freeAdjacentLocation(location);
			}
			if (newLocation != null) {
				setLocation(newLocation);
				updatedField.put(this, newLocation);
			} else {
				alive = false;
			}
		}
	}

	private void incrementHunger() {
		foodLevel--;
		if (foodLevel <= 0) {
			alive = false;
		}
	}

	private Location findFood(Field field, Location location) {
		List<Location> adjacentLocations = field.adjacentLocations(location);

		for (Location where : adjacentLocations) {
			Object animal = field.getObjectAt(where);
			if (animal instanceof Rabbit) {
				Rabbit rabbit = (Rabbit) animal;
				if (rabbit.isAlive()) {
					rabbit.setEaten();
					foodLevel = RABBIT_FOOD_VALUE;
					return where;
				}
			}
		}

		return null;
	}

	public void setFoodLevel(int fl) {
		this.foodLevel = fl;
	}

	public void setEaten() {
		alive = false;
	}
}