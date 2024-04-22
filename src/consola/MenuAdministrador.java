package consola;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import logica.Comprador;
import logica.Consignacion;
import logica.Dimenciones;
import logica.DuracionTiempo;
import logica.Escultura;
import logica.Fotografia;
import logica.Galeria;
import logica.Impresion;
import logica.Inventario;
import logica.Autor;
import logica.Operador;
import logica.Pieza;
import logica.PiezaSubasta;
import logica.Pintura;
import logica.PropietarioPieza;
import logica.Subasta;
import logica.Venta;
import logica.Verificar;
import logica.Video;

public class MenuAdministrador {

	public void mostrarMenu(Scanner scanner, String nombreCarpeta, Galeria galeria) throws IOException {
		String rutaArchivo = nombreCarpeta + "/piezas";
		boolean parar = false;
		while(!parar) {
			System.out.println("MENU ADMINISTRADOR");
			System.out.println("1. Gestionar Piezas (venta o devolucion por consignacion)");
			System.out.println("2. Verificar compradores");
			System.out.println("3. Crear subastas");
			System.out.println("4. Salir menu Administrador");
			System.out.println("Por favor ingrese el numero de la accion que desea:");
			String respuesta = scanner.nextLine();
			if (respuesta.equals("1")) {
				boolean parar1 = false;
				while(!parar1) {
					System.out.println("MENU PIEZAS");
					System.out.println("1. Crear nueva Pieza");
					System.out.println("2. Ingresar Pieza por 'consignacion'");
					System.out.println("3. Verificar compras de Piezas");
					System.out.println("4. Atras");
					System.out.println("Por favor ingrese el numero de la accion que desea:");
					String respuesta1 = scanner.nextLine();
					if (respuesta1.equals("1")) {
						boolean parar1_1 = false;
						String tipo = "VIDEO";
						while(!parar1_1) {
						System.out.println("Porfavor eliga un tipo de Pieza");
						System.out.println("1. VIDEO");
						System.out.println("2. ESCULTURA");
						System.out.println("3. PINTURA");
						System.out.println("4. FOTOGRAFIA");
						System.out.println("5. IMPRESION");
						System.out.println("Por favor ingrese el numero del tipo de Pieza:");
						String respTipo = scanner.nextLine();
						if(respTipo.equals("1")) {
							tipo = "VIDEO";
							parar1_1 = true;
						}else if(respTipo.equals("2")) {
							tipo = "ESCULTURA";
							parar1_1 = true;
						}else if(respTipo.equals("3")) {
							tipo = "PINTURA";
							parar1_1 = true;
						}else if(respTipo.equals("4")) {
							tipo = "FOTOGRAFIA";
							parar1_1 = true;
						}else if(respTipo.equals("5")) {
							tipo = "IMPRESION";
							parar1_1 = true;
						}else {
							System.out.println("Ingrese una opcion correcta");
						}
						}boolean parar1_2 = false;
						String lugarInven = null;
						while(!parar1_2) {
						System.out.println("Porfavor eliga donde quiere poner la Pieza");
						System.out.println("1. EXHIBICION");
						System.out.println("2. BODEGA");
						System.out.println("3. HISTORIAL");
						System.out.println("Por favor ingrese el lugar de la Pieza:");
						String respLugar = scanner.nextLine();
						if(respLugar.equals("1")) {
							lugarInven = "EXHIBICION";
							parar1_2 = true;
						}else if(respLugar.equals("2")) {
							lugarInven = "BODEGA";
							parar1_2 = true;
						}else if(respLugar.equals("3")) {
							lugarInven = "HISTORIAL";
							parar1_2 = true;
						}else {
							System.out.println("Ingrese una opcion correcta");
						}
						}
						System.out.println("Por favor ingrese el titulo de la Pieza:");
						String titulo = scanner.nextLine();
						System.out.println("Por favor ingrese el anio de creacion de la Pieza:");
						String anio = scanner.nextLine();
						System.out.println("Por favor ingrese el lugar de creacion de la Pieza:");
						String lugar = scanner.nextLine();
						System.out.println("Por favor ingrese el nombre del autor de la Pieza:");
						String autor = scanner.nextLine();
						boolean parar1_3 = false;
						String anonimo = "false";
						while(!parar1_3) {
							System.out.println("El autor quiere ser anonimo?");
							System.out.println("1. Si");
							System.out.println("2. No");
							String respAnonimo = scanner.nextLine();
							if(respAnonimo.equals("1")) {
								anonimo = "true";
								parar1_3 = true;
							}else if(respAnonimo.equals("2")) {
								parar1_3 = true;
							}
							else {
								System.out.println("Ingrese una opcion correcta");
							}
						}
						System.out.println("Por favor ingrese el precio de la Pieza:");
						String precio = scanner.nextLine();
						ArrayList<String> cualidadesPieza = new ArrayList<String>();
						cualidadesPieza.addAll(List.of(tipo, lugarInven, titulo, anio, lugar, autor, anonimo, "true", precio));
						ArrayList<String> cualidadesDeTipo = cualidadesSegunTipo(tipo, scanner, false);
						cualidadesDeTipo.addAll(0, cualidadesPieza);
						Pieza pieza = null;
						try {
							ArrayList<PropietarioPieza> propietarios = galeria.getPropietarios();
							PropietarioPieza propietario = null;
							boolean encontrado = false;
							int i = 0;
							while (i < propietarios.size() && !encontrado) {
								if(propietarios.get(i).getNombre().equals(autor)) {
									propietario = propietarios.get(i);
									encontrado = true;
								}
								i++;
							
							}if(tipo.equals("VIDEO")) {
								Autor autor_ = null;
								if(anonimo.equals("true")) {
									autor_ = new Autor(true, propietario);
								}else {
									autor_ = new Autor(false, propietario);
								}
								double precioDouble = Double.parseDouble(precio);
								Venta venta = new Venta(true, precioDouble);
								double segDouble = Double.parseDouble(cualidadesDeTipo.get(9));
								double minDouble = Double.parseDouble(cualidadesDeTipo.get(10));
								double horasDouble = Double.parseDouble(cualidadesDeTipo.get(11));
								DuracionTiempo duracion =  new DuracionTiempo(segDouble, minDouble, horasDouble) ;
								pieza = new Video(titulo, anio, lugar, autor_, venta, null, duracion, cualidadesDeTipo.get(12), cualidadesDeTipo.get(13));
							}else if (tipo.equals("ESCULTURA")) {
								Autor autor_ = null;
								if(anonimo.equals("true")) {
									autor_ = new Autor(true, propietario);
								}else {
									autor_ = new Autor(false, propietario);
								}
								double precioDouble = Double.parseDouble(precio);
								Venta venta = new Venta(true, precioDouble);
								double anchoDouble = Double.parseDouble(cualidadesDeTipo.get(11));
								double antoDouble = Double.parseDouble(cualidadesDeTipo.get(12));
								double profunDouble = Double.parseDouble(cualidadesDeTipo.get(13));
								Dimenciones dimenciones =  new Dimenciones(anchoDouble, antoDouble, profunDouble) ;
								boolean elecBoolean = true;
								if (cualidadesDeTipo.get(14).equals("false")) {
									elecBoolean = false;
								}
								double pesoDouble = Double.parseDouble(cualidadesDeTipo.get(15));
								pieza = new Escultura(titulo, anio, lugar, autor_, venta, null, cualidadesDeTipo.get(9), cualidadesDeTipo.get(10), dimenciones, elecBoolean, pesoDouble, cualidadesDeTipo.get(16));
								
							}else if (tipo.equals("PINTURA")) {
								Autor autor_ = null;
								if(anonimo.equals("true")) {
									autor_ = new Autor(true, propietario);
								}else {
									autor_ = new Autor(false, propietario);
								}
								double precioDouble = Double.parseDouble(precio);
								Venta venta = new Venta(true, precioDouble);
								double anchoDouble = Double.parseDouble(cualidadesDeTipo.get(9));
								double antoDouble = Double.parseDouble(cualidadesDeTipo.get(10));
								Dimenciones dimenciones =  new Dimenciones(anchoDouble, antoDouble, 0);
								pieza = new Pintura(titulo, anio, lugar, autor_, venta, null, dimenciones, cualidadesDeTipo.get(11), cualidadesDeTipo.get(12));
								
							}else if (tipo.equals("FOTOGRAFIA")) {
								Autor autor_ = null;
								if(anonimo.equals("true")) {
									autor_ = new Autor(true, propietario);
								}else {
									autor_ = new Autor(false, propietario);
								}
								double precioDouble = Double.parseDouble(precio);
								Venta venta = new Venta(true, precioDouble);
								pieza = new Fotografia(titulo, anio, lugar, autor_, venta, null, cualidadesDeTipo.get(9), cualidadesDeTipo.get(10));
								
							}else{
								Autor autor_ = null;
								if(anonimo.equals("true")) {
									autor_ = new Autor(true, propietario);
								}else {
									autor_ = new Autor(false, propietario);
								}
								double precioDouble = Double.parseDouble(precio);
								Venta venta = new Venta(true, precioDouble);
								double anchoDouble = Double.parseDouble(cualidadesDeTipo.get(9));
								double antoDouble = Double.parseDouble(cualidadesDeTipo.get(10));
								Dimenciones dimenciones =  new Dimenciones(anchoDouble, antoDouble, 0);
								pieza = new Impresion(titulo, anio, lugar, autor_, venta, null, dimenciones, cualidadesDeTipo.get(11), cualidadesDeTipo.get(12));
							}
							if (lugarInven.equals("EXHIBICION")) {
								Inventario inventario = galeria.getInventario();
								inventario.addExhibicion(pieza);
							}else if (lugarInven.equals("BODEGA")) {
								Inventario inventario = galeria.getInventario();
								inventario.addBodega(pieza);
							}else {
								Inventario inventario = galeria.getInventario();
								inventario.addHistorial(pieza);
							}
				            BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true));
				            writer.newLine();
				            for (int i1 = 0; i1 < cualidadesDeTipo.size(); i1++) {
				                writer.write(cualidadesDeTipo.get(i1));
				                if (i1 < cualidadesDeTipo.size() - 1) {
				                    writer.write(", ");
				                }
				            }
				            writer.close();
				            System.out.println("Pieza creada y agregada correctamente");
					}catch (IOException e) {
			            e.printStackTrace();
					}
					}else if(respuesta1.equals("2")){
						boolean parar1_11 = false;
						String tipo = "VIDEO";
						while(!parar1_11) {
						System.out.println("Porfavor eliga un tipo de Pieza");
						System.out.println("1. VIDEO");
						System.out.println("2. ESCULTURA");
						System.out.println("3. PINTURA");
						System.out.println("4. FOTOGRAFIA");
						System.out.println("5. IMPRESION");
						System.out.println("Por favor ingrese el numero del tipo de Pieza:");
						String respTipo = scanner.nextLine();
						if(respTipo.equals("1")) {
							tipo = "VIDEO";
							parar1_11 = true;
						}else if(respTipo.equals("2")) {
							tipo = "ESCULTURA";
							parar1_11 = true;
						}else if(respTipo.equals("3")) {
							tipo = "PINTURA";
							parar1_11 = true;
						}else if(respTipo.equals("4")) {
							tipo = "FOTOGRAFIA";
							parar1_11 = true;
						}else if(respTipo.equals("5")) {
							tipo = "IMPRESION";
							parar1_11 = true;
						}else {
							System.out.println("Ingrese una opcion correcta");
						}
						}boolean parar1_12 = false;
						String lugarInven = null;
						while(!parar1_12) {
						System.out.println("Porfavor eliga donde quiere poner la Pieza");
						System.out.println("1. EXHIBICION");
						System.out.println("2. BODEGA");
						System.out.println("3. HISTORIAL");
						System.out.println("Por favor ingrese el lugar de la Pieza:");
						String respLugar = scanner.nextLine();
						if(respLugar.equals("1")) {
							lugarInven = "EXHIBICION";
							parar1_12 = true;
						}else if(respLugar.equals("2")) {
							lugarInven = "BODEGA";
							parar1_12 = true;
						}else if(respLugar.equals("3")) {
							lugarInven = "HISTORIAL";
							parar1_12 = true;
						}else {
							System.out.println("Ingrese una opcion correcta");
						}
						}
						System.out.println("Por favor ingrese el titulo de la Pieza:");
						String titulo = scanner.nextLine();
						System.out.println("Por favor ingrese el anio de creacion de la Pieza:");
						String anio = scanner.nextLine();
						System.out.println("Por favor ingrese el lugar de creacion de la Pieza:");
						String lugar = scanner.nextLine();
						System.out.println("Por favor ingrese el nombre del autor de la Pieza:");
						String autor = scanner.nextLine();
						boolean parar1_13 = false;
						String anonimo = "false";
						while(!parar1_13) {
							System.out.println("El autor quiere ser anonimo?");
							System.out.println("1. Si");
							System.out.println("2. No");
							String respAnonimo = scanner.nextLine();
							if(respAnonimo.equals("1")) {
								anonimo = "true";
								parar1_13 = true;
							}else if(respAnonimo.equals("2")) {
								parar1_13 = true;
							}
							else {
								System.out.println("Ingrese una opcion correcta");
							}
						}
						System.out.println("Por favor ingrese el precio de la Pieza:");
						String precio = scanner.nextLine();
						ArrayList<String> cualidadesPieza = new ArrayList<String>();
						cualidadesPieza.addAll(List.of(tipo, lugarInven, titulo, anio, lugar, autor, anonimo, "true", precio));
						ArrayList<String> cualidadesDeTipo = cualidadesSegunTipo(tipo, scanner, true);
						cualidadesDeTipo.addAll(0, cualidadesPieza);
						Pieza pieza = null;
						try {
							ArrayList<PropietarioPieza> propietarios = galeria.getPropietarios();
							PropietarioPieza propietario = null;
							boolean encontrado = false;
							int i = 0;
							while (i < propietarios.size() && !encontrado) {
								if(propietarios.get(i).getNombre().equals(autor)) {
									propietario = propietarios.get(i);
									encontrado = true;
								}
								i++;
							
							}if(tipo.equals("VIDEO")) {
								Autor autor_ = null;
								if(anonimo.equals("true")) {
									autor_ = new Autor(true, propietario);
								}else {
									autor_ = new Autor(false, propietario);
								}
								double precioDouble = Double.parseDouble(precio);
								Venta venta = new Venta(true, precioDouble);
								double segDouble = Double.parseDouble(cualidadesDeTipo.get(9));
								double minDouble = Double.parseDouble(cualidadesDeTipo.get(10));
								double horasDouble = Double.parseDouble(cualidadesDeTipo.get(11));
								DuracionTiempo duracion =  new DuracionTiempo(segDouble, minDouble, horasDouble);
								Consignacion consig = new Consignacion(cualidadesDeTipo.get(15), cualidadesDeTipo.get(16));
								pieza = new Video(titulo, anio, lugar, autor_, venta, consig, duracion, cualidadesDeTipo.get(12), cualidadesDeTipo.get(13));
							}else if (tipo.equals("ESCULTURA")) {
								Autor autor_ = null;
								if(anonimo.equals("true")) {
									autor_ = new Autor(true, propietario);
								}else {
									autor_ = new Autor(false, propietario);
								}
								double precioDouble = Double.parseDouble(precio);
								Venta venta = new Venta(true, precioDouble);
								double anchoDouble = Double.parseDouble(cualidadesDeTipo.get(11));
								double antoDouble = Double.parseDouble(cualidadesDeTipo.get(12));
								double profunDouble = Double.parseDouble(cualidadesDeTipo.get(13));
								Dimenciones dimenciones =  new Dimenciones(anchoDouble, antoDouble, profunDouble) ;
								boolean elecBoolean = true;
								if (cualidadesDeTipo.get(14).equals("false")) {
									elecBoolean = false;
								}
								double pesoDouble = Double.parseDouble(cualidadesDeTipo.get(15));
								Consignacion consig = new Consignacion(cualidadesDeTipo.get(18), cualidadesDeTipo.get(19));
								pieza = new Escultura(titulo, anio, lugar, autor_, venta, consig, cualidadesDeTipo.get(9), cualidadesDeTipo.get(10), dimenciones, elecBoolean, pesoDouble, cualidadesDeTipo.get(16));
								
							}else if (tipo.equals("PINTURA")) {
								Autor autor_ = null;
								if(anonimo.equals("true")) {
									autor_ = new Autor(true, propietario);
								}else {
									autor_ = new Autor(false, propietario);
								}
								double precioDouble = Double.parseDouble(precio);
								Venta venta = new Venta(true, precioDouble);
								double anchoDouble = Double.parseDouble(cualidadesDeTipo.get(9));
								double antoDouble = Double.parseDouble(cualidadesDeTipo.get(10));
								Dimenciones dimenciones =  new Dimenciones(anchoDouble, antoDouble, 0);
								Consignacion consig = new Consignacion(cualidadesDeTipo.get(14), cualidadesDeTipo.get(15));
								pieza = new Pintura(titulo, anio, lugar, autor_, venta, consig, dimenciones, cualidadesDeTipo.get(11), cualidadesDeTipo.get(12));
								
							}else if (tipo.equals("FOTOGRAFIA")) {
								Autor autor_ = null;
								if(anonimo.equals("true")) {
									autor_ = new Autor(true, propietario);
								}else {
									autor_ = new Autor(false, propietario);
								}
								double precioDouble = Double.parseDouble(precio);
								Venta venta = new Venta(true, precioDouble);
								Consignacion consig = new Consignacion(cualidadesDeTipo.get(12), cualidadesDeTipo.get(13));
								pieza = new Fotografia(titulo, anio, lugar, autor_, venta, consig, cualidadesDeTipo.get(9), cualidadesDeTipo.get(10));
								
							}else{
								Autor autor_ = null;
								if(anonimo.equals("true")) {
									autor_ = new Autor(true, propietario);
								}else {
									autor_ = new Autor(false, propietario);
								}
								double precioDouble = Double.parseDouble(precio);
								Venta venta = new Venta(true, precioDouble);
								double anchoDouble = Double.parseDouble(cualidadesDeTipo.get(9));
								double antoDouble = Double.parseDouble(cualidadesDeTipo.get(10));
								Dimenciones dimenciones =  new Dimenciones(anchoDouble, antoDouble, 0);
								Consignacion consig = new Consignacion(cualidadesDeTipo.get(14), cualidadesDeTipo.get(15));
								pieza = new Impresion(titulo, anio, lugar, autor_, venta, consig, dimenciones, cualidadesDeTipo.get(11), cualidadesDeTipo.get(12));
							}
							if (lugarInven.equals("EXHIBICION")) {
								Inventario inventario = galeria.getInventario();
								inventario.addExhibicion(pieza);
							}else if (lugarInven.equals("BODEGA")) {
								Inventario inventario = galeria.getInventario();
								inventario.addBodega(pieza);
							}else {
								Inventario inventario = galeria.getInventario();
								inventario.addHistorial(pieza);
							}
				            BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true));
				            writer.newLine();
				            for (int i1 = 0; i1 < cualidadesDeTipo.size(); i1++) {
				                writer.write(cualidadesDeTipo.get(i1));
				                if (i1 < cualidadesDeTipo.size() - 1) {
				                    writer.write(", ");
				                }
				            }
				            writer.close();
				            System.out.println("Pieza creada y agregada correctamente");

				        } catch (IOException e) {
				        	System.out.println("No se pudo crear la Pieza");
				            e.printStackTrace();
				        }
					}else if(respuesta1.equals("3")){
						ArrayList<Pieza> pendientes = galeria.getComprasPendientes();
						int opcion;
						do {
				            if (pendientes.size() == 0) {
				                System.out.println("No hay compras de piezas por revisar.");
				                break;
				            } else {
				                System.out.println("Piezas por revisar:");
				                for (int i = 0; i < pendientes.size(); i++) {
				                    System.out.println((i + 1) + ". Revisar " + pendientes.get(i).getTitulo());
				                }
				                System.out.println((pendientes.size() + 1) + ". Volver al menú anterior");

				                System.out.println("Ingrese el número de la opción que desea revisar:");
				                opcion = scanner.nextInt();

				                if (opcion >= 1 && opcion <= pendientes.size()) {
				                    Pieza piezaRevizar = pendientes.get(opcion - 1);
				                    revisarPieza(piezaRevizar, pendientes, galeria, scanner);
				                } else if (opcion == pendientes.size() + 1) {
				                    System.out.println("Volviendo al menú anterior...");
				                    break;
				                } else {
				                    System.out.println("Opción inválida. Por favor, seleccione un número válido.");
				                }
				            }
				        } while (true);
					}else if (respuesta1.equals("4")) {
						parar1 = true;
					}else {
						System.out.println("Por favor ingrese una opcion valida");
					}
				}
			}else if (respuesta.equals("2")) {
				boolean parar2 = false;
				while(!parar2) {
					System.out.println("MENU COMPRADORES");
					System.out.println("1. Verificar compradores nuevos");
					System.out.println("2. Verificar solicitudes de ampliacion de gasto maximo");
					System.out.println("3. Atras");
					System.out.println("Por favor ingrese el numero de la accion que desea:");
					String resp = scanner.nextLine();
					if (resp.equals("1")) {
						ArrayList<Verificar> listaPorVerificar = galeria.getCompradoresPorVerificar();
						int contador = 1;
						ArrayList<Integer> indices = new ArrayList<>();
				        for (Verificar comprador : listaPorVerificar) {
				        	String razon = comprador.getRazon();
				            if (razon.equals("NUEVO")) {
				                System.out.println(contador + ". " + comprador.getComprador().getNombre());
				                indices.add(listaPorVerificar.indexOf(comprador));
				                contador++;
				            }
				        }
				        System.out.println("Por favor ingrese el numero del comprador a verificar:");
				        int seleccion = scanner.nextInt();
				        Verificar comprador = listaPorVerificar.get(seleccion - 1);
				        System.out.println("Quieres aprovar al nuevo comprador " + comprador.getComprador().getNombre() + "?");
				        System.out.println("1. Si");
						System.out.println("2. No");
						String resp1 = scanner.nextLine();
						if(resp1.equals("1")) {
							listaPorVerificar.remove(comprador);
							comprador.getComprador().setVerificado(true);
							galeria.getCasaSubasta().addVerificados(comprador.getComprador());
							System.out.println("Comprador verificado");
						}else if (resp1.equals("2")) {
							listaPorVerificar.remove(comprador);
						}
					}else if (resp.equals("2")) {
						ArrayList<Verificar> listaPorVerificar = galeria.getCompradoresPorVerificar();
						int contador = 1;
						ArrayList<Integer> indices = new ArrayList<>();
				        for (Verificar comprador : listaPorVerificar) {
				        	String razon = comprador.getRazon();
				            if (razon.equals("VALOR_MAXIMO")) {
				                System.out.println(contador + ". " + comprador.getComprador().getNombre());
				                indices.add(listaPorVerificar.indexOf(comprador));
				                contador++;
				            }
				        }
				        System.out.println("Por favor ingrese el numero del comprador a verificar:");
				        int seleccion = scanner.nextInt();
				        Verificar comprador = listaPorVerificar.get(seleccion - 1);
				        System.out.println("Quieres aprovar el ampliamento del valor maximo del comprador " + comprador.getComprador().getNombre() + "?");
				        System.out.println("1. Si");
						System.out.println("2. No");
						String resp1 = scanner.nextLine();
						if(resp1.equals("1")) {
							listaPorVerificar.remove(comprador);
							//en vez de verificarlo apliarle al valor maximo
							comprador.getComprador().setVerificado(true);
							galeria.getCasaSubasta().addVerificados(comprador.getComprador());
							System.out.println("Comprador verificado");
						}else if (resp1.equals("2")) {
							listaPorVerificar.remove(comprador);
						}
					}else if(resp.equals("3")){
						parar2 = true;
					}
				}
				
			}else if (respuesta.equals("3")) {
				int opcion;
				ArrayList<PiezaSubasta> selecciones = new ArrayList<>();
				ArrayList<Pieza> bodega = galeria.getInventario().getListaBodega();
		        do {
		            System.out.println("Menu:");
		            for (int i = 0; i < bodega.size(); i++) {
		                System.out.println((i + 1) + ". " + bodega.get(i).getTitulo() + " de " + bodega.get(i).getAutor().getAutor().getNombre());
		            }
		            System.out.println("Seleccione una opcion (0 para salir):");
		            opcion = scanner.nextInt();
		            if (opcion >= 1 && opcion <= bodega.size()) {
		                Pieza seleccion = bodega.get(opcion - 1);
		                PiezaSubasta piezaSub = crearPiezaSub(scanner, seleccion);
		                if (!selecciones.contains(piezaSub)) {
		                    selecciones.add(piezaSub);
		                    System.out.println("Seleccion agregada: " + seleccion.getTitulo());
		                } else {
		                    System.out.println("¡Ya seleccionaste esta Pieza!");
		                }
		            } else if (opcion != 0) {
		                System.out.println("Opción invalida. Por favor, seleccione una opcion valida.");
		            }
		        } while (opcion != 0);
		        System.out.println("Selecciones del usuario:");
		        for (PiezaSubasta seleccion : selecciones) {
		            System.out.println("- " + seleccion.getPieza().getTitulo());
		        }
		        Operador seleccion = null;
		        ArrayList<Operador> operadores = galeria.getCasaSubasta().getOperadores();
		        do {
		            System.out.println("Menú:");
		            for (int i = 0; i < operadores.size(); i++) {
		                System.out.println((i + 1) + ". " + operadores.get(i).getNombre());
		            }
		            System.out.println("Seleccione una opción (0 para salir):");
		            opcion = scanner.nextInt();
		            if (opcion >= 1 && opcion <= operadores.size()) {
		                seleccion = operadores.get(opcion - 1);
		            } else if (opcion != 0) {
		                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
		            }
		        } while (seleccion == null && opcion != 0);
		        if (seleccion != null) {
		            System.out.println("Selección del usuario: " + seleccion);
		        } else {
		            System.out.println("No se seleccionó ningún objeto.");
		        }
		        int opcion1;
				ArrayList<Comprador> compradores = galeria.getCasaSubasta().getVerificados();
				ArrayList<Comprador> selecionados = new ArrayList<Comprador>();
		        do {
		            System.out.println("Menu:");
		            for (int i = 0; i < compradores.size(); i++) {
		                System.out.println((i + 1) + ". Comprador," + compradores.get(i).getNombre());
		            }
		            System.out.println("Seleccione una opcion (0 para salir):");
		            opcion1 = scanner.nextInt();
		            if (opcion1 >= 1 && opcion1 <= compradores.size()) {
		                Comprador comprador = compradores.get(opcion1 - 1);
		                if (!selecionados.contains(comprador)) {
		                	selecionados.add(comprador);
		                    System.out.println("Comprador agregado: " + comprador.getNombre());
		                } else {
		                    System.out.println("¡Ya seleccionaste este comprador!");
		                }
		            } else if (opcion1 != 0) {
		                System.out.println("Opción invalida. Por favor, seleccione una opcion valida.");
		            }
		        } while (opcion1 != 0);
		        System.out.println("Selecciones del usuario:");
		        for (Comprador comprador_ : selecionados) {
		            System.out.println("- " + comprador_.getNombre());
		        }
		        Subasta subasta = new Subasta(seleccion);
		        subasta.setListaPiezas(selecciones);
		        subasta.setCompradores(selecionados);
		        galeria.getCasaSubasta().setSubastaActual(subasta);
		        System.out.println("Subasta creada correctamente");
				
			}else if (respuesta.equals("4")) {
				parar = true;
			}else {
				System.out.println("Por favor ingrese una opcion valida");
			}
			
		}
	}


	private PiezaSubasta crearPiezaSub(Scanner scanner, Pieza seleccion) {
		System.out.println("El precio de esta Pieza es" + seleccion.getVenta().getPrecio());
		System.out.println("Escriba el precio minimo para la subasta:");
		double min = scanner.nextDouble();
		System.out.println("Escriba el precio inicial para la subasta:");
		double ini = scanner.nextDouble();
		PiezaSubasta piezaSub = new PiezaSubasta(seleccion, min, ini);
		return piezaSub;
	}


	private void revisarPieza(Pieza piezaRevizar, ArrayList<Pieza> pendientes, Galeria galeria, Scanner scanner) {
		boolean parar = false;
		while(!parar) {
			System.out.println("Quieres confirmar la venta de la Pieza" + piezaRevizar.getTitulo() + " de " + piezaRevizar.getAutor().getAutor().getNombre());
			System.out.println("1. Si");
			System.out.println("2. No");
			System.out.println("3. Atras");
			String resp = scanner.nextLine();
			if(resp.equals("1")) {
				pendientes.remove(piezaRevizar);
				ArrayList<Pieza> bodega = galeria.getInventario().getListaBodega();
				ArrayList<Pieza> exhibicion = galeria.getInventario().getListaExhibida();
				if(bodega.contains(piezaRevizar)) {
					bodega.remove(piezaRevizar);
					galeria.getInventario().addHistorial(piezaRevizar);
				}else {
					exhibicion.remove(piezaRevizar);
					galeria.getInventario().addHistorial(piezaRevizar);
				}
				parar = true;
			}else if(resp.equals("2")) {
				piezaRevizar.getVenta().setBloqueado(false);
				pendientes.remove(piezaRevizar);
				parar = true;
			}else if(resp.equals("3")) {
				parar = true;
			}else {
				System.out.println("Escriba una opcion valida");
			}
		}
		
	}


	private ArrayList<String> cualidadesSegunTipo(String tipo, Scanner scanner, boolean consig) {
		ArrayList<String> cualidades = new ArrayList<String>();
		if (tipo.equals("VIDEO")) {
			System.out.println("Por favor escriba cuanto dura el Video en el siguiente formato ('segundos,minutos,horas')");
			System.out.println("Ejemplo: 45,50,1");
			String tiempo = scanner.nextLine();
			String[] datos = tiempo.split(",");
			String seg = datos[0];
			String min = datos[1];
			String horas = datos[2];
			System.out.println("Escriba el tema del Video:");
			String tema = scanner.nextLine();
			System.out.println("Escriba la resolucion del Video:");
			System.out.println("Ejemplo: 1080p");
			String resolucion = scanner.nextLine();
			if(consig) {
				System.out.println("Escriba la fecha de llegada de la Pieza en el formato DD/MM/AAAA");
				System.out.println("Ejemplo: 02/03/2024");
				String fechaLlegada = scanner.nextLine();
				System.out.println("Escriba la fecha de salida de la Pieza en el formato DD/MM/AAAA");
				System.out.println("Ejemplo: 05/04/2024");
				String fechaSalida = scanner.nextLine();
				cualidades.addAll(List.of(seg, min, horas, tema, resolucion, "true", fechaLlegada, fechaSalida));
			}else {
			cualidades.addAll(List.of(seg, min, horas, tema, resolucion, "false"));
			}
		}else if (tipo.equals("ESCULTURA")) {
			System.out.println("Por favor escriba el material de la Escultura");
			String material = scanner.nextLine();
			System.out.println("Escriba el estilo");
			String estilo = scanner.nextLine();
			System.out.println("Por favor escriba de cuanto son las dimenciones de la Escultura (ancho,alto,profundidad)");
			System.out.println("Ejemplo: 20,25,32");
			String dimenciones = scanner.nextLine();
			String[] datos = dimenciones.split(",");
			String ancho = datos[0];
			String alto = datos[1];
			String profun = datos[2];
			boolean parar = false;
			String nesecitaElec = "false";
			while(!parar) {
				System.out.println("Nesecita electricidad para funcionar?");
				System.out.println("1. Si");
				System.out.println("2. No");
				String respElec = scanner.nextLine();
				if(respElec.equals("1")) {
					nesecitaElec = "true";
					parar = true;
				}else if(respElec.equals("2")) {
					parar = true;
				}
				else {
					System.out.println("Ingrese una opcion correcta");
				}
			}
			System.out.println("Por favor escriba el peso de la Escultura");
			String peso = scanner.nextLine();
			System.out.println("Por favor escriba detalles de isntalacion y precauciones de la Escultura");
			String detalles = scanner.nextLine();
			if(consig) {
				System.out.println("Escriba la fecha de llegada de la Pieza en el formato DD/MM/AAAA");
				System.out.println("Ejemplo: 02/03/2024");
				String fechaLlegada = scanner.nextLine();
				System.out.println("Escriba la fecha de salida de la Pieza en el formato DD/MM/AAAA");
				System.out.println("Ejemplo: 05/04/2024");
				String fechaSalida = scanner.nextLine();
				cualidades.addAll(List.of(material, estilo, ancho, alto, profun, nesecitaElec, peso, detalles, "true", fechaLlegada, fechaSalida));
			}else {
			cualidades.addAll(List.of(material, estilo, ancho, alto, profun, nesecitaElec, peso, detalles, "false"));
			}
		}else if (tipo.equals("PINTURA")) {
			System.out.println("Escriba la tecnica del la Pintura");
			String tecnica = scanner.nextLine();
			System.out.println("Escriba el estilo");
			String estilo = scanner.nextLine();
			System.out.println("Por favor escriba de cuanto son las dimenciones de la Pintura (ancho,alto)");
			System.out.println("Ejemplo: 30,35");
			String dimenciones = scanner.nextLine();
			String[] datos = dimenciones.split(",");
			String ancho = datos[0];
			String alto = datos[1];
			if(consig) {
				System.out.println("Escriba la fecha de llegada de la Pieza en el formato DD/MM/AAAA");
				System.out.println("Ejemplo: 02/03/2024");
				String fechaLlegada = scanner.nextLine();
				System.out.println("Escriba la fecha de salida de la Pieza en el formato DD/MM/AAAA");
				System.out.println("Ejemplo: 05/04/2024");
				String fechaSalida = scanner.nextLine();
				cualidades.addAll(List.of(ancho, alto, tecnica, estilo, "true", fechaLlegada, fechaSalida));
			}else {
			cualidades.addAll(List.of(ancho, alto, tecnica, estilo, "false"));
			}
		}else if (tipo.equals("FOTOGRAFIA")) {
			System.out.println("Escriba el tema de la Fotografia");
			String tema = scanner.nextLine();
			System.out.println("Escriba la resolucion de la Fotografia:");
			System.out.println("Ejemplo: 1920x1080");
			String resolucion = scanner.nextLine();
			if(consig) {
				System.out.println("Escriba la fecha de llegada de la Pieza en el formato DD/MM/AAAA");
				System.out.println("Ejemplo: 02/03/2024");
				String fechaLlegada = scanner.nextLine();
				System.out.println("Escriba la fecha de salida de la Pieza en el formato DD/MM/AAAA");
				System.out.println("Ejemplo: 05/04/2024");
				String fechaSalida = scanner.nextLine();
				cualidades.addAll(List.of(tema, resolucion, "true", fechaLlegada, fechaSalida));
			}else {
			cualidades.addAll(List.of(tema, resolucion, "false"));
			}
		}else if (tipo.equals("IMPRESION")) {
			System.out.println("Escriba el tema de la Impresion");
			String tema = scanner.nextLine();
			System.out.println("Escriba la resolucion de la Impresion:");
			System.out.println("Ejemplo: 1920x1080");
			String resolucion = scanner.nextLine();
			System.out.println("Por favor escriba de cuanto son las dimenciones de la Impresion (ancho,alto):");
			System.out.println("Ejemplo: 45,55");
			String dimenciones = scanner.nextLine();
			String[] datos = dimenciones.split(",");
			String ancho = datos[0];
			String alto = datos[1];
			if(consig) {
				System.out.println("Escriba la fecha de llegada de la Pieza en el formato DD/MM/AAAA");
				System.out.println("Ejemplo: 02/03/2024");
				String fechaLlegada = scanner.nextLine();
				System.out.println("Escriba la fecha de salida de la Pieza en el formato DD/MM/AAAA");
				System.out.println("Ejemplo: 05/04/2024");
				String fechaSalida = scanner.nextLine();
				cualidades.addAll(List.of(ancho, alto, tema, resolucion, "true", fechaLlegada, fechaSalida));
			}else {
			cualidades.addAll(List.of(ancho, alto, tema, resolucion, "false"));
			}
		}else {
			return null;
		}
		return cualidades;
	}

}
