package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import javabeans.ListaObjetos;
import javabeans.Objeto;

public class LeerXMLSCXStream2 {
	
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
		xs.allowTypesByWildcard(new String[] {"javabeans.*"});
		
		// Las etiquetas XML se corresponden con el nombre de los atributos de la
		// clase, aunque se podrían cambiar usando el método alias
		xs.alias("ListaObjetos", ListaObjetos.class);
		xs.alias("Objeto", Objeto.class);
		
		// Para que no aparexca el atributo listaObjetos de la clase ListaObjetos
		// en el XML utilizamos el método addImplicitCollection
		xs.addImplicitCollection(ListaObjetos.class, "listaObjetos");
		
		// Establecemos canal al fichero XML y volcamos/deserializamos el
		// contenido en un objetp de tipo ListaObjetos que podemos después leer
		FileInputStream fis;
		try {
			fis = new FileInputStream("Ficheros/objetos.xml");
			ListaObjetos listaObjetos = (ListaObjetos) xs.fromXML(fis);
			
			// Mostrar el contenido de la lista ya deserializada por consola
			for (Objeto obj : listaObjetos.getListaObjetos()) {
				System.out.println("Nombre: " + obj.getNombre() + ", Valor: " + obj.getValor());
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo");
			e.printStackTrace();
		}
		
		
		
	}

}
