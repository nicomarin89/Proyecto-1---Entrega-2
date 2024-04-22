package logica;

public abstract class Pieza {
	
    protected String titulo;
    protected String anioCreacion;
    protected String lugarCreacion;
    protected Autor autor;
    public Venta venta;
    public Consignacion consigna;
	
	public Pieza(String titulo, String anio, String lugar, Autor autor, Venta venta, Consignacion consigna) {
		this.titulo = titulo;
        this.anioCreacion = anio;
        this.lugarCreacion = lugar;
        this.autor = autor;
        this.venta = venta;
        this.consigna = consigna;
	}

	public String getTitulo() {
		return titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setConsigna(Consignacion consigna) {
		this.consigna = consigna;
	}
    
}
