package dam.ad.ej2.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaActividades implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ActividadParque> listaActividades;

	public ListaActividades() {
		listaActividades = new ArrayList<>();
	}

	public void addActividad(ActividadParque a) {
		listaActividades.add(a);
	}

	public List<ActividadParque> getListaActividades() {
		return listaActividades;
	}
}
