package logica;

public class PiezaSubasta {

	public Pieza pieza;
	public double valorMinimo;
	public double valorInicial;
	
	public PiezaSubasta(Pieza pieza, double valorMinimo, double valorInicial) {
		this.pieza = pieza;
		this.valorMinimo = valorMinimo;
		this.valorInicial = valorInicial;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public double getValorMinimo() {
		return valorMinimo;
	}

	public double getValorInicial() {
		return valorInicial;
	}
	
	
}
