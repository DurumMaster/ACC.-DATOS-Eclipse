package dam.ad.ej2.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import dam.ad.ej2.javabeans.ActividadParque;
import dam.ad.ej2.javabeans.ListaActividades;

public class Main {

	private static final Scanner sc = new Scanner(System.in);
	private static final String XML_PATH = "Ficheros/actividades.xml";
	private static ListaActividades listaActividades = new ListaActividades();
	private static int ID = 1;

	public static void main(String[] args) {

		String opt = "";

		cargarXML();

		while (!opt.equals("3")) {
			System.out.println("\nElija una opción: ");
			System.out.println("1. Registrar Actividad");
			System.out.println("2. Mostrar XML por consola");
			System.out.println("3. Salir");
			opt = sc.nextLine();

			switch (opt) {
			case "1":
				registrarActividad();
				break;
			case "2":
				if (!listaActividades.getListaActividades().isEmpty()) {
					mostrarXMLConsola();					
				} else {
					System.out.println("Aún no hay actividades cargadas!!!");
				}
				break;
			case "3": 
				break;
			default:
				System.out.println("Introduce una opción válida");
				break;
			}
		}

		sc.close();

	}

	private static void mostrarXMLConsola() {
		
		XStream xs = new XStream();

		xs.addPermission(NoTypePermission.NONE);
		xs.addPermission(NullPermission.NULL);
		xs.addPermission(PrimitiveTypePermission.PRIMITIVES);

		Class[] clase = { ActividadParque.class, ListaActividades.class };
		xs.allowTypes(clase);

		xs.allowTypesByWildcard(new String[] { "dam.ad.ej2.*" });

		xs.alias("actividad", ActividadParque.class);
		xs.alias("Actividades", ListaActividades.class);

		xs.addImplicitCollection(ListaActividades.class, "listaActividades");

		try (FileInputStream fis = new FileInputStream(XML_PATH)) {

			listaActividades = (ListaActividades) xs.fromXML(fis);
			
			for (ActividadParque actividad : listaActividades.getListaActividades()) {
				System.out.println(actividad);
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error");
			e.printStackTrace();
		}
		
		
	}

	private static void cargarXML() {

		File file = new File(XML_PATH);

		if (file.exists()) {
			try {

				XStream xs = new XStream();

				xs.addPermission(NoTypePermission.NONE);
				xs.addPermission(NullPermission.NULL);
				xs.addPermission(PrimitiveTypePermission.PRIMITIVES);

				Class[] clase = { ActividadParque.class, ListaActividades.class };
				xs.allowTypes(clase);

				xs.allowTypesByWildcard(new String[] { "dam.ad.ej2.*" });

				xs.alias("actividad", ActividadParque.class);
				xs.alias("Actividades", ListaActividades.class);

				xs.addImplicitCollection(ListaActividades.class, "listaActividades");

				try (FileInputStream fis = new FileInputStream(file)) {

					listaActividades = (ListaActividades) xs.fromXML(fis);
					
					ID = listaActividades.getListaActividades().size() + 1;

				}
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error");
				e.printStackTrace();
			}
		}

	}

	private static void registrarActividad() {

		listaActividades.addActividad(solicitarDatos());

		XStream xs = new XStream();

		xs.addPermission(NoTypePermission.NONE);
		xs.addPermission(NullPermission.NULL);
		xs.addPermission(PrimitiveTypePermission.PRIMITIVES);

		Class[] clase = { ActividadParque.class, ListaActividades.class };
		xs.allowTypes(clase);

		xs.allowTypesByWildcard(new String[] { "dam.ad.ej2.*" });

		xs.alias("actividad", ActividadParque.class);
		xs.alias("Actividades", ListaActividades.class);

		xs.addImplicitCollection(ListaActividades.class, "listaActividades");

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(XML_PATH);
			xs.toXML(listaActividades, fos);
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo actividades.xml");
			e.printStackTrace();
		}

		System.out.println("Actividad Registrada!!");
	}

	private static ActividadParque solicitarDatos() {

		System.out.println("SOLICITAR DATOS");

		String nombre = solicitarCadena("Introduce el nombre de la actividad: ", "nombre");
		String zona = solicitarCadena("Introduce la zona de la actividad: ", "zona");
		double tiempo = solicitarDouble("Introduce el tiempo de la actividad en minutos");

		return new ActividadParque(ID++, nombre, zona, tiempo);
	}

	private static double solicitarDouble(String message) {
		double num = 0;
		boolean esValido = false;

		while (!esValido) {
			try {
				System.out.print(message);
				num = sc.nextDouble();
				esValido = true;

			} catch (Exception e) {
				System.out.println("Debe introducir un número válido");
				sc.nextLine();
			}
		}
		sc.nextLine();
		return num;
	}

	private static String solicitarCadena(String message, String valor) {

		String cadena = "";
		boolean esValido = false;

		while (!esValido) {
			System.out.print(message);
			cadena = sc.nextLine().trim();

			if (cadena.isBlank()) {
				System.out.println("Debe introducir un " + valor);
			} else {
				esValido = true;
			}
		}
		return cadena;
	}

}
