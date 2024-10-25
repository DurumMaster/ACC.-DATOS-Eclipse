package dam.ad.pruebasSax;

import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class LeerXMLSax {

	public static void main(String[] args) {

		try {
			// Crear un SAXParserFactory
			SAXParserFactory factory = SAXParserFactory.newInstance();

			// Crear un SaxParser
			SAXParser parser = factory.newSAXParser();
			
			// Obtener un XMLReader a partir del SAXParser
			XMLReader procXML = parser.getXMLReader();
			
			// Crear un manejador de eventos
			GestorContenido gestor = new GestorContenido();
			
			// Asignar el manejador al procesador XML
			procXML.setContentHandler(gestor);
			
			// Definir el archivo XML a leer
			InputSource archivoXML = new InputSource("Ficheros/empleados.xml");
			
			// 1. PROCESAR E IMPRIMIR archivo XMML en CONSOLA
			System.out.println("Comienzo del documento XML");
			System.out.println("---------------------------\n");
			procXML.parse(archivoXML);
			System.out.println("\n---------------------------");
			System.out.println("Fin del documento XML");
			
			// 2. PROCESAR E IMPRIMIR contenido XML en archivo XML
			// Crear un PrintStream
			PrintStream fileOut = new PrintStream(new FileOutputStream("Ficheros/empleados_generados_con_sax.xml"));
			System.setOut(fileOut);
			procXML.parse(archivoXML);
			
			// Cerramos siempre el PrintStream
			fileOut.close();
			
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error inesperado");
		}

	}

}
