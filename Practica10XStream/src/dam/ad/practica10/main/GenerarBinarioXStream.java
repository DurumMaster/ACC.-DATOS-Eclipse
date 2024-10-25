package dam.ad.practica10.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import dam.ad.practica10.javabeans.Empleado;
import dam.ad.practica10.javabeans.ListaEmpleados;

public class GenerarBinarioXStream {

	private static final String RUTA_FICHERO_BINARIO = "Ficheros/empleados.dat";
	private static final String RUTA_FICHERO_XML = "Ficheros/empleadosXStream.xml";

	public static void main(String[] args) {

		// 1. Crear la lista de empleados

		ListaEmpleados lista = crearListaEmpleados();

		// 2. Crear el archivo binario empleadosObj.dat
		
		crearArchivoBinario(lista);

		// 3. Generar el archivo empleadosXStream.xml usando XStream (serializar de Java a XML)
		
		generarXMLDesdeBinario();

		// 4. Leer el archivo xml y convertirlo nuevamente a objetos (deserializar XML a Java)
		
		leerArchivoXML();
	}

	private static void leerArchivoXML() {
		
		// Crear instancia de XStream
		XStream xs = new XStream();
		
		// Configurar los permisos básicos de seguridad
		xs.addPermission(NoTypePermission.NONE);
		xs.addPermission(NullPermission.NULL);
		xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
		
		// Especificar las clases permitidas
		Class[] clases = {ListaEmpleados.class, Empleado.class};
		xs.allowTypes(clases);
		
		// Permitir cualquier tipo de procedente del mismo paquete
		xs.allowTypesByWildcard(new String[] {"dam.ad.practica10.*"});
		
		// Las etiquetas XML se corresponden con el nombre de los atributos de la
		// clase, aunque se podrían cambiar usando el método alias
		xs.alias("ListaEmpleados", ListaEmpleados.class);
		xs.alias("Empleado", Empleado.class);
		
		// Para que no aparezca el atributo listaObjetos de la clase ListaObjetos
		// en el XML utilizamos el método addImplicitCollection
		xs.addImplicitCollection(ListaEmpleados.class, "listaEmpleados");
		
		// Establecemos canal al fichero XML y volcamos/deserializamos el
		// contenido en un objetp de tipo ListaObjetos que podemos después leer
		FileInputStream fis;
		try {
			fis = new FileInputStream(RUTA_FICHERO_XML);
			ListaEmpleados listaEmpleados = (ListaEmpleados) xs.fromXML(fis);
			
			System.out.println("4. Lectura del archivo XML realizada con éxito.\n");
			
			// Mostrar el contenido de la lista ya deserializada por consola
			for (Empleado emp : listaEmpleados.getListaEmpleados()) {
				System.out.println(emp);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo");
			e.printStackTrace();
		}
		
	}

	private static void generarXMLDesdeBinario() {
		
		try (ObjectInputStream ois = new ObjectInputStream( new FileInputStream(RUTA_FICHERO_BINARIO))) {
			
			ListaEmpleados listaEmpleados = (ListaEmpleados) ois.readObject();
			
			// Crear instancia de XStream
			XStream xs = new XStream();
			
			// Configurar los permisos básicos de seguridad
			xs.addPermission(NoTypePermission.NONE);
			xs.addPermission(NullPermission.NULL);
			xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
			
			// Especificar las clases permitidas
			Class[] clases = {ListaEmpleados.class, Empleado.class};
			xs.allowTypes(clases);
			
			// Permitir cualquier tipo de procedente del mismo paquete
			xs.allowTypesByWildcard(new String[] {"dam.ad.practica10.*"});
			
			// Las etiquetas XML se corresponden con el nombre de los atributos de la
			// clase, aunque se podrían cambiar usando el método alias
			xs.alias("ListaEmpleados", ListaEmpleados.class);
			xs.alias("Empleado", Empleado.class);
			
			// Para que no aparezca el atributo listaObjetos de la clase ListaObjetos
			// en el XML utilizamos el método addImplicitCollection
			xs.addImplicitCollection(ListaEmpleados.class, "listaEmpleados");
			
			// Serializar a XML
			try {
				
				FileOutputStream fos = new FileOutputStream(RUTA_FICHERO_XML);
				xs.toXML(listaEmpleados, fos);
				
				System.out.println("3. Archivo XML creado con éxito.");
				
			} catch (FileNotFoundException e) {
				System.out.println("Archivo no encontrado");
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error - No se ha encontrado el archivo");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error - No hay creada una clase Empleado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error - Ha ocurrido un error no esperado");
			e.printStackTrace();
		}
		
	}

	private static void crearArchivoBinario(ListaEmpleados lista) {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO_BINARIO))) {
			
			oos.writeObject(lista);
			System.out.println("2. Archivo binario empleados.dat creado con éxito.");
			
		} catch (FileNotFoundException e) {
			System.out.println("Error - Archivo no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error - Ha ocurrido un error no esperado");
			e.printStackTrace();
		}
		
	}

	private static ListaEmpleados crearListaEmpleados() {

		String[] nombres = { "Alberto", "Guillermo", "Alejandro", "Ana", "Patricia" };
		int[] departamentos = { 10, 20, 30, 20, 10 };
		double[] salarios = { 2000.00, 1500.50, 3000.40, 2300.60, 19000.10 };

		// Crear la instancia de ListaEmpleados
		ListaEmpleados listaEmpleados = new ListaEmpleados();

		// Iterar sobre los arrays para crear los objetos empleado y añadirlos a la
		// lista
		for (int i = 0; i < nombres.length; i++) {
			listaEmpleados.addEmpleado(new Empleado(i + 1, nombres[i], departamentos[i], salarios[i]));
		}

		System.out.println("1. Lista de empleados creada con éxito.");
		
		return listaEmpleados;
	}

}
