package dam.ad.practica6.javabeans;

import java.io.Serializable;

public class Producto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProducto;
	private String nombre;
	private double precio;
	
	public Producto(int idProducto, String nombre, double precio) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	

	@Override
	public String toString() {
		return "\n ID producto: " + idProducto + "\n Nombre: " + nombre + "\n Precio: " + precio + "€";
	}



	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	
}
