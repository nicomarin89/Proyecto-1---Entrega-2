package logica;

public class Recibo {

	public Pieza pieza;
	public double valorCompra;
	public String metdoPago;
	public Comprador comprador;
	public Cajero cajero;
	

	public Recibo(Pieza pieza, double valorCompra, String metdoPago, Comprador comprador, Cajero cajero) {
		this.pieza = pieza;
		this.valorCompra = valorCompra;
		this.metdoPago = metdoPago;
		this.comprador = comprador;
		this.cajero = cajero;
	}
	
	public Pieza getPieza() {
		return pieza;
	}
	
}
