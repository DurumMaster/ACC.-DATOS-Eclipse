package dam.ad.uf1;

import java.io.File;

public class ComprobarFicheros {

	public static void main(String[] args) {
		// Establecemos la ruta del directorio
		File file = new File("Ficheros", "ejemplo.txt");

		// Verificamos si el fichero o el directorio existe
		if (file.exists()) {
			String message = "El ";
			// Verificar si es un directorio o un fichero
			message += file.isDirectory() ? " directorio " : " fichero ";
			message += " ya existe";
			System.out.println(message
				+ "\nNombre: " + file.getName() 
				+ "\nRuta completa: " + file.getAbsolutePath()
				+ "\nTamaño directorio: " + file.length());

		} else {
			System.out.println("El elemento no existe");
		}

	}

}
