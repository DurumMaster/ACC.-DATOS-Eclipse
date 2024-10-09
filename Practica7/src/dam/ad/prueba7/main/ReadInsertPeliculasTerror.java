package dam.ad.prueba7.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import dam.ad.prueba7.javabeans.Pelicula;

public class ReadInsertPeliculasTerror {

	static final ArrayList<Pelicula> filmsList = new ArrayList<Pelicula>();
	static final int TAM_TITULO = 35;
	static final int TAM_DIRECTOR = 35;
	static final int TAM_SINOPSIS = 1000;
	static final int TAM_REGISTRO = 4 + TAM_TITULO * 2 + 4 + 4 + TAM_DIRECTOR * 2 + TAM_SINOPSIS * 2;

	public static void main(String[] args) {

		// Leer películas archivo.txt
		readTxtFile();

		// Escribirlas en un archivo binario aleatorio
		writeBinaryAleatory();

		// Leer las películas del archivo binario
		readBinaryAleatory();

	}

	private static void readBinaryAleatory() {
		
		System.out.println("\n* * * PELÍCULAS DE TERROR * * *");

		try (RandomAccessFile raf = new RandomAccessFile("Ficheros/PelisTerror.dat", "r")) {

			raf.seek(0);
			
			int id;
			String titulo;
			char[] cTitulo = new char[TAM_TITULO];
			int anio;
			int duracion;
			String director;
			char[] cDirector = new char[TAM_DIRECTOR];
			String sinopsis;
			char[] cSinopsis = new char[TAM_SINOPSIS];
			
			Pelicula pelicula;

			while (raf.getFilePointer() < raf.length()) {
				
				id = raf.readInt();
				
				for (int i = 0; i < TAM_TITULO; i++) {
					cTitulo[i] = raf.readChar();
				}
				titulo = new String(cTitulo).trim();
				
				anio = raf.readInt();
				
				duracion = raf.readInt();
				
				for (int i = 0; i < TAM_DIRECTOR; i++) {
					cDirector[i] = raf.readChar();
				}
				director = new String(cDirector).trim();
				
				for (int i = 0; i < TAM_SINOPSIS; i++) {
					cSinopsis[i] = raf.readChar();
				}
				sinopsis = new String(cSinopsis).trim();
			
				pelicula = new Pelicula(id, titulo, anio, duracion, director, sinopsis);
				
				System.out.println(pelicula);
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo. Las películas no se han podidio leer.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer las películas.");
			e.printStackTrace();
		}

	}

	private static void writeBinaryAleatory() {

		try (RandomAccessFile raf = new RandomAccessFile("Ficheros/PelisTerror.dat", "rw")) {

			raf.seek(raf.length());

			StringBuffer sbTitulo;
			StringBuffer sbDirector;
			StringBuffer sbSinopsis;
			
			int id = findID(raf);

			for (Pelicula pelicula : filmsList) {
				
				raf.writeInt(id);

				sbTitulo = new StringBuffer(pelicula.getTitulo());
				sbTitulo.setLength(TAM_TITULO);
				raf.writeChars(sbTitulo.toString());

				raf.writeInt(pelicula.getAnio());
				
				raf.writeInt(pelicula.getDuracion());

				sbDirector = new StringBuffer(pelicula.getDirector());
				sbDirector.setLength(TAM_DIRECTOR);
				raf.writeChars(sbDirector.toString());

				sbSinopsis = new StringBuffer(pelicula.getSinopsis());
				sbSinopsis.setLength(TAM_SINOPSIS);
				raf.writeChars(sbSinopsis.toString());
				
				id++;
			}

			System.out.println("\nPelículas escritas en archivo binario aleatorio con éxito.");

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo. Las películas no se han escrito.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al escribir las películas en el archivo binario aleatorio.");
			e.printStackTrace();
		}

	}
	
	private static int findID(RandomAccessFile raf) throws IOException {
		
		int id;
		
		if (raf.length() > 0) {
			id = (int) (raf.length() / TAM_REGISTRO);
			id++;
		} else {
			id = 1;
		}
		return id;
	}
	
	private static void readTxtFile() {

		String rutaArchivo = "Ficheros/pelis_terror.txt";

		try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {

			String line;
			String[] films;

			while ((line = reader.readLine()) != null) {
				films = line.split("-");
				filmsList.add(new Pelicula(0, films[0], Integer.parseInt(films[1]), Integer.parseInt(films[2]),
						films[3], films[4]));
			}
			System.out.println("Se han cargado las películas con éxito.");

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo. Las películas no se han cargado.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al cargar las películas.");
			e.printStackTrace();
		}

	}

}
