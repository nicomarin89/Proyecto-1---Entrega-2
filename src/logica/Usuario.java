package logica;

public class Usuario {
	
private String usuario;
private String contrasena;

public Usuario(String usuario, String contrasena) {
    this.usuario = usuario;
    this.contrasena = contrasena;
}

public String getUsuario() {
    return usuario;
}

public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
}

public boolean validarContrasena(String contrasena) {
    return this.contrasena.equals(contrasena);
}
}
