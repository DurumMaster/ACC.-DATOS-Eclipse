package dam.ad.uf1;

import java.io.File;

public class ComprobarDirectorio {

	public static void main(String[] args) {
		
		// Instanciamos objeto de la clase File y pasamos el nombre del directorio a comprobar
		File file = new File("Ficheros");
		
		// Verificamos si el fichero o el directorio existe
		if (file.exists()) {
			String message = "El ";
			// Verificar si es un directorio o un fichero
			message += file.isDirectory() 
			? " directorio "
			: " fichero ";
			message += " ya existe";
			System.out.println(message
					+ "\nNombre: " + file.getName()
					+ "\nRuta completa: " + file.getAbsolutePath()
					+ "\nTamaño directorio: " + file.length()
					);
			
			// Mostrar lista de archivos  dentro del directorio
			File[] listaArchivos = file.listFiles();
			System.out.println("\nLista de Archivos del directorio");
			System.out.println("-----------------------------------");
			for (File archivo : listaArchivos) {
				System.out.println(archivo.getName()); // Imprimir por pantalla cada uno de los nombres de los archivos
			}
			
		} else {
			System.out.println("El elemento no existe");
		}
	}

}
