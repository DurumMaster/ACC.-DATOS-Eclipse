package dam.ad.ej2.javabeans;

import java.io.Serializable;

public class ActividadParque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String zona;
	private double minutos;

	public ActividadParque(int id, String nombre, String zona, double minutos) {
		this.id = id;
		this.nombre = nombre;
		this.zona = zona;
		this.minutos = minutos;
	}

	@Override
	public String toString() {
		return "\nID: " + id + "\nNombre: " + nombre + "\nZona: " + zona + "\nMinutos: " + minutos;
	}

	public int getId() {
		return id;
	}
}
