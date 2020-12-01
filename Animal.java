import java.util.Random;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Animal {
	protected int MAX_AGE;
	protected int BREEDING_AGE;
	protected double BREEDING_PROBABILITY;
	protected int MAX_LITTER_SIZE;
	protected Random rand = new Random();
	protected Location location;
	protected int age;
	protected boolean alive;
	public Animal(boolean randomAge, int MAX_AGE) {
		age = 0;
		alive = true;
		if (randomAge) {
			age = rand.nextInt(MAX_AGE);
		}
	}

	public void act(Field field, Field updateField, java.util.List<Animal> newAnimals) {
	}

	protected void incrementAge() {
		age++;
		if (age > MAX_AGE) {
			alive = false;
		}
	}

	protected int breed() {
		int births = 0;
		if (canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
			births = rand.nextInt(MAX_LITTER_SIZE) + 1;
		}
		return births;
	}

	protected boolean canBreed() {
		return age >= BREEDING_AGE;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setLocation(int row, int col) {
		this.location = new Location(row, col);
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}