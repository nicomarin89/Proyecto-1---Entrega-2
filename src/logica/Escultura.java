package logica;

public class Escultura extends Pieza {
	
	public String material;
	public String estilo;
	public Dimenciones dimenciones;
	public boolean nesecitaElec;
    private double peso;
    private String detalles;
    
	public Escultura(String titulo, String anio, String lugar, Autor autor, Venta venta, Consignacion consigna, String material, String estilo,
			Dimenciones dimenciones, boolean nesecitaElec, double peso, String detalles) {
		super(titulo, anio, lugar, autor, venta, consigna);
		this.material = material;
		this.estilo = estilo;
		this.dimenciones = dimenciones;
		this.nesecitaElec = nesecitaElec;
		this.peso = peso;
		this.detalles = detalles;
	}
    
	

    
}
