package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import javabeans.ListaObjetos;
import javabeans.Objeto;

public class EscribirXMLSCXStream {
	
	public static void main(String[] args) {
		
		// Crear la instancia XStream
		XStream xs = new XStream();
		
		// Configurar los permisos básicos de seguridad
		xs.addPermission(NoTypePermission.NONE);
		xs.addPermission(NullPermission.NULL);
		xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
		
		// Especificar las clases permitidas
		Class[] clases = {ListaObjetos.class, Object.class};
		xs.allowTypes(clases);
		
		// Permitir cualquier tipo de procedente del mismo paquete
		xs.allowTypesByWildcard(new String[] {"*"});
		
		// Las etiquetas XML se corresponden con el nombre de los atributos de la
		// clase, aunque se podrían cambiar usando el método alias
		xs.alias("ListaObjetos", ListaObjetos.class);
		xs.alias("Objeto", Objeto.class);
		
		// Para que no aparexca el atributo listaObjetos de la clase ListaObjetos
		// en el XML utilizamos el método addImplicitCollection
		xs.addImplicitCollection(ListaObjetos.class, "listaObjetos");
		
		// Crear una instancia de ListaObjetos y añado algún Objeto
		ListaObjetos listaObjetos = new ListaObjetos();
		listaObjetos.addObeto(new Objeto("Objeto1", 100));
		listaObjetos.addObeto(new Objeto("Objeto2", 200));
		listaObjetos.addObeto(new Objeto("Objeto3", 300));
		listaObjetos.addObeto(new Objeto("Objeto4", 400));
		listaObjetos.addObeto(new Objeto("Objeto5", 500));
		
		// Serializar a XML
		try {
			
			FileOutputStream fos = new FileOutputStream("Ficheros/objetos.xml");
			xs.toXML(listaObjetos, fos);
			
			System.out.println("Archivo creado con éxito");
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
			e.printStackTrace();
		}
		
		
		
	}

}
