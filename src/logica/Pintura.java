package logica;

public class Pintura extends Pieza{

	public String tecnica;
	public String estilo;
	public Dimenciones dimenciones;
	
	public Pintura(String titulo, String anio, String lugar, Autor autor, Venta venta, Consignacion consigna,
			Dimenciones dimenciones, String tecnica, String estilo) {
		super(titulo, anio, lugar, autor, venta, consigna);
		this.dimenciones = dimenciones;
		this.tecnica = tecnica;
		this.estilo = estilo;
		
	}
	
}
