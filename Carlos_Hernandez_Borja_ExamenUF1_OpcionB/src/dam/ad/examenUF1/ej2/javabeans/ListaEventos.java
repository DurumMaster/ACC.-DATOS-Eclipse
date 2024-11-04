package dam.ad.examenUF1.ej2.javabeans;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaEventos implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Evento> eventos;
	
	public ListaEventos() {
		eventos = new ArrayList<Evento>();
	}
	
	public void addEvento(Evento e) {
		eventos.add(e);
	}
	
	public ArrayList<Evento> getListaEventos() {
		return eventos;
	}

}
