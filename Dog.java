import java.io.Serializable;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class Dog extends Animal implements Serializable {
	private int RABBIT_FOOD_VALUE = 6;

	private int foodLevel;

	public Dog(boolean randomAge) {
		super(randomAge, 60);
		BREEDING_AGE = 3;
		MAX_AGE = 60;
		BREEDING_PROBABILITY = 0.01;
		MAX_LITTER_SIZE = 6;

		foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
		foodLevel = RABBIT_FOOD_VALUE;

	}

	public void act(Field currentField, Field updatedField, List<Animal> newDogs) {
		incrementAge();
		incrementHunger();
		if (alive) {
			int births = breed();
			for (int b = 0; b < births; b++) {
				Dog newDog = new Dog(false);
				newDog.setFoodLevel(this.foodLevel);
				newDogs.add(newDog);
				Location loc = updatedField.randomAdjacentLocation(location);
				newDog.setLocation(loc);
				updatedField.put(newDog, loc);
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
}
