package ejercicio1;

public class main {

	public static void main(String[] args) {
		Thread hilo = new Thread(new HiloAlfanumerico());
		hilo.start();
	}

}