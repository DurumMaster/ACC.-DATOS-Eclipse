package dam.ad.practica10.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaEmpleados implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Lista como atributo
	private List<Empleado> listaEmpleados = new ArrayList<Empleado>();

	// Constructor que inicializa la lista
	public ListaEmpleados(List<Empleado> listaEmpleados) {
		listaEmpleados = new ArrayList<Empleado>();
	}
	
	public ListaEmpleados() {
	}



	// Método para agregar elementos
	public void addEmpleado(Empleado empelado) {
		listaEmpleados.add(empelado);
	}
	
	// Método para obtener lista
	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}
	
	
}
