package dam.ad.uf1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeerFicheroBinarioAleatorio {

	public static void main(String[] args) {
		
		// Ruta de archivo
		String rutaArchivo = "Ficheros/ejemplo.bin";
		
		try (RandomAccessFile archivo = new RandomAccessFile(rutaArchivo, "r")) {
			
			// Mueve el puntero al inicio del archivo para comenzar a leer
			archivo.seek(0);
			
			// Leer la cadena escrita antes con writeUTF()
			String textoLeido = archivo.readUTF();
			System.out.println("Texto leído: " + textoLeido);
			
			// Leer el valor entero
			int numeroEntero = archivo.readInt();
			System.out.println("Entero leído: " + numeroEntero);
			
			// Leer el valor booleano
			boolean valorBooleano = archivo.readBoolean();
			System.out.println("Booleano leído: " + valorBooleano);
			
			// Leer el caracter
			char caracterLeido = archivo.readChar();
			System.out.println("Caracter leído: " + caracterLeido);
			
			// Leer el double
			double numeroDouble = archivo.readDouble();
			System.out.println("Double leído: " + numeroDouble);
			
			// Leer el booleano de nuevo
			// Me coloco el cursos antes del booleano
			// 33 caracteres de la cadena * 2 bytes que ocupa cada caracter + 4 bytes que ocupa un int
			archivo.seek(30*2 + 1*4);
			
			System.out.println("Nuevo booleano leído: " + valorBooleano);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
