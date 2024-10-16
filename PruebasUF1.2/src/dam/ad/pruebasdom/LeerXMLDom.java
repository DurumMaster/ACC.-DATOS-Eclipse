package dam.ad.pruebasdom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class LeerXMLDom {
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// Crea una instancia de DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		// Creamos el parser
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		// Leer el archivo XML
		Document doc = builder.parse(new File("Ficheros/empleados.xml"));
		
		// Normalizar el archivo XML (eliminar espacios y tabuladores que no se necesiten)
		doc.getDocumentElement().normalize();
		
		// Obtener la lista de elementos que contienen la etiqueta "empleado"
		NodeList listaEmpleados = doc.getElementsByTagName("empleado");
		
		// Recorrer la lista de empleados
		for (int i = 0; i < listaEmpleados.getLength(); i++) {
			
			// Obtener el primer elemento en la lista del nodo principal
			Node empleado = listaEmpleados.item(i);
			
			// COmprobar que el elemento es de tipo ELEMENT, y no d etexto. A continuación lo ceonvertimos a tipo Element.
			if (empleado.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) empleado;
				
				// Obtengo los valores de cada campo del elemento empleado
				String id = elemento.getElementsByTagName("id").item(0).getTextContent();
				String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
				String apellido = elemento.getElementsByTagName("apellido").item(0).getTextContent();
				
				// Imprimir los datos de elemento empleado
				System.out.println("\nEmpleado ID: " + id + "\nNombre: " + nombre + "\nApellido: " + apellido);
			}
		}
	}
}
