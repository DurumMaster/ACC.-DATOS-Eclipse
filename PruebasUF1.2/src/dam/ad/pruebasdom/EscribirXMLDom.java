package dam.ad.pruebasdom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class EscribirXMLDom {

	public static void main(String[] args) {

		// Crear una instancia de DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// Crear el parse
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();

			// DOMImplementation nos facilita el método createDocument para crear un documento XML inicial
			DOMImplementation implementation = builder.getDOMImplementation();

			// Creo un documento vacio indicando el nombre del nodo raíz
			Document doc = implementation.createDocument(null, "empleados", null);
			
			// Asignamos la versión
			doc.setXmlVersion("1.0");
			
			// EMPLEADO 1
			// ------------------------------------
			// PADRE
			// Crear primer elemento empleado1 y añadirlo al nodo raíz
			Element elementoEmpleado1 = doc.createElement("empleado");
			doc.getDocumentElement().appendChild(elementoEmpleado1);
			
			// ELEMENTOS HIJOS
			// Crear elementos finales en el documento XML
			// Primero creo el elemento (Element o Text) y luego lo asigno a un nodo que ya exista (elementoEmpleado1 o elementoId1)
			Element elementoId1 = doc.createElement("id");
			Text textoId1 = doc.createTextNode("1");
			
			elementoEmpleado1.appendChild(elementoId1);
			elementoId1.appendChild(textoId1);
			
			Element elementoNombre1 = doc.createElement("nombre");
			Text textoNombre1 = doc.createTextNode("Axel");
			
			elementoEmpleado1.appendChild(elementoNombre1);
			elementoNombre1.appendChild(textoNombre1);
			
			Element elementoApellido1 = doc.createElement("apellido");
			Text textoApellido1 = doc.createTextNode("León");
			
			elementoEmpleado1.appendChild(elementoApellido1);
			elementoApellido1.appendChild(textoApellido1);
			
			Element elementoEmail1 = doc.createElement("email");
			Text textoEmail1 = doc.createTextNode("AxelLeon@gmail.com");
			
			elementoEmpleado1.appendChild(elementoEmail1);
			elementoEmail1.appendChild(textoEmail1);
			
			// EMPLEADO 2
			// ------------------------------------
			Element elementoEmpleado2 = doc.createElement("empleado");
			doc.getDocumentElement().appendChild(elementoEmpleado2);
			
			Element elementoId2 = doc.createElement("id");
			Text textoId2 = doc.createTextNode("2");
			
			elementoEmpleado2.appendChild(elementoId2);
			elementoId2.appendChild(textoId2);
			
			Element elementoNombre2 = doc.createElement("nombre");
			Text textoNombre2 = doc.createTextNode("Carlos");
			
			elementoEmpleado2.appendChild(elementoNombre2);
			elementoNombre2.appendChild(textoNombre2);
			
			Element elementoApellido2 = doc.createElement("apellido");
			Text textoApellido2 = doc.createTextNode("Elvira");
			
			elementoEmpleado2.appendChild(elementoApellido2);
			elementoApellido2.appendChild(textoApellido2);
			
			Element elementoEmail2 = doc.createElement("email");
			Text textoEmail2 = doc.createTextNode("CarlosElvira@gmail.com");
			
			elementoEmpleado2.appendChild(elementoEmail2);
			elementoEmail2.appendChild(textoEmail2);
			
			// Una vez que hemos generado la estructura, creamos la fuente XML a partir de la estructura
			DOMSource source = new DOMSource(doc);
			
			// Obtenermos un TransformerFactory
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // esto es para que aparezca indecando y no es una sola ínea
			transformer.setOutputProperty(OutputKeys.METHOD, "xml"); // para que me muestre en el xml, la versión y la codificación
			transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "4"); // añadir los 4 espacios por tabulador
			
			// defino el canal de salida y volcamos la fuente XML en el dichero
			StreamResult fichero = new StreamResult(new File("Ficheros/empleados2.xml"));
			transformer.transform(source, fichero);
			
			System.out.println("Archivo XML creado correctamente");
			
			// A modo de aprobación podemos mostrar el documento por pantlala
			StreamResult console = new StreamResult(System.out);
			transformer.transform(source, console);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

}
