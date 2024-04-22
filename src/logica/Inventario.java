package logica;

import java.util.ArrayList;

public class Inventario {

	public ArrayList<Pieza> listaBodega;
	public ArrayList<Pieza> listaExhibida;
	public ArrayList<Pieza> historial;
	
	public Inventario() {
		this.listaBodega = new ArrayList<Pieza>();
		this.listaExhibida = new ArrayList<Pieza>();
		this.historial = new ArrayList<Pieza>();
	}
	public void addBodega(Pieza pieza) {
		listaBodega.add(pieza);
	}
	public void addExhibicion(Pieza pieza) {
		listaExhibida.add(pieza);
	}
	public void addHistorial(Pieza pieza) {
		historial.add(pieza);
	}
	public ArrayList<Pieza> getListaBodega() {
		return listaBodega;
	}
	public ArrayList<Pieza> getListaExhibida() {
		return listaExhibida;
	}
	public ArrayList<Pieza> getHistorial() {
		return historial;
	}
	public Pieza getPiezaPorTitulo(ArrayList<Pieza> lista, String titulo) {
		for (Pieza pieza : lista) {
            if (pieza.getTitulo().equals(titulo)) {
                return pieza;
            }
		}	
	return null;
	}
}
