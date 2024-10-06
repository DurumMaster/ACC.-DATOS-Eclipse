package dam.ad.uf1;

import dam.ad.uf1.javabean.Alumno;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EscribirFicheroBinarioObjeto {
	
	public static void main(String[] args) {
		Alumno [] alumnos = {
				new Alumno("Juan", 20, "2DAM"),
				new Alumno("Carlos", 11, "2DAM"),
				new Alumno("Miguel", 46, "2DAM"),
				new Alumno("Roberto", 54, "2DAM")
		};
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Ficheros/alumnos.dat"))) {
			
			// Para cada objeto de tipo Alumno en el array alumnos, lo escribimos en el archivo
			for (Alumno alumno : alumnos) {
				oos.writeObject(alumno);
			}
			System.out.println("Alumnos escritos correctamente en el archivo binario");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
