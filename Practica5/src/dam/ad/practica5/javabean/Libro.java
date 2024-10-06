package dam.ad.practica5.javabean;

public class Libro {

	private int id;
	private StringBuffer titulo;
	private StringBuffer autor;
	private int anioEd;
	private int nPag;

	public Libro(int id, StringBuffer titulo, StringBuffer autor, int anioEd, int nPag) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anioEd = anioEd;
		this.nPag = nPag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StringBuffer getTitulo() {
		return titulo;
	}

	public void setTitulo(StringBuffer titulo) {
		this.titulo = titulo;
	}

	public StringBuffer getAutor() {
		return autor;
	}

	public void setAutor(StringBuffer autor) {
		this.autor = autor;
	}

	public int getAnioEd() {
		return anioEd;
	}

	public void setAnioEd(int anioEd) {
		this.anioEd = anioEd;
	}

	public int getnPag() {
		return nPag;
	}

	public void setnPag(int nPag) {
		this.nPag = nPag;
	}

}
