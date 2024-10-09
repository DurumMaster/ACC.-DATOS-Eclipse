package dam.ad.prueba7.javabeans;

import java.io.Serializable;

public class Pelicula implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int id;
		private String titulo;
		private int anio;
		private int duracion;
		private String director;
		private String sinopsis;
		
		public Pelicula(int id, String titulo, int anio, int duracion, String director, String sinopsis) {
			this.id = id;
			this.titulo = titulo;
			this.anio = anio;
			this.duracion = duracion;
			this.director = director;
			this.sinopsis = sinopsis;
		}

		@Override
		public String toString() {
			return "\nID: " + id + "\nTítulo: " + titulo + "\nAño: " + anio
					+ "\nDuración: " + duracion + " minutos"
					+ "\nDirector: " + director + "\nSinopsis: " + sinopsis;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public int getAnio() {
			return anio;
		}

		public void setAnio(int anio) {
			this.anio = anio;
		}

		public int getDuracion() {
			return duracion;
		}

		public void setDuracion(int duracion) {
			this.duracion = duracion;
		}

		public String getDirector() {
			return director;
		}

		public void setDirector(String director) {
			this.director = director;
		}

		public String getSinopsis() {
			return sinopsis;
		}

		public void setSinopsis(String sinopsis) {
			this.sinopsis = sinopsis;
		}
		
}
