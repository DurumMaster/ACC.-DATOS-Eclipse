package dam.ad.practica9.javabeans;

public class Direccion {
	
	private String calle;
	private String ciudad;
	private String provincia;
	private int codPostal;
	
	public Direccion(String calle, String ciudad, String provincia, int codPostal) {
		this.calle = calle;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.codPostal = codPostal;
	}

	public String getCalle() {
		return calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public int getCodPostal() {
		return codPostal;
	}
	
	

}
