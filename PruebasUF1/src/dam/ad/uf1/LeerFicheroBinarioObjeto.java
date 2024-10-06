package dam.ad.uf1;

import dam.ad.uf1.javabean.Alumno;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeerFicheroBinarioObjeto {
	
	public static void main(String[] args) {
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ficheros/alumnos.dat"))) {
			
			Alumno alumno;
			
			// Bucle para leer objetos del archivo biinario
			while (true) {
				try {
					alumno = (Alumno) ois.readObject(); // Leo el objeto alumnos
					System.out.println(alumno);
				} catch (Exception e) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
