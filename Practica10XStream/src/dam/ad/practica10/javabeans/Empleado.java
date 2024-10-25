package dam.ad.practica10.javabeans;

import java.io.Serializable;

public class Empleado implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private int departamento;
	private double salario;
	
	public Empleado(int id, String nombre, int departamento, double salario) {
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\nNombre: " + nombre + "\nDepartamento: " + departamento + "\nSalario: " + salario + "\n";
	}
	
	
	
}