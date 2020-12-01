import java.io.Serializable;
import java.util.List;

public class Bigfoot extends Animal implements Serializable {
	protected int RABBIT_FOOD_VALUE = 10;

	protected int foodLevel;
//	protected int fertilityLevel;

	public Bigfoot(boolean randomAge) {
		super(randomAge, 100);

//		fertilityLevel = (int) (Math.random() * 3 + 1);

		BREEDING_AGE = 16;
		MAX_AGE = 100;
		BREEDING_PROBABILITY = 0.01 ;
		MAX_LITTER_SIZE = 1;

		foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
		foodLevel = RABBIT_FOOD_VALUE;

	}

	public void act(Field currentField, Field updatedField, List<Animal> newBigfeet) {
		incrementAge();
		incrementHunger();
		if (alive) {
			int births = breed();
			for (int b = 0; b < births; b++) {
				Bigfoot newBigfoot = new Bigfoot(false);
				newBigfoot.setFoodLevel(this.foodLevel);
//				newBigfoot.setFertilityLevel(this.fertilityLevel + (int) (Math.random() * 5 - 3));
				newBigfeet.add(newBigfoot);
				Location loc = updatedField.randomAdjacentLocation(location);
				newBigfoot.setLocation(loc);
				updatedField.put(newBigfoot, loc);
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

//	protected void setFertilityLevel(int fertilityLevel2) {
//		this.fertilityLevel = fertilityLevel2;
//	}

	protected Location findFood(Field field, Location location) {
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
			} else if (animal instanceof Fox) {
				Fox fox = (Fox) animal;
				if (fox.isAlive()) {
					fox.setEaten();
					foodLevel = RABBIT_FOOD_VALUE;
					return where;
				}
			}
		}
		return null;

	}

	private void incrementHunger() {
		foodLevel--;
		if (foodLevel <= 0) {
			alive = false;
		}
	}

	public void setFoodLevel(int fl) {
		this.foodLevel = fl;
	}

}
