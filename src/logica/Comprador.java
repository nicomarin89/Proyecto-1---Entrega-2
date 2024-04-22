package logica;

import java.util.ArrayList;

public class Comprador {

	public Usuario getUsuario() {
		return usuario;
	}
	public String nombre;
	public Usuario usuario;
	public String numContacto;
	public ArrayList<Recibo> historialCompra;
	public boolean verificado;
	public double valorMaximo;
	
	public Comprador(String nombre, Usuario usuario, String numContacto, 
			boolean verificado, double valorMaximo) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.numContacto = numContacto;
		this.historialCompra = new ArrayList<Recibo>();
		this.verificado = verificado;
		this.valorMaximo = valorMaximo;
	}
	public void agregarRecibo(Recibo recibo) {
		historialCompra.add(recibo);
    }
	public String getNombre() {
		return nombre;
	}
	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}
	public double getValorMaximo() {
		return valorMaximo;
	}
	public void setValorMaximo(double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
	
	
}
