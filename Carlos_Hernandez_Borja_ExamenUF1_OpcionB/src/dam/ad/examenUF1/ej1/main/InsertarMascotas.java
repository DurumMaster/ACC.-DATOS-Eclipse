package dam.ad.examenUF1.ej1.main;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dam.ad.examenUF1.ej1.javabeans.Mascota;
import dam.ad.examenUF1.ej1.javabeans.Propietario;

public class InsertarMascotas {
	
	private static final String DAT_PATH = "Ficheros/mascotas.dat";
	private static int ID = 1;

	public static void main(String[] args) {

		// Crear mascotas
		// Escribir en archivo binario
		// ID incremental
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(DAT_PATH)))) {
			
			ArrayList<Mascota> mascotas = crearMascotas();
			
			
			for (Mascota mascota : mascotas) {
				oos.writeObject(mascota);
			}
			
			System.out.println("Mascotas guardadas en '" + DAT_PATH + "' con éxito.");
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido encontrar el archivo '" + DAT_PATH + "'.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al guardar las mascotas en el archivo '" + DAT_PATH + "'.");
			e.printStackTrace();
		}

	}

	private static ArrayList<Mascota> crearMascotas() {

		String[] nombresMascotas = { "Buddy", "Luna", "Max", "Bella", "Rocky" };
		String[] razas = { "Golden Retriever", "Bulldog", "Pastor Alemán", "Beagle", "Husky" };
		int[] edades = { 3, 5, 2, 4, 1 };
		String[] nombresPropietarios = { "Carlos", "María", "Ana", "Javier", "Sofía" };
		String[] telefonos = { "123-456-789", "987-654-321", "456-789-123", "321-654-987", "654-321-987" };

		ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

		for (int i = 0; i < nombresMascotas.length; i++) {
			mascotas.add(new Mascota(ID, nombresMascotas[i], razas[i], edades[i],
					new Propietario(nombresPropietarios[i], telefonos[i])));
			ID++;
		}

		return mascotas;
	}

}
