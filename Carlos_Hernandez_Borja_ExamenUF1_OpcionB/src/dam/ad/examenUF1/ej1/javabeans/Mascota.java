package dam.ad.examenUF1.ej1.javabeans;

import java.io.Serializable;

public class Mascota implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombreMascota;
	private String raza;
	private int edad;
	private Propietario propietario;

	public Mascota(int id, String nombreMascota, String raza, int edad, Propietario propietario) {
		this.id = id;
		this.nombreMascota = nombreMascota;
		this.raza = raza;
		this.edad = edad;
		this.propietario = propietario;
	}

	@Override
	public String toString() {
		return "\nID: " + id
				+ "\nNombre: " + nombreMascota
				+ "\nRaza: " + raza
				+ "\nEdad: " + edad + (edad !=1 ? " años" : " año")
				+ "\nPropietario:" + propietario;
	}
	
	
}
