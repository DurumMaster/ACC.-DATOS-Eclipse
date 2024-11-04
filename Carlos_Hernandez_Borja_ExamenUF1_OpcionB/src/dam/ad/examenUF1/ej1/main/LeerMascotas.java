package dam.ad.examenUF1.ej1.main;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import dam.ad.examenUF1.ej1.javabeans.Mascota;

public class LeerMascotas {

	private static final String DAT_PATH = "Ficheros/mascotas.dat";

	public static void main(String[] args) {

		// Mostrar mascotas del archivo binario

		boolean finalArch = false;
		
		System.out.println("******************");
		System.out.println("**** MASCOTAS ****");
		System.out.println("******************\n");
		System.out.println("--------------------------------------------");

		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(DAT_PATH)))) {

			while (!finalArch) {
				try {
					System.out.println((Mascota) ois.readObject());
				} catch (Exception e) {
					finalArch = true;
				}
			}
			System.out.println("\n--------------------------------------------");
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido encontrar el archivo '" + DAT_PATH + "'.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al leer las mascotas en el archivo '" + DAT_PATH + "'.");
			e.printStackTrace();
		}
	}

}
