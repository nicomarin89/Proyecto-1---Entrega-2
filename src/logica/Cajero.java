package logica;

public class Cajero {

	public String nombre;
	public Usuario usuario;
	
	public Cajero(String nombre, Usuario usuario) {
		this.nombre = nombre;
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
