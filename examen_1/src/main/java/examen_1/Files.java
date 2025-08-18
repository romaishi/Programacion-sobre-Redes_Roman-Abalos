package examen_1;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Files {
	
	static File ogFile = new File("datos.dat");
	static File newFile = new File ("tuti_fruti.csv");
	static Set<Character> usedLetters = new HashSet<>();
	
	public static void main(String[] args) {
		fileToCSV();
		
	}
	
	public static void fileToCSV() {
		List<String> formatedText = leerFileConBuffer(ogFile);
		PrintStream ps = new PrintStream(System.out);
	    PrintStream psErr = new PrintStream(System.err);
		
		if (!newFile.exists()) {
			crearFileConPrinter(newFile, formatedText);
            ps.printf(Colors.ANSI_GREEN + "Archivo modificado correctamente. \n" + Colors.ANSI_RESET);
		}
		
		System.setErr(psErr);
	}
	
	public static void crearFileConPrinter(File f, List<String> texto) {
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
                }
            }

            fw = new FileWriter(f);
            pw = new PrintWriter(fw);

            for (String linea : texto) {
                pw.println(linea);
            }

        } catch (IOException e) {
            Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
        } finally {
            try {
                if (pw != null)
                    pw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
	
	public static List<String> leerFileConBuffer(File f) {

        try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr)) {
            try {

                String line;
                List<String> text = new ArrayList<>();
                
                while ((line = br.readLine()) != null) {
                    char letraTuti = line.charAt(1);
                    usedLetters.add(letraTuti);
                    line = Character.toString(letraTuti).toUpperCase() + line.replaceAll("\\.", ";");
                    text.add(line);
                }

                return text;

            } catch (IOException e) {
                Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
            }
        } catch (IOException e) {
            Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
        }

        return null;
    }
	
	public static void showContent() {
		FileReader fr = null;
		BufferedReader br = null;
		PrintStream ps = new PrintStream(System.out);
		
		try {
			fr = new FileReader(newFile);
			br = new BufferedReader(fr);
			
			String line;
			List<String> text = new ArrayList<>();
			int count = 1;
			ps.printf("N° Letra    Color 	Animal    Objeto    Alimento\n");
			while ((line = br.readLine()) != null) {
				line = count + "	" + line;
				line = line.replaceAll(";", "\t\t | ");
				text.add(line);
				
				if (count % 2 == 0) {
					ps.printf(Colors.ANSI_CYAN + line + "\n" );
				} else {
					ps.printf(Colors.ANSI_GREEN + line + "\n");
				}
				
				count++;
			}
		} catch (IOException e) {
			 Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				 Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
			}
		}
		
	}
	
	public static void modifyFileLinkedList() {
		List<String> completeText = new LinkedList<>();
		PrintStream ps = new PrintStream(System.out);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        ps.printf(Colors.ANSI_RED + "Ingrese la línea que desea eliminar: ");
        
		try(BufferedReader br = new BufferedReader(new FileReader(newFile))) 
		{
			int input = Integer.parseInt(br2.readLine().trim());
			String lines = "";
			String EOF = null;
			int count = 1;
			while ((lines = br.readLine()) != EOF)
			{
				if (input != count) {
					completeText.add(lines);					
				}
				
				count ++;
				
			}
			
		} catch (IOException e) {
			Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
		}
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFile))) {
	           for (String line : completeText) {
	                bw.write(line);
	                bw.newLine();
	           }
	    } catch (IOException e) {
	    	Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
	    }
	}
	
	
	public static void cargarUsedLetters() {
	    usedLetters.clear();  
	    try (BufferedReader br = new BufferedReader(new FileReader(newFile))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            if (!line.isEmpty()) {
	                char letra = Character.toUpperCase(line.charAt(0));
	                usedLetters.add(letra);
	            }
	        }
	    } catch (IOException e) {
	        Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
	    }
	}
	

	public static void addDataLinkedList() {
	    cargarUsedLetters();
	    List<String> completeText = new LinkedList<>();
	    PrintStream ps = new PrintStream(System.out);
	    BufferedReader brTuti = new BufferedReader(new InputStreamReader(System.in));

	    String letter = "";
	    String color = "";
	    String animal = "";
	    String object = "";
	    String food = "";

	    boolean achieveParams = false;

	    List<Character> availableLetters = new ArrayList<>();
	    for (char c = 'A'; c <= 'Z'; c++) {
	        if (!usedLetters.contains(c)) {
	            availableLetters.add(c);
	        }
	    }

	    if (availableLetters.isEmpty()) {
	        ps.printf(Colors.ANSI_RED + "No hay letras disponibles para jugar. \n" + Colors.ANSI_RESET);
	        return;
	    }

	    while (!achieveParams) {
	        try {
	            ps.printf(Colors.ANSI_YELLOW + "Letras disponibles: ");
	            for (char c : availableLetters) {
	                ps.printf("%c ", c);
	            }
	            ps.printf("\n" + Colors.ANSI_RESET);

	            ps.printf(Colors.ANSI_BLUE + "Ingrese letra: ");
	            letter = brTuti.readLine().trim().toUpperCase();

	            if (letter.length() != 1 || !availableLetters.contains(letter.charAt(0))) {
	                ps.printf(Colors.ANSI_RED + "Letra no válida o ya usada. Ingrese otra.\n" + Colors.ANSI_RESET);
	                continue;
	            }

	            ps.printf(Colors.ANSI_PURPLE + "Ingrese color: ");
	            color = brTuti.readLine().trim();

	            ps.printf(Colors.ANSI_GREEN + "Ingrese animal: ");
	            animal = brTuti.readLine().trim();

	            ps.printf(Colors.ANSI_RED + "Ingrese objeto: ");
	            object = brTuti.readLine().trim();

	            ps.printf(Colors.ANSI_YELLOW + "Ingrese comida: ");
	            food = brTuti.readLine().trim();

	         
	            char inputLetter = letter.charAt(0);
	            if (color.isEmpty() || animal.isEmpty() || object.isEmpty() || food.isEmpty()) {
	                ps.printf(Colors.ANSI_RED + "Ningún campo puede estar vacío.\n" + Colors.ANSI_RESET);
	                continue;
	            }
	            if (Character.toUpperCase(color.charAt(0)) != inputLetter
	                    || Character.toUpperCase(animal.charAt(0)) != inputLetter
	                    || Character.toUpperCase(object.charAt(0)) != inputLetter
	                    || Character.toUpperCase(food.charAt(0)) != inputLetter) {
	                ps.printf(Colors.ANSI_RED + "Todas las palabras deben empezar con la letra %c.\n" + Colors.ANSI_RESET, inputLetter);
	                continue;
	            }

	            achieveParams = true;

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }



	    List<String> entries = Arrays.asList(color.toLowerCase(), animal.toLowerCase(), object.toLowerCase(), food.toLowerCase());
	    Set<String> unique = new HashSet<>(entries);

	    int score = (unique.size() < entries.size()) ? 5 : 10;

	    ps.printf(Colors.ANSI_GREEN + "Puntaje de la jugada: %d puntos.\n" + Colors.ANSI_RESET, score);


	    TutiFruti newGame = new TutiFruti(letter, color, animal, object, food);


	    usedLetters.add(letter.charAt(0));
	    String newData = newGame.toCSV();

	    try (BufferedReader br = new BufferedReader(new FileReader(newFile))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            completeText.add(line);
	        }
	    } catch (IOException e) {
	        Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
	    }

	    completeText.add(newData);

	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFile))) {
	        for (String linea : completeText) {
	            bw.write(linea);
	            bw.newLine();
	        }
	    } catch (IOException e) {
	        Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
	    }
	}
	

}
