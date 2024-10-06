package dam.ad.uf1;

import java.io.File;

public class CrearDirectorio {

	public static void main(String[] args) {
		// Especificamos el nombre del directorio
		String nombreDirectorio = "Ficheros";
		
		// Creamos un objeto File para el directorio
		File directorio = new File(nombreDirectorio);
		
		// Creamos el directorio con el nombre definido. Recomendación, comprobar si existe el directorio
		if (directorio.exists()) {
			System.out.println("El directorio ya existe, no se ha podido crear");
		} else {
			directorio.mkdir();
			System.out.println("Se ha creado el directorio: " + nombreDirectorio);
		}

	}

}
