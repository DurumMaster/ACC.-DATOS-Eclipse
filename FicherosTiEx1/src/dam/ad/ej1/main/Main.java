package dam.ad.ej1.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import dam.ad.ej1.javabeans.Competidor;

public class Main {
	
	private static final int TAM_NOMBRE = 30;
	private static final int TAM_VEHICULO = 20;
	private static final int TAM_REGISTRO = 4 + 30 * 2 + 20 * 2 + 8;
	private static final String TXT_PATH = "Ficheros/competidores.txt";
	private static final String DAT_PATH = "Ficheros/competidores.dat";

	public static void main(String[] args) {
		
		try(BufferedReader br = new BufferedReader(new FileReader(TXT_PATH));
				RandomAccessFile raf = new RandomAccessFile(DAT_PATH, "rw");) {
			
			String linea;
			String[] datos;
			StringBuffer sbNombre;
			StringBuffer sbVehiculo;
			
			int id = 1;
			if (raf.length() > 0) {
				id = (int) (raf.length() / TAM_REGISTRO);
				raf.seek(raf.length());
				id++;
			}

			while ((linea = br.readLine()) != null) {
				datos = linea.split(";");
				
				raf.writeInt(id);
				id++;
				
				sbNombre = new StringBuffer(datos[0]);
				sbNombre.setLength(TAM_NOMBRE);
				raf.writeChars(sbNombre.toString());
				
				sbVehiculo = new StringBuffer(datos[1]);
				sbVehiculo.setLength(TAM_VEHICULO);
				raf.writeChars(sbVehiculo.toString());
				
				raf.writeDouble(Double.parseDouble(datos[2]));
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido encontrar el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error inesperado");
			e.printStackTrace();
		}
		
		mostrarBinarioAlea();
		
	}

	private static void mostrarBinarioAlea() {
		
		System.out.println("* * * COMPETIDORES * * *");
		
		try (RandomAccessFile raf = new RandomAccessFile(DAT_PATH, "r")) {
			
			raf.seek(0);
			
			int id;
			char[] cNombre = new char[TAM_NOMBRE];
			String nombre;
			char[] cVehiculo = new char[TAM_VEHICULO];
			String vehiculo;
			double tiempo;
			
			while (raf.getFilePointer() < raf.length()) {
				id = raf.readInt();
				
				for (int i = 0; i < cNombre.length; i++) {
					cNombre[i] = raf.readChar();
				}
				nombre = new String(cNombre).trim();
				
				for (int i = 0; i < cVehiculo.length; i++) {
					cVehiculo[i] = raf.readChar();
				}
				vehiculo = new String(cVehiculo).trim();
				
				tiempo = raf.readDouble();
				
				System.out.println(new Competidor(id, nombre, vehiculo, tiempo));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido encontrar el archivo binario");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error inesperado al leer el archivo  binario");
			e.printStackTrace();
		}
		
	}
	

}
