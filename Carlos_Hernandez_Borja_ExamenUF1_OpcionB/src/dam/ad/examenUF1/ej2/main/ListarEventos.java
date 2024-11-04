package dam.ad.examenUF1.ej2.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import dam.ad.examenUF1.ej2.javabeans.*;

public class ListarEventos {
	
	private static final String XML_PATH = "Ficheros/eventos.xml";

	public static void main(String[] args) {
		
		XStream xs = new XStream();
		
		xs.addPermission(NoTypePermission.NONE);
		xs.addPermission(NullPermission.NULL);
		xs.addPermission(PrimitiveTypePermission.PRIMITIVES);
		
		Class[] clases = {ListaEventos.class, Evento.class};
		xs.allowTypes(clases);
		
		xs.allowTypesByWildcard(new String[] {"dam.ad.examenUF1.ej2.javabeans.*"});
		
		xs.alias("Eventos", ListaEventos.class);
		xs.alias("evento", Evento.class);
		
		xs.addImplicitCollection(ListaEventos.class, "eventos");
		
		FileInputStream fis; 
		try {
			fis = new FileInputStream(XML_PATH);
			
			ListaEventos listaEventos = (ListaEventos) xs.fromXML(fis);
			
			System.out.println("* * * * * * * * * *");
			System.out.println("* * * EVENTOS * * *");
			System.out.println("* * * * * * * * * *");
			System.out.println("-------------------");

			for (Evento e : listaEventos.getListaEventos()) {
				System.out.println(e);
			}
			
			System.out.println("\n-------------------");
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido encontrar el archivo '" + XML_PATH + "'.");
			e.printStackTrace();
		}
	}

}
