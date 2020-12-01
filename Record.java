
/**
 *Recorder to allow serialization of current state of simulation
 * @author Sitar Harel 2012.1.25
 */

import java.io.Serializable;
import java.util.List;

public class Record implements Serializable {
	private List<Animal> animals;
	private int steps;

	// The current state of the field.
	private Field field;

	public Record(List<Animal> a, Field field, int step) {
		setAnimals(a);
		setField(field);
		setSteps(step);
	}

	private void setAnimals(List<Animal> a) {
		animals = a;

	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
}
