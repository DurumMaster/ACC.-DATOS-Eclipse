package dam.ad.examenUF1.ej1.main;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

public class EscribirArchivoTexto {
	
	private static final String DAT_PATH = "Ficheros/mascotas.dat";
	private static final String TXT_PATH = "Ficheros/mascotas.txt";
	
	public static void main(String[] args) {
		
		boolean finalArchBi = false;

		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(DAT_PATH)));
				BufferedWriter bw = new BufferedWriter(new FileWriter(TXT_PATH,true));) {

			while (!finalArchBi) {
				try {
					bw.write(ois.readObject().toString()); 
				} catch (Exception e) {
					finalArchBi = true;
				}
			}
			
			System.out.println("Mascotas guardadas en '" + TXT_PATH + "' con éxito.");
		} catch (FileNotFoundException e) {
			System.out.println("No se han podido encontrar algún archivo.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error en la ejecución del programa.");
			e.printStackTrace();
		}
	}
}
