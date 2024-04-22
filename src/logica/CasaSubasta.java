package logica;

import java.util.ArrayList;

public class CasaSubasta {

	public ArrayList<Comprador> verificados;
	public ArrayList<Operador> operadores;
	public Subasta subastaActual;
	
	public CasaSubasta() {
		this.verificados = new ArrayList<Comprador>();
		this.operadores = new ArrayList<Operador>();
		this.subastaActual = null;
	}

	public ArrayList<Operador> getOperadores() {
		return operadores;
	}

	public void setOperadores(ArrayList<Operador> operadores) {
		this.operadores = operadores;
	}
	
	public void addVerificados(Comprador comprador) {
		verificados.add(comprador);
	}

	public ArrayList<Comprador> getVerificados() {
		return verificados;
	}

	public Subasta getSubastaActual() {
		return subastaActual;
	}

	public void setSubastaActual(Subasta subastaActual) {
		this.subastaActual = subastaActual;
	}
	
}
