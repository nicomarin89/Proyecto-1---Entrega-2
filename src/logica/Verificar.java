package logica;

public class Verificar {

	public Comprador comprador;
	public String razon;
	
	public Verificar(Comprador comprador, String razon) {
		this.comprador = comprador;
		this.razon = razon;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public String getRazon() {
		return razon;
	}
	
}
