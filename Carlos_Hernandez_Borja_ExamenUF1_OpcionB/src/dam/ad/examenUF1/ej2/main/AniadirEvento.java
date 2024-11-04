package dam.ad.examenUF1.ej2.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
import org.xml.sax.SAXException;

import dam.ad.examenUF1.ej2.javabeans.Evento;
import dam.ad.examenUF1.ej2.javabeans.ListaEventos;

public class AniadirEvento {

	private static int ID = 1;
	private static ListaEventos listaEventos = new ListaEventos();
	private static final Scanner sc = new Scanner(System.in);
	private static final File xmlFile = new File("Ficheros/eventos.xml");

	public static void main(String[] args) {

		// Volcar todo XML en lista

		cargarXML();

		// Añadir evento nuevo lista

		Evento evento = solicitarDatos();
		listaEventos.addEvento(evento);

		// Generar XML con DOM

		generarXML();

	}

	private static void cargarXML() {

		if (xmlFile.exists()) {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(xmlFile);

				doc.getDocumentElement().normalize();
				NodeList nodeEventList = doc.getElementsByTagName("evento");

				for (int i = 0; i < nodeEventList.getLength(); i++) {

					Node evento = nodeEventList.item(i);

					if (evento.getNodeType() == Node.ELEMENT_NODE) {
						Element elemento = (Element) evento;

						int id = Integer.parseInt(elemento.getElementsByTagName("id").item(0).getTextContent());
						String nombreEvento = elemento.getElementsByTagName("nombreEvento").item(0).getTextContent();
						String fecha = elemento.getElementsByTagName("fecha").item(0).getTextContent();
						String lugar = elemento.getElementsByTagName("lugar").item(0).getTextContent();
						String organizador = elemento.getElementsByTagName("organizador").item(0).getTextContent();

						listaEventos.addEvento(new Evento(id, nombreEvento, fecha, lugar, organizador));
						
						ID = listaEventos.getListaEventos().size() + 1;
					}
				}
				
				System.out.println("- Se han cargado los datos del XML con éxito -\n");
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static void generarXML() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		try {
			builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document doc = implementation.createDocument(null, "Eventos", null);
			doc.setXmlVersion("1.0");

			for (Evento e : listaEventos.getListaEventos()) {
				Element evento = doc.createElement("evento");
				doc.getDocumentElement().appendChild(evento);

				Element id = doc.createElement("id");
				Text tId = doc.createTextNode(String.valueOf(e.getId()));
				evento.appendChild(id);
				id.appendChild(tId);

				Element nombre = doc.createElement("nombreEvento");
				Text tNombre = doc.createTextNode(e.getNombreEvento());
				evento.appendChild(nombre);
				nombre.appendChild(tNombre);

				Element fecha = doc.createElement("fecha");
				Text tFecha = doc.createTextNode(e.getFecha());
				evento.appendChild(fecha);
				fecha.appendChild(tFecha);

				Element lugar = doc.createElement("lugar");
				Text tLugar = doc.createTextNode(e.getLugar());
				evento.appendChild(lugar);
				lugar.appendChild(tLugar);

				Element organizador = doc.createElement("organizador");
				Text tOrganizador = doc.createTextNode(e.getOrganizador());
				evento.appendChild(organizador);
				organizador.appendChild(tOrganizador);
			}

			DOMSource source = new DOMSource(doc);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "4");

			StreamResult fichero = new StreamResult(xmlFile);
			transformer.transform(source, fichero);

			System.out.println("\n- Archivo XML creado con éxito -");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	private static Evento solicitarDatos() {

		String nombreEvento = solicitarCadena("el nombre");
		String fecha = solicitarCadena("la fecha");
		String lugar = solicitarCadena("el lugar");
		String organizador = solicitarCadena(" el organizador");

		sc.close();

		return new Evento(ID++, nombreEvento, fecha, lugar, organizador);
	}

	private static String solicitarCadena(String valor) {

		String cadena = "";
		boolean esValido = false;

		while (!esValido) {
			System.out.print("Introduce " + valor + " del evento: ");
			cadena = sc.nextLine().trim();

			if (cadena.isBlank()) {
				System.out.println("Debe introducir " + valor + "!!!");
			} else {
				esValido = true;
			}
		}
		return cadena;
	}

}
