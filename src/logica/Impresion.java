package logica;

public class Impresion extends Pieza{

	public String tema;
	public String resolucion;
	public Dimenciones dimenciones;
	
	public Impresion(String titulo, String anio, String lugar, Autor autor, Venta venta, Consignacion consigna,
			Dimenciones dimenciones, String tema, String resolucion ) {
		super(titulo, anio, lugar, autor, venta, consigna);
		this.dimenciones = dimenciones;
		this.tema = tema;
		this.resolucion = resolucion;
		
	}

	
}
