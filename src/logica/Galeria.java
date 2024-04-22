package logica;

import java.util.ArrayList;

public class Galeria {

	public Administrador admin;
	public ArrayList<Cajero> cajeros;
	public ArrayList<PropietarioPieza> propietarios;
	public ArrayList<Comprador> compradores;
	public ArrayList<Pieza> comprasPendientes;
	public ArrayList<Verificar> compradoresPorVerificar;
	public CasaSubasta casaSubasta;
	public Inventario inventario;
	
	public Galeria(Administrador admin, ArrayList<Cajero> cajeros, ArrayList<PropietarioPieza> propietarios,
			ArrayList<Comprador> compradores, CasaSubasta casaSubasta, Inventario inventario) {
		this.admin = admin;
		this.cajeros = cajeros;
		this.propietarios = propietarios;
		this.compradores = compradores;
		this.comprasPendientes = new ArrayList<Pieza>();
		this.compradoresPorVerificar = new ArrayList<Verificar>();
		this.casaSubasta = casaSubasta;
		this.inventario = inventario;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public ArrayList<Cajero> getCajeros() {
		return cajeros;
	}
	
	public ArrayList<PropietarioPieza> getPropietarios() {
			return propietarios;
		}
	
	public ArrayList<Comprador> getCompradores() {
		return compradores;
	}

	public ArrayList<Pieza> getComprasPendientes() {
		return comprasPendientes;
	}

	public ArrayList<Verificar> getCompradoresPorVerificar() {
		return compradoresPorVerificar;
	}

	public CasaSubasta getCasaSubasta() {
		return casaSubasta;
	}

	public Inventario getInventario() {
		return inventario;
	}

	
	
	
}
