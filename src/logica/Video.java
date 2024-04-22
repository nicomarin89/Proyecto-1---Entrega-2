package logica;

public class Video extends Pieza{

	public DuracionTiempo duracion;
	public String tema;
	public String resolucion;
	
	public Video(String titulo, String anio, String lugar, Autor autor, Venta venta, Consignacion consigna,
			DuracionTiempo duracion, String tema, String resolucion) {
		super(titulo, anio, lugar, autor, venta, consigna);
		this.duracion = duracion;
		this.tema = tema;
		this.resolucion = resolucion;
	}
	
	
}
