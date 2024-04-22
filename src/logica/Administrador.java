package logica;

public class Administrador {

	public Usuario getUsuario() {
		return usuario;
	}

	public String nombre;
	public Usuario usuario;
	
	public Administrador(String nombre, Usuario usuario) {
		this.nombre = nombre;
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
