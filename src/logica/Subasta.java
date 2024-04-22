package logica;

import java.util.ArrayList;

public class Subasta {
	public ArrayList<PiezaSubasta> listaPiezas;
	public ArrayList<Comprador> compradores;
	public Operador operador;
	
	public Subasta(Operador operador) {
		this.listaPiezas = new ArrayList<PiezaSubasta>();
		this.compradores = new ArrayList<Comprador>();
		this.operador = operador;
	}

	public void setCompradores(ArrayList<Comprador> compradores) {
		this.compradores = compradores;
	}

	public void setListaPiezas(ArrayList<PiezaSubasta> listaPiezas) {
		this.listaPiezas = listaPiezas;
	}

	public ArrayList<PiezaSubasta> getListaPiezas() {
		return listaPiezas;
	}

	public ArrayList<Comprador> getCompradores() {
		return compradores;
	}



}
