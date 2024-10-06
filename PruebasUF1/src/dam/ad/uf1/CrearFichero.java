package dam.ad.uf1;

import java.io.File;

public class CrearFichero {

	public static void main(String[] args) {
		// Ruta del directorio donde vamos a crear el archivo
		String nombreDirectorio = "Ficheros";
		
		// Creamos un objeto Fle para poder crear el fichero "ejemplo.txt" dentro del directorio "Ficheros"
		File archivo = new File(nombreDirectorio,"ejemplo2.txt");
		
		// Finalmente creamos el fichero
		if (archivo.exists()) {
			System.out.println("El archivo ya existe, no se ha podido crear el fichero");
		} else {
			try {
				archivo.createNewFile();
			} catch (Exception e) {
				System.out.println("No se ha podido crear el fichero debido a una excepción");
			}
			System.out.println("Archivo creado con éxito");
		}

	}

}
