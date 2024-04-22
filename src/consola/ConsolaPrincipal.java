
package consola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import logica.Administrador;
import logica.Operador;
import logica.Pieza;
import logica.Cajero;
import logica.CasaSubasta;
import logica.Comprador;
import logica.Galeria;
import logica.PropietarioPieza;
import logica.Inventario;
import persistencia.Persistencia;

public class ConsolaPrincipal {
    public static void main(String[] args) {
        String nombreCarpeta = "data"; // Nombre de la carpeta donde estan los archivos

        Persistencia persistencia = new Persistencia();
        Administrador admin = persistencia.cargarDatosAdmin(nombreCarpeta);
        if (admin != null) {
        	System.out.println("Administrador " + admin.getNombre() +" cargado correctamente");
        } else {
            System.out.println("No se pudo cargar el administrador.");
        }
        ArrayList<Operador> operadores = persistencia.cargarOperadores(nombreCarpeta);
        if (operadores != null) {
        	System.out.println("Operadores cargados correctamente");
        } else {
            System.out.println("No se pudo cargar a los operadores.");
        }
        ArrayList<Cajero> cajeros = persistencia.cargarCajeros(nombreCarpeta);
        if (cajeros != null) {
        	System.out.println("Cajeros cargados correctamente");
        } else {
            System.out.println("No se pudo cargar a los cajeros.");
        }
        ArrayList<PropietarioPieza> propietariosPiezas = persistencia.cargarPropietarios(nombreCarpeta);
        if (propietariosPiezas != null) {
        	System.out.println("Porpietarios de piezas cargados correctamente");
        } else {
            System.out.println("No se pudo cargar a los propietarios de piezas.");
        }
        ArrayList<Comprador> compradores = persistencia.cargarCompradores(nombreCarpeta);
        if (compradores != null) {
        	System.out.println("Compradores de piezas cargados correctamente");
        } else {
            System.out.println("No se pudo cargar a los compradores.");
        }
        Inventario inventario = persistencia.cargarPiezas(nombreCarpeta, propietariosPiezas);
        if (inventario != null) {
        	System.out.println("Piezas cargadas correctamente");
        } else {
            System.out.println("No se pudo cargar las piezas.");
        }
        ArrayList<Comprador> compradoresConResivos = persistencia.cargarResivos(nombreCarpeta, compradores, inventario, cajeros);
        if (compradoresConResivos != null) {
        	System.out.println("Recibos cargados correctamente");
        } else {
            System.out.println("No se pudo cargar los Recibos.");
        }
        try {
            CasaSubasta casaSubasta = new CasaSubasta();
            casaSubasta.setOperadores(operadores);
            Galeria galeria = new Galeria(admin, cajeros, propietariosPiezas, compradoresConResivos, casaSubasta, inventario);
            System.out.println("Galeria creada correctamente");
            try {
            iniciarSesion(galeria, nombreCarpeta);	
            }catch(Exception e) {
                System.err.println("No se pudo iniciar secion correctamente");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            System.err.println("No se pudo crear la galeria correctamente.");
            System.out.println("Error: " + e.getMessage());
        }
        
        
        
    }

	private static void iniciarSesion(Galeria galeria, String nombreCarpeta) {
	    boolean usuarioEncontrado = false;
	    Scanner scanner = new Scanner(System.in);
	    do {
	    usuarioEncontrado = false;
	    System.out.println("Ingrese el nombre de usuario:");
	    String usuario = scanner.nextLine();
	    System.out.println("Ingrese la contrasena:");
	    String contraseña = scanner.nextLine();
	    
	    Administrador admin = galeria.getAdmin();
        if (admin.getUsuario().getUsuario().equals(usuario) && admin.getUsuario().validarContrasena(contraseña)) {
            System.out.println("Credenciales validas. Bienvenido, " + usuario + ".");
            usuarioEncontrado = true;
            MenuAdministrador menuAdmin = new MenuAdministrador();
            try {
				menuAdmin.mostrarMenu(scanner, nombreCarpeta, galeria);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	    if (!usuarioEncontrado) {
	    CasaSubasta casaSub = galeria.getCasaSubasta();
	    ArrayList<Operador> operadores = casaSub.getOperadores();
		    for (Operador operador : operadores) {
	            if (operador.getUsuario().getUsuario().equals(usuario) && operador.getUsuario().validarContrasena(contraseña)) {
	                System.out.println("Credenciales validas. Bienvenido, " + usuario + ".");
	                usuarioEncontrado = true;
	                MenuOperador menuOperador = new MenuOperador();
	                menuOperador.mostrarMenu(scanner, operador, galeria);
	            }
		    }
	    }
	    if (!usuarioEncontrado) {
		    ArrayList<Cajero> cajeros= galeria.getCajeros();
	            for (Cajero cajero : cajeros) {
	                if (cajero.getUsuario().getUsuario().equals(usuario) && cajero.getUsuario().validarContrasena(contraseña)) {
	                    System.out.println("Credenciales validas. Bienvenido, " + usuario + ".");
	                    usuarioEncontrado = true;
	                    MenuCajero menuCajero = new MenuCajero();
	                    menuCajero.mostrarMenu();
	                }
	            }
	        }
	    if (!usuarioEncontrado) {
	    ArrayList<Comprador> compradores= galeria.getCompradores();
            for (Comprador comprador : compradores) {
                if (comprador.getUsuario().getUsuario().equals(usuario) && comprador.getUsuario().validarContrasena(contraseña)) {
                    System.out.println("Credenciales validas. Bienvenido, " + usuario + ".");
                    usuarioEncontrado = true;
                    MenuComprador menuComprador = new MenuComprador();
                    menuComprador.mostrarMenu();
                }
            }
        }
	    if (!usuarioEncontrado) {
		    ArrayList<PropietarioPieza> propietarios= galeria.getPropietarios();
	            for (PropietarioPieza propietario : propietarios) {
	                if (propietario.getUsuario().getUsuario().equals(usuario) && propietario.getUsuario().validarContrasena(contraseña)) {
	                    System.out.println("Credenciales validas. Bienvenido, " + usuario + ".");
	                    usuarioEncontrado = true;
	                    MenuPropietario menuPropietario = new MenuPropietario();
	                    menuPropietario.mostrarMenu();
	                }
	            }
	        }
	    if (!usuarioEncontrado) {
            System.out.println("Credenciales invalidas. Por favor, intentelo de nuevo.");
        	}
	    }while(true);
	}
	
}
