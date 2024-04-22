package logica;

public class Fotografia extends Pieza{

	public String tema;
	public String resolucion;

	public Fotografia(String titulo, String anio, String lugar, Autor autor, Venta venta, Consignacion consigna,
			String tema, String resolucion) {
		super(titulo, anio, lugar, autor, venta, consigna);
		this.tema = tema;
		this.resolucion = resolucion;
	}
	
}
