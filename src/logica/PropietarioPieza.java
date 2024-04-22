package logica;

public class PropietarioPieza {
	
	public String nombre;
	public Usuario usuario;
	public String numeroContancto;
	
	public PropietarioPieza(String nombre, Usuario usuario, String numeroContancto) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.numeroContancto = numeroContancto;
}

	public String getNombre() {
		return nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
