package dam.ad.practica5.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.Scanner;

import dam.ad.practica5.javabean.Libro;

public class InsertLibrosAlea {

	static Scanner sc = new Scanner(System.in);

	static final int TAM_TITULO = 30;
	static final int TAM_AUTOR = 20;

	static final int TAM_REG = 4 + TAM_TITULO * 2 + TAM_AUTOR * 2 + 4 + 4; // 112 bytes

	public static void main(String[] args) {

		int id;
		Libro libro;
		boolean continuar = true;

		try (RandomAccessFile raf = new RandomAccessFile("Ficheros/libros.dat", "rw")) {
			while (continuar) {

				if (raf.length() > 0) {

					raf.seek(raf.length() - TAM_REG);

					id = raf.readInt();
					id++;
				} else {
					id = 1;
				}

				libro = solicitarDatos(id);

				insertLibro(raf, libro);

				System.out.println("¿Desea introducir otro libro? (SI/NO)");
				continuar = sc.nextLine().equalsIgnoreCase("si");
			}

			sc.close();

			System.out.println(" * * * LIBROS * * *");

			leer(raf);
			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el fichero.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al escribir en el archivo");
			e.printStackTrace();
		}

	}

	private static void leer(RandomAccessFile raf) throws IOException {

		raf.seek(0);

		int id;
		String titulo;
		char[] cTitulo = new char[TAM_TITULO];
		String autor;
		char[] cAutor = new char[TAM_AUTOR];
		int anioEd;
		int nPag;

		StringBuilder response = new StringBuilder();

		while (raf.getFilePointer() < raf.length()) {
			id = raf.readInt();
			response.append("\nid: " + id);

			for (int i = 0; i < TAM_TITULO; i++) {
				cTitulo[i] = raf.readChar();
			}
			titulo = new String(cTitulo).trim();
			response.append("\nTítulo: " + titulo);

			for (int i = 0; i < TAM_AUTOR; i++) {
				cAutor[i] = raf.readChar();
			}
			autor = new String(cAutor).trim();
			response.append("\nAutor: " + autor);

			anioEd = raf.readInt();
			response.append("\nAño de edición: " + anioEd);

			nPag = raf.readInt();
			response.append("\nNúmero de páginas: " + nPag + "\n");
		}

		System.out.println(response);

	}

	private static void insertLibro(RandomAccessFile raf, Libro libro) throws IOException {
		raf.seek(raf.length());

		raf.writeInt(libro.getId());
		raf.writeChars(libro.getTitulo().toString());
		raf.writeChars(libro.getAutor().toString());
		raf.writeInt(libro.getAnioEd());
		raf.writeInt(libro.getnPag());
	}

	private static Libro solicitarDatos(int id) {

		String titulo = solicitarCadena(TAM_TITULO, "Introduce el título del libro: ", "titulo");
		StringBuffer sbTitulo = new StringBuffer(titulo);
		sbTitulo.setLength(TAM_TITULO);

		String autor = solicitarCadena(TAM_AUTOR, "Introduce el autor del libro: ", "autor");
		StringBuffer sbAutor = new StringBuffer(autor);
		sbAutor.setLength(TAM_AUTOR);

		int anioEd = solicitarEntero("Introduce el año de edición del libro: ", 0, LocalDate.now().getYear());
		int nPag = solicitarEntero("Introduce el número de páginas del libro: ", 1, Integer.MAX_VALUE);

		return new Libro(id, sbTitulo, sbAutor, anioEd, nPag);

	}

	private static int solicitarEntero(String message, int min, int max) {
		int num = 0;
		boolean esValido = false;
		
		while (!esValido) {
			try {
				System.out.print(message);
				num = sc.nextInt();
				
				if (num < min || num > max) {
					System.out.println("Debe introducir un número entre " + min + " y " + max);
				} else {
					esValido = true;
				}
			} catch (Exception e) {
				System.out.println("Debe introducir un número válido");
				sc.nextLine();
			}
		}
		sc.nextLine();
		return num;
	}

	private static String solicitarCadena(int tamMax, String message, String valor) {

		String cadena = "";
		boolean esValido = false;

		while (!esValido) {
			System.out.print(message + "(máx. " + tamMax + " caracteres): ");
			cadena = sc.nextLine().trim();

			if (cadena.length() > TAM_TITULO) {
				System.out.println("El " + valor + " no puede superar los " + TAM_TITULO + " caracteres");
			} else if (cadena.isBlank()) {
				System.out.println("Debe introducir un " + valor);
			} else {
				esValido = true;
			}
		}
		return cadena;
	}

}
