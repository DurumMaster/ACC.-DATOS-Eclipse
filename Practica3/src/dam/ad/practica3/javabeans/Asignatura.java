package dam.ad.practica3.javabeans;

import java.io.Serializable;

public class Asignatura implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codAsignatura;
	private String nombre;
	private String profesor;
	private int numHoras;
	
	public Asignatura(int codAsignatura, String nombre, String profesor, int numHoras) {
		this.codAsignatura = codAsignatura;
		this.nombre = nombre;
		this.profesor = profesor;
		this.numHoras = numHoras;
	}
	
	@Override
	public String toString() {
		return "\nCódigo asignatura: " + codAsignatura
				+ "\nAsignatura: " + nombre
				+ "\nProfesor: " + profesor
				+ "\nNúmero de horas: " + numHoras;
	}
	
	

}
