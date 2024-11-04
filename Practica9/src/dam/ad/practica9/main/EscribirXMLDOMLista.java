package dam.ad.practica9.main;

import java.util.Arrays;
import java.util.List;

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

import dam.ad.practica9.javabeans.Direccion;
import dam.ad.practica9.javabeans.Empleado;

public class EscribirXMLDOMLista {

	public static void main(String[] args) {

		List<Empleado> empleados = crearListaEmpleados();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder;

		try {
			builder = factory.newDocumentBuilder();

			DOMImplementation implementation = builder.getDOMImplementation();

			Document doc = implementation.createDocument(null, "empleados", null);

			doc.setXmlVersion("1.0");

			for (Empleado e : empleados) {

				Element empleado = doc.createElement("empleado");
				doc.getDocumentElement().appendChild(empleado);

				Element id = doc.createElement("id");
				Text tId = doc.createTextNode(String.valueOf(e.getId()));
				empleado.appendChild(id);
				id.appendChild(tId);

				Element nombre = doc.createElement("nombre");
				Text tNombre = doc.createTextNode(e.getNombre());
				empleado.appendChild(nombre);
				nombre.appendChild(tNombre);

				Element apellido = doc.createElement("apellido");
				Text tApellido = doc.createTextNode(e.getApellido());
				empleado.appendChild(apellido);
				apellido.appendChild(tApellido);

				Element direccion = doc.createElement("direccion");
				empleado.appendChild(direccion);

				Element calle = doc.createElement("calle");
				Text tCalle = doc.createTextNode(e.getDireccion().getCalle());
				direccion.appendChild(calle);
				calle.appendChild(tCalle);

				Element ciudad = doc.createElement("ciudad");
				Text tCiudad = doc.createTextNode(e.getDireccion().getCiudad());
				direccion.appendChild(ciudad);
				ciudad.appendChild(tCiudad);

				Element provincia = doc.createElement("provincia");
				Text tProvincia = doc.createTextNode(e.getDireccion().getProvincia());
				direccion.appendChild(provincia);
				provincia.appendChild(tProvincia);

				Element codPostal = doc.createElement("codigoPostal");
				Text tCodPostal = doc.createTextNode(String.valueOf(e.getDireccion().getCodPostal()));
				direccion.appendChild(codPostal);
				codPostal.appendChild(tCodPostal);

			}

			DOMSource source = new DOMSource(doc);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "4");

			StreamResult console = new StreamResult(System.out);
			transformer.transform(source, console);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e1) {
			e1.printStackTrace();
		}

	}

	private static List<Empleado> crearListaEmpleados() {
		List<Empleado> empleados = (List<Empleado>) Arrays.asList(
				new Empleado(1, "Juan", "Pérez", new Direccion("Calle 1", "Madrid", "Madrid", 28001)),
				new Empleado(2, "Ana", "García", new Direccion("Avenida 2", "Barcelona", "Cataluña", 28108)),
				new Empleado(3, "Luis", "Martínez", new Direccion("Calle 3", "Sevilla", "Andalucía", 41001)),
				new Empleado(4, "María", "Rodríguez", new Direccion("Plaza Mayor", "Valencia", "Valencia", 46001)),
				new Empleado(5, "Carlos", "López", new Direccion("Gran Vía", "Bilbao", "País Vasco", 48001)));

		return empleados;
	}

}
