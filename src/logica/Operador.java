package logica;

public class Operador {

	public String nombre;
	public Usuario usuario;
	
	public Operador(String nombre, Usuario usuario) {
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
