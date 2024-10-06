package dam.ad.uf1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirFicheroSecuencial {

	public static void main(String[] args) {
		/*No escribe -> comprobar en el github que va a subir*/
		// Ruta fichero ya existente
		String rutaArchivo = "Ficheros/ejemplo.txt";
		
		//Escribir la frase "Hello Worl" en el dichero de forma secuencial
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo,true));
			escritor.write("Hello World");
			escritor.newLine();
			System.out.println("Escritura completada");
		} catch (IOException e) {
			System.out.println("Ocurrió un error al escribir un archivo");
			e.printStackTrace();
		}

	}

}
