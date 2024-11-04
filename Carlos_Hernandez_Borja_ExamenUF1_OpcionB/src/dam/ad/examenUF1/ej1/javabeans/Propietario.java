package dam.ad.examenUF1.ej1.javabeans;

import java.io.Serializable;

public class Propietario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombrePropietario;
	private String telefono;

	public Propietario(String nombrePropietario, String telefono) {
		this.nombrePropietario = nombrePropietario;
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "\n Nombre: " + nombrePropietario + "\n Teléfono: " + telefono;
	}

}
