package logica;

public class Autor {
	
	public boolean anonimo;
	public PropietarioPieza autor;
	
	public Autor(boolean anonimo, PropietarioPieza autor) {
		this.anonimo = anonimo;
		this.autor = autor;
	}

	public PropietarioPieza getAutor() {
		return autor;
	}


}
