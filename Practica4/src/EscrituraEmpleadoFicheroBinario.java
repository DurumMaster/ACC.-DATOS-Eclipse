import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscrituraEmpleadoFicheroBinario {
	
	static final int TAM_NOMBRE = 10; // Tamaño máximo del nombre
	static final int TAM_REG = TAM_NOMBRE * 2 + 4 + 0; // Tamaño de registro

	public static void main(String[] args) {
		// Iniciar manualente la información de empleados
		String[] nombres = {"Jorge", "David", "Pablo", "Inés", "Paula"};
		int[] departamentos = {10, 10, 20, 20, 30};
		double[] salarios = {28000.6, 33000.0, 42000.4, 27000.2, 37000.5};
		
		try (RandomAccessFile raf = new RandomAccessFile("Ficheros/empleados.txt", "rw")) {
			
			// Me posiciono al final del archivo
			raf.seek(raf.length());
			
			// Escribir los datos de los empleados
			StringBuffer sb;
			
			for (int i = 0; i < nombres.length; i++) {
				
				// Escribir los nombres
				sb = new StringBuffer(nombres[i]); // Guardo cada nombre en el String Buffer
				sb.setLength(TAM_NOMBRE); // Ajusto el tamaño a 10 caracteres como máximo
				raf.writeChars(sb.toString()); // Finalmente escribo el nombre del archivo
				
				// Escritura del departamento
				raf.writeInt(departamentos[i]);
				
				// Escritura del salario
				raf.writeDouble(salarios[i]);
				
			}
			System.out.println("Datos de empleados escritos correctamente");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
