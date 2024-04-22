package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import logica.Cajero;
import logica.Comprador;
import logica.Consignacion;
import logica.Dimenciones;
import logica.DuracionTiempo;
import logica.Escultura;
import logica.Fotografia;
import logica.Impresion;
import logica.Inventario;
import logica.Administrador;
import logica.Autor;
import logica.Operador;
import logica.Pieza;
import logica.Pintura;
import logica.PropietarioPieza;
import logica.Recibo;
import logica.Usuario;
import logica.Venta;
import logica.Video;

public class Persistencia {
	
    public Administrador cargarDatosAdmin(String nombreCarpeta) {
        String rutaArchivo = nombreCarpeta + "/administrador";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            if ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0].trim();
                String nombreUsuario = datos[1].trim();
                String contrasena = datos[2].trim();
                Usuario usuario = new Usuario(nombreUsuario, contrasena);
                return new Administrador(nombre, usuario);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return null; 
    }
    
    public ArrayList<Operador> cargarOperadores(String nombreCarpeta) {
        ArrayList<Operador> operadores = new ArrayList<>();
        String rutaArchivo = nombreCarpeta + "/operadores";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0].trim();
                String nombreUsuario = datos[1].trim();
                String contrasena = datos[2].trim();
                Usuario usuario = new Usuario(nombreUsuario, contrasena);
                operadores.add(new Operador(nombre, usuario));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
        return operadores;
    }

	public ArrayList<Cajero> cargarCajeros(String nombreCarpeta) {
		ArrayList<Cajero> cajeros = new ArrayList<>();
        String rutaArchivo = nombreCarpeta + "/cajeros";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0].trim();
                String nombreUsuario = datos[1].trim();
                String contrasena = datos[2].trim();
                Usuario usuario = new Usuario(nombreUsuario, contrasena);
                cajeros.add(new Cajero(nombre, usuario));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
        return cajeros;
    }

	public ArrayList<PropietarioPieza> cargarPropietarios(String nombreCarpeta) {
		ArrayList<PropietarioPieza> propietarios = new ArrayList<>();
        String rutaArchivo = nombreCarpeta + "/propietarios";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0].trim();
                String nombreUsuario = datos[1].trim();
                String contrasena = datos[2].trim();
                String numContacto = datos[3].trim();
                Usuario usuario = new Usuario(nombreUsuario, contrasena);
                propietarios.add(new PropietarioPieza(nombre, usuario, numContacto));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
        return propietarios;
    }

	public ArrayList<Comprador> cargarCompradores(String nombreCarpeta) {
		ArrayList<Comprador> compradores = new ArrayList<>();
        String rutaArchivo = nombreCarpeta + "/compradores";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0].trim();
                String nombreUsuario = datos[1].trim();
                String contrasena = datos[2].trim();
                String numContacto = datos[3].trim();               
                String verificadoStr = datos[4].trim();
                boolean verificado = false;
                if (verificadoStr.equals("true")) {
                	verificado = true;
                }
                String valorMax = datos[5].trim();
                double valorMaxDouble = Double.parseDouble(valorMax);
                Usuario usuario = new Usuario(nombreUsuario, contrasena);
                compradores.add(new Comprador(nombre, usuario, numContacto, verificado, valorMaxDouble));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
        return compradores;
    }

	public Inventario cargarPiezas(String nombreCarpeta, ArrayList<PropietarioPieza> PropietariosPiezas) {
		Inventario inventario = new Inventario();
        String rutaArchivo = nombreCarpeta + "/piezas";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String tipo = datos[0].trim();
                if (tipo.equals("ESCULTURA")) {
                String sitioInventario = datos[1].trim();
                String titulo = datos[2].trim();
                String anio = datos[3].trim();
                String lugar = datos[4].trim();
                String nomAutor = datos[5].trim();
                String anonimoStr = datos[6].trim();
                String disponibleVentaStr = datos[7].trim();
                String precio = datos[8].trim();
                String material = datos[9].trim();
                String estilo = datos[10].trim();
                String ancho = datos[11].trim();
                String alto = datos[12].trim();
                String profundidad = datos[13].trim();
                String nesecitaElecStr = datos[14].trim();
                String peso = datos[15].trim();
                String detalles = datos[16].trim();
                String consignacionStr = datos[17].trim();
                PropietarioPieza propietario = null;
                boolean encontrado = false;
                int i = 0;
                while (i < PropietariosPiezas.size() && !encontrado) {
                	if(PropietariosPiezas.get(i).getNombre().equals(nomAutor)) {
                		propietario = PropietariosPiezas.get(i);
                		encontrado = true;
                	}
                	i++;
                }
                Autor autor = null;
                if (anonimoStr.equals("false")) {
                	autor = new Autor(false, propietario );
                }else {
                	autor = new Autor(true, propietario );
                }
                Venta venta = null;
                double precioDouble = Double.parseDouble(precio);
                if (disponibleVentaStr.equals("true")) {
                	venta = new Venta(true, precioDouble);
                }else {
                	venta = new Venta(false, precioDouble);
                }
                Consignacion consigna = null;
                if (consignacionStr.equals("true")) {
                	String fechaLlegada = datos[18].trim();
                	String fechaSalida = datos[19].trim();
                	consigna = new Consignacion(fechaLlegada, fechaSalida);
                }
                double anchoDouble = Double.parseDouble(ancho);
                double altoDouble = Double.parseDouble(alto);
                double profundidadDouble = Double.parseDouble(profundidad);
                Dimenciones dimenciones = new Dimenciones(anchoDouble, altoDouble, profundidadDouble);
                boolean nesecitaElec = false;
                if(nesecitaElecStr.equals("true")) {
                	nesecitaElec = true;
                }
                double pesoDouble = Double.parseDouble(peso);
                Escultura escultura = new Escultura(titulo, anio, lugar, autor, venta, consigna, material, estilo, dimenciones, nesecitaElec, pesoDouble, detalles);
                if (sitioInventario.equals("EXHIBICION")) {
                	inventario.addExhibicion(escultura);
                }else if(sitioInventario.equals("BODEGA")) {
                	inventario.addBodega(escultura);
                }else {
                	inventario.addHistorial(escultura);
                }
                }else if(tipo.equals("VIDEO")) {
                	String sitioInventario = datos[1].trim();
                    String titulo = datos[2].trim();
                    String anio = datos[3].trim();
                    String lugar = datos[4].trim();
                    String nomAutor = datos[5].trim();
                    String anonimoStr = datos[6].trim();
                    String disponibleVentaStr = datos[7].trim();
                    String precio = datos[8].trim();
                    String seg = datos[9].trim();
                    String min = datos[10].trim();
                    String horas = datos[11].trim();
                    String tema = datos[12].trim();
                    String resolucion = datos[13].trim();
                    String consignacionStr = datos[14].trim();
                    PropietarioPieza propietario = null;
                    boolean encontrado = false;
                    int i = 0;
                    while (i < PropietariosPiezas.size() && !encontrado) {
                    	if(PropietariosPiezas.get(i).getNombre().equals(nomAutor)) {
                    		propietario = PropietariosPiezas.get(i);
                    		encontrado = true;
                    	}
                    	i++;
                    }
                    Autor autor = null;
                    if (anonimoStr.equals("false")) {
                    	autor = new Autor(false, propietario );
                    }else {
                    	autor = new Autor(true, propietario );
                    }
                    Venta venta = null;
                    double precioDouble = Double.parseDouble(precio);
                    if (disponibleVentaStr.equals("true")) {
                    	venta = new Venta(true, precioDouble);
                    }else {
                    	venta = new Venta(false, precioDouble);
                    }
                    Consignacion consigna = null;
                    if (consignacionStr.equals("true")) {
                    	String fechaLlegada = datos[15].trim();
                    	String fechaSalida = datos[16].trim();
                    	consigna = new Consignacion(fechaLlegada, fechaSalida);
                    }
                    double segDouble = Double.parseDouble(seg);
                    double minDouble = Double.parseDouble(min);
                    double horasDouble = Double.parseDouble(horas);
                    DuracionTiempo duracion = new DuracionTiempo(segDouble, minDouble, horasDouble);
                    Video video = new Video(titulo, anio, lugar, autor, venta, consigna, duracion, tema, resolucion);
                    if (sitioInventario.equals("EXHIBICION")) {
                    	inventario.addExhibicion(video);
                    }else if(sitioInventario.equals("BODEGA")) {
                    	inventario.addBodega(video);
                    }else {
                    	inventario.addHistorial(video);
                    }
                }else if (tipo.equals("PINTURA")){
                	String sitioInventario = datos[1].trim();
                    String titulo = datos[2].trim();
                    String anio = datos[3].trim();
                    String lugar = datos[4].trim();
                    String nomAutor = datos[5].trim();
                    String anonimoStr = datos[6].trim();
                    String disponibleVentaStr = datos[7].trim();
                    String precio = datos[8].trim();
                    String ancho = datos[9].trim();
                    String alto = datos[10].trim();
                    String tecnica = datos[11].trim();
                    String estilo = datos[12].trim();
                    String consignacionStr = datos[13].trim();
                    PropietarioPieza propietario = null;
                    boolean encontrado = false;
                    int i = 0;
                    while (i < PropietariosPiezas.size() && !encontrado) {
                    	if(PropietariosPiezas.get(i).getNombre().equals(nomAutor)) {
                    		propietario = PropietariosPiezas.get(i);
                    		encontrado = true;
                    	}
                    	i++;
                    }
                    Autor autor = null;
                    if (anonimoStr.equals("false")) {
                    	autor = new Autor(false, propietario );
                    }else {
                    	autor = new Autor(true, propietario );
                    }
                    Venta venta = null;
                    double precioDouble = Double.parseDouble(precio);
                    if (disponibleVentaStr.equals("true")) {
                    	venta = new Venta(true, precioDouble);
                    }else {
                    	venta = new Venta(false, precioDouble);
                    }
                    Consignacion consigna = null;
                    if (consignacionStr.equals("true")) {
                    	String fechaLlegada = datos[14].trim();
                    	String fechaSalida = datos[15].trim();
                    	consigna = new Consignacion(fechaLlegada, fechaSalida);
                    }
                    double anchoDouble = Double.parseDouble(ancho);
                    double altoDouble = Double.parseDouble(alto);
                    Dimenciones dimenciones = new Dimenciones(anchoDouble, altoDouble, 0);
                    Pintura pintura = new Pintura(titulo, anio, lugar, autor, venta, consigna, dimenciones, tecnica, estilo);
                    if (sitioInventario.equals("EXHIBICION")) {
                    	inventario.addExhibicion(pintura);
                    }else if(sitioInventario.equals("BODEGA")) {
                    	inventario.addBodega(pintura);
                    }else {
                    	inventario.addHistorial(pintura);
                    }
                }else if (tipo.equals("IMPRESION")){
                	String sitioInventario = datos[1].trim();
                    String titulo = datos[2].trim();
                    String anio = datos[3].trim();
                    String lugar = datos[4].trim();
                    String nomAutor = datos[5].trim();
                    String anonimoStr = datos[6].trim();
                    String disponibleVentaStr = datos[7].trim();
                    String precio = datos[8].trim();
                    String ancho = datos[9].trim();
                    String alto = datos[10].trim();
                    String tema = datos[11].trim();
                    String resolucion = datos[12].trim();
                    String consignacionStr = datos[13].trim();
                    PropietarioPieza propietario = null;
                    boolean encontrado = false;
                    int i = 0;
                    while (i < PropietariosPiezas.size() && !encontrado) {
                    	if(PropietariosPiezas.get(i).getNombre().equals(nomAutor)) {
                    		propietario = PropietariosPiezas.get(i);
                    		encontrado = true;
                    	}
                    	i++;
                    }
                    Autor autor = null;
                    if (anonimoStr.equals("false")) {
                    	autor = new Autor(false, propietario );
                    }else {
                    	autor = new Autor(true, propietario );
                    }
                    Venta venta = null;
                    double precioDouble = Double.parseDouble(precio);
                    if (disponibleVentaStr.equals("true")) {
                    	venta = new Venta(true, precioDouble);
                    }else {
                    	venta = new Venta(false, precioDouble);
                    }
                    Consignacion consigna = null;
                    if (consignacionStr.equals("true")) {
                    	String fechaLlegada = datos[14].trim();
                    	String fechaSalida = datos[15].trim();
                    	consigna = new Consignacion(fechaLlegada, fechaSalida);
                    }
                    double anchoDouble = Double.parseDouble(ancho);
                    double altoDouble = Double.parseDouble(alto);
                    Dimenciones dimenciones = new Dimenciones(anchoDouble, altoDouble, 0);
                    Impresion impresion = new Impresion(titulo, anio, lugar, autor, venta, consigna, dimenciones, tema, resolucion);
                    if (sitioInventario.equals("EXHIBICION")) {
                    	inventario.addExhibicion(impresion);
                    }else if(sitioInventario.equals("BODEGA")) {
                    	inventario.addBodega(impresion);
                    }else {
                    	inventario.addHistorial(impresion);
                    }
                }else if (tipo.equals("FOTOGRAFIA")){
                	String sitioInventario = datos[1].trim();
                    String titulo = datos[2].trim();
                    String anio = datos[3].trim();
                    String lugar = datos[4].trim();
                    String nomAutor = datos[5].trim();
                    String anonimoStr = datos[6].trim();
                    String disponibleVentaStr = datos[7].trim();
                    String precio = datos[8].trim();
                    String tema = datos[9].trim();
                    String resolucion = datos[10].trim();
                    String consignacionStr = datos[11].trim();
                    PropietarioPieza propietario = null;
                    boolean encontrado = false;
                    int i = 0;
                    while (i < PropietariosPiezas.size() && !encontrado) {
                    	if(PropietariosPiezas.get(i).getNombre().equals(nomAutor)) {
                    		propietario = PropietariosPiezas.get(i);
                    		encontrado = true;
                    	}
                    	i++;
                    }
                    Autor autor = null;
                    if (anonimoStr.equals("false")) {
                    	autor = new Autor(false, propietario );
                    }else {
                    	autor = new Autor(true, propietario );
                    }
                    Venta venta = null;
                    double precioDouble = Double.parseDouble(precio);
                    if (disponibleVentaStr.equals("true")) {
                    	venta = new Venta(true, precioDouble);
                    }else {
                    	venta = new Venta(false, precioDouble);
                    }
                    Consignacion consigna = null;
                    if (consignacionStr.equals("true")) {
                    	String fechaLlegada = datos[12].trim();
                    	String fechaSalida = datos[13].trim();
                    	consigna = new Consignacion(fechaLlegada, fechaSalida);
                    }
                    Fotografia fotografia = new Fotografia(titulo, anio, lugar, autor, venta, consigna, tema, resolucion);
                    if (sitioInventario.equals("EXHIBICION")) {
                    	inventario.addExhibicion(fotografia);
                    }else if(sitioInventario.equals("BODEGA")) {
                    	inventario.addBodega(fotografia);
                    }else {
                    	inventario.addHistorial(fotografia);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
        return inventario;
    }

	public ArrayList<Comprador> cargarResivos(String nombreCarpeta, ArrayList<Comprador> compradores, Inventario inventario, ArrayList<Cajero> cajeros) {
		String rutaArchivo = nombreCarpeta + "/recibos";
		ArrayList<Pieza> historial = inventario.getHistorial();
		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String titulo = datos[0].trim();
                String valorCompraStr = datos[1].trim();
                String medioPago = datos[2].trim();
                String compradorNom = datos[3].trim();
                String cajeroNom = datos[4].trim();
                double valorCompra = Double.parseDouble(valorCompraStr);
                Pieza pieza = inventario.getPiezaPorTitulo(historial, titulo);
                Comprador comprador = getCompradorPorNombre(compradores, compradorNom);
                Cajero cajero = getCajeroPorNombre(cajeros, cajeroNom);
                Recibo recibo = new Recibo(pieza, valorCompra, medioPago, comprador, cajero);
                comprador.agregarRecibo(recibo);
            }return compradores;
            
            }catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
                return null;
            }
		
}
	private Comprador getCompradorPorNombre(ArrayList<Comprador> compradores, String compradorNom) {
		for (Comprador comprador : compradores) {
            if (comprador.getNombre().equals(compradorNom)) {
                return comprador;
            }
		}	
	return null;
	}
	private Cajero getCajeroPorNombre(ArrayList<Cajero> cajeros, String cajeroNom) {
		for (Cajero cajero : cajeros) {
            if (cajero.getNombre().equals(cajeroNom)) {
                return cajero;
            }
		}	
	return null;
	}
}
