package consola;

import java.util.ArrayList;
import java.util.Scanner;

import logica.Comprador;
import logica.Galeria;
import logica.Operador;
import logica.Pieza;
import logica.PiezaSubasta;
import logica.Subasta;

public class MenuOperador {

	public void mostrarMenu(Scanner scanner, Operador operador, Galeria galeria) {
		{
			boolean parar = false;
			while(!parar) {
				System.out.println("MENU OPERADOR");
				System.out.println("1. Iniciar subasta");
				System.out.println("2. Salir menu Operador");
				System.out.println("Por favor ingrese el numero de la accion que desea:");
				String respuesta = scanner.nextLine();
				if (respuesta.equals("1")) {
					iniciarSubasta(scanner, galeria);
				}else if (respuesta.equals("2")){
					parar = true;
				}else {
					System.out.println("Opcion invalida. Por favor, seleccione una opcion valida.");
				}
			}
		}
	}

	private void iniciarSubasta(Scanner scanner, Galeria galeria) {
		Subasta subasta = galeria.getCasaSubasta().getSubastaActual();
		ArrayList<PiezaSubasta> piezas = subasta.getListaPiezas();
    if (piezas.isEmpty()) {
        System.out.println("No hay piezas disponibles para subastar.");
        return;
    }
    System.out.println("Lista de piezas disponibles para subastar:");
    for (int i = 0; i < piezas.size(); i++) {
        System.out.println((i + 1) + ". " + piezas.get(i).getPieza().getTitulo());
    }
    System.out.println("Seleccione el numero de la pieza para comenzar la subasta (0 para cancelar):");
    int opcionPieza = scanner.nextInt();

    if (opcionPieza >= 1 && opcionPieza <= piezas.size()) {
        PiezaSubasta piezaSeleccionada = piezas.get(opcionPieza - 1);
        System.out.println("Comenzando la subasta de la pieza: " + piezaSeleccionada.getPieza().getTitulo());
        System.out.println("Lista de compradores:");
        ArrayList<Comprador> compradores = subasta.getCompradores();
        for (int j = 0; j < compradores.size(); j++) {
            System.out.println((j + 1) + ". " + compradores.get(j).getNombre());
        }
        System.out.println("Seleccione los numeros de los compradores para invitar a la subasta (0 para iniciar la subasta):");
        ArrayList<Comprador> compradoresSeleccionados = new ArrayList<>();
        int opcionComprador;
        while ((opcionComprador = scanner.nextInt()) != 0) {
            if (opcionComprador >= 1 && opcionComprador <= compradores.size()) {
                Comprador compradorSeleccionado = compradores.get(opcionComprador - 1);
                compradoresSeleccionados.add(compradorSeleccionado);
                System.out.println("Comprador " + compradorSeleccionado.getNombre() + " seleccionado.");
            } else {
                System.out.println("Opcion invalida. Por favor, seleccione un numero de comprador valido.");
            }
        }
        if (!compradoresSeleccionados.isEmpty()) {
            System.out.println("Iniciando subasta...");
            iniciarSubastaParaPieza(piezaSeleccionada, compradoresSeleccionados, scanner, galeria);
        } else {
            System.out.println("Subasta cancelada.");
        }
    } else if (opcionPieza == 0) {
        System.out.println("Operacion cancelada.");
    } else {
        System.out.println("Opcion invalida. Por favor, seleccione un numero de pieza valido.");
    }
	}

	private void iniciarSubastaParaPieza(PiezaSubasta piezaSeleccionada, ArrayList<Comprador> compradoresSeleccionados, Scanner scanner, Galeria galeria) {
		double precioBase = piezaSeleccionada.getValorInicial();
	    double precioActual = precioBase;
	    Comprador mejorPostor = null;
	    System.out.println("Precio base de la pieza: " + precioBase);
	    while (true) {
	        System.out.println("Precio actual: " + precioActual);
	        if (mejorPostor != null) {
	            System.out.println("Mejor postor: " + mejorPostor.getNombre());
	        }
	        System.out.println("Seleccione una opcion:");
	        System.out.println("1. Seleccionar comprador para pujar");
	        System.out.println("2. Finalizar subasta");
	        int opcion = scanner.nextInt();
	        if (opcion == 1) {
	            System.out.println("Seleccione el numero del comprador para pujar:");
	            for (int i = 0; i < compradoresSeleccionados.size(); i++) {
	                System.out.println((i + 1) + ". " + compradoresSeleccionados.get(i).getNombre());
	            }
	            int opcionComprador = scanner.nextInt();
	            if (opcionComprador >= 1 && opcionComprador <= compradoresSeleccionados.size()) {
	                Comprador compradorSeleccionado = compradoresSeleccionados.get(opcionComprador - 1);
	                System.out.println("Ingrese el monto de la puja para " + compradorSeleccionado.getNombre() + ":");
	                double oferta = scanner.nextDouble();
	                if (oferta > precioActual) {
	                    precioActual = oferta;
	                    mejorPostor = compradorSeleccionado;
	                    System.out.println("Oferta aceptada.");
	                } else {
	                    System.out.println("La oferta debe ser mayor que el precio actual.");
	                }
	            } else {
	                System.out.println("Opcion invalida. Por favor, seleccione un numero de comprador valido.");
	            }
	        } else if (opcion == 2) {
	            System.out.println("Subasta finalizada.");
	            break;
	        } else {
	            System.out.println("Opcion invalida. Por favor, seleccione una opcion valida.");
	        }
	    }
	    if (mejorPostor.getValorMaximo() >= precioActual) {
	    	mejorPostor.setValorMaximo(mejorPostor.getValorMaximo() - precioActual);
	    	ArrayList<Pieza> exhibicion = galeria.getInventario().getListaExhibida();
	    	ArrayList<Pieza> bodega = galeria.getInventario().getListaBodega();
	    	if (exhibicion.contains(piezaSeleccionada.getPieza())) {
	    		exhibicion.remove(piezaSeleccionada.getPieza());
	    		galeria.getInventario().addHistorial(piezaSeleccionada.getPieza());
	    	}else {
	    		bodega.remove(piezaSeleccionada.getPieza());
	    		galeria.getInventario().addHistorial(piezaSeleccionada.getPieza());
	    	}
	    	
	    }else {
	    	galeria.getCompradoresPorVerificar();
	    }
	}

}
