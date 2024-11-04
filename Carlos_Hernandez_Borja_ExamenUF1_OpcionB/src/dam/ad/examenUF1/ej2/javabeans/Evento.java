package dam.ad.examenUF1.ej2.javabeans;

import java.io.Serializable;

public class Evento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombreEvento;
	private String fecha;
	private String lugar;
	private String organizador;

	public Evento(int id, String nombreEvento, String fecha, String lugar, String organizador) {
		this.id = id;
		this.nombreEvento = nombreEvento;
		this.fecha = fecha;
		this.lugar = lugar;
		this.organizador = organizador;
	}

	@Override
	public String toString() {
		return "\nID: " + id
				+ "\nNombre: " + nombreEvento
				+ "\nFecha: " + fecha
				+ "\nLugar: " + lugar
				+ "\nOrganizador: "+ organizador;
	}

	public int getId() {
		return id;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public String getFecha() {
		return fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public String getOrganizador() {
		return organizador;
	}

}
