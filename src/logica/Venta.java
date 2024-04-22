package logica;

public class Venta {
	
	public boolean disponibleVenta;
	public boolean bloqueado;
	public double precio;
	
	public Venta(boolean disponibleVenta, double precio) {
		this.disponibleVenta = disponibleVenta;
		this.bloqueado = false;
		this.precio = precio;
	}

	public void setDisponibleVenta(boolean disponibleVenta) {
		this.disponibleVenta = disponibleVenta;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public double getPrecio() {
		return precio;
	}
	
}
