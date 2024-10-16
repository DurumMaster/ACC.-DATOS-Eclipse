package dam.ad.practica10.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

import dam.ad.practica10.javabeans.Empleado;

public class Main {
	
	private static final String RUTA_FICHERO = "Ficheros/empleadosObj.dat";

	public static void main(String[] args) {
		
		escribirBinario();
		
		ArrayList<Empleado> empleados = leerBinario();
		
		escribirXML(empleados);

	}

	private static void escribirXML(ArrayList<Empleado> empleados) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder builder;
		
		try {
			builder = factory.newDocumentBuilder();
			
			DOMImplementation implementation = builder.getDOMImplementation();
			
			Document doc = implementation.createDocument(null, "Empleados", null);
			
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
				
				Element dep = doc.createElement("dep");
				Text tDep = doc.createTextNode(String.valueOf(e.getDepartamento()));
				empleado.appendChild(dep);
				dep.appendChild(tDep);
				
				Element salario = doc.createElement("salario");
				Text tSalario = doc.createTextNode(String.valueOf(e.getSalario()));
				empleado.appendChild(salario);
				salario.appendChild(tSalario);
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

	private static ArrayList<Empleado> leerBinario() {
		
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_FICHERO))) {
			
			Empleado empleado;
			boolean finArchivo = false;
			
			while (!finArchivo) {
				try {
					empleado = (Empleado) ois.readObject();
					empleados.add(empleado);
				} catch (Exception e) {
					finArchivo = true;
				}
				
			}
			
		} catch (FileNotFoundException e1) {
			System.out.println("No se ha encontrado el fichero");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Ha ocurrido un problema al leer en el fichero binario.");
			e1.printStackTrace();
		}
		
		return empleados;
	}

	private static void escribirBinario() {
		String[] nombres = {"Alberto", "Guillermo", "Alejandro", "Ana", "Patricia"};
		int[] departamentos = {10, 20, 30, 20, 10};
		double[] salarios = {2000.00, 1500.50, 3000.40, 2300.60, 1900.10};
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO))) {
			
			for (int i = 0; i < nombres.length; i++) {
				oos.writeObject(new Empleado(i+1, nombres[i], departamentos[i], salarios[i]));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el fichero.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un problema al escribir en el fichero binario.");
			e.printStackTrace();
		}
	}

}
