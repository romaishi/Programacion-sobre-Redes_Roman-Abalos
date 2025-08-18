package examen_1;

public class TutiFruti {
	private String letter;
	private String color;
	private String animal;
	private String object;
	private String food;

	public TutiFruti(String letter, String color, String animal, String object, String food) {
		this.letter = letter.toUpperCase();
		this.color = color;
		this.animal = animal;
		this.object = object;
		this.food = food;
	}

	
	
	public String getLetra() {
		return letter;
	}
	
	public String toCSV() {
		return letter + ";" + color + ";" + animal + ";" + object + ";" + food;
	}
	

	public String getColor() {
		return color;
	}
	
	public String getAnimal() {
		return animal;
	}
	
	public String getObject() {
		return object;
	}

	public String getFood() {
		return food;
	}
}  

