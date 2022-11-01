package server;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;




//Este hilo va a contar el numero de letras que tiene la frase que le manda el 
//cliente.

//La conexion se mantendra abierta hasta que el cliente mande la palabra
//"FIN" al servidor.

//Recibe el socket que abre el servidor con el cliente y con el que
//mantendra la conversacion

public class HiloLibros implements Runnable{
	
	
	
		private Thread hilo;
		private static int numCliente = 0;
		private Socket socketAlCliente;	
		
		
		//se crea el hilo desde el server
		public HiloLibros(Socket socketAlCliente) {
			numCliente++;
			hilo = new Thread(this, "Cliente_"+numCliente);
			this.socketAlCliente = socketAlCliente;
			hilo.start();
		}
		
		
		
	
		
		@Override
		public void run() {

			ArrayList<Libro> listaLibros = new ArrayList<Libro>();
			
			Libro libro1 = new Libro("El señor de los Anillos", "84", "Tolkien", "30");
			Libro libro2 = new Libro("El Hobbit", "89", "Tolkien", "8.99");
			Libro libro3 = new Libro("Los pilares de la tierra", "87", "Follet", "19.99");
			Libro libro4 = new Libro("Dune", "978", "Herbert", "10.99");
			Libro libro5 = new Libro("Don Quijote de La Mancha", "979", "Cervantes", "14.99");
			
			
			listaLibros.add(libro1);
			listaLibros.add(libro2);
			listaLibros.add(libro3);
			listaLibros.add(libro4);
			listaLibros.add(libro5);

			
			
			//comunicamos con el hilo
			System.out.println("Estableciendo comunicacion con " + hilo.getName());
			
			//igualamos todas las conexiones de entrada, salida y modo de lectura a nulo para que quede libre para almacenar los datos
			PrintStream salida = null;
			InputStreamReader entrada = null;
			BufferedReader entradaBuffer = null;
			
			try {
				//salida del server a cliente
				salida = new PrintStream(socketAlCliente.getOutputStream());
				//Entrada del servidor al cliente
				entrada = new InputStreamReader(socketAlCliente.getInputStream());
				//aqui asignamos lo introducido en "entrada" a entradaBuffer 
				entradaBuffer = new BufferedReader(entrada);
				
				String texto = "";
				boolean continuar = true;
				
				//Procesaremos entradas hasta que el texto del cliente sea FIN
				while (continuar) {
					//leemos lo introducido desde entrada a entradaBuffer y almacenamos en la variable texto
					texto = entradaBuffer.readLine();
					
					//dividimos el texto para idenficar que quiere hacer el usuario y tambien tener la informacion por otro lado
					
					String[] codigos = texto.split("-");
					String valorCons = codigos[1];
					
					//String opcion = codigos[0];
					int opcion = Integer.parseInt(codigos[0]);
					
					
					
					
					//trim() es un metodo que quita los espacios en blanco del principio
					//y del final
					if (codigos[0].equalsIgnoreCase("0")) {
						//Mandamos la señal de "0" para que el cliente sepa que vamos a cortar
						//la comunicacion
						salida.println("OK");
						System.out.println(hilo.getName() + " ha cerrado la comunicacion");
						continuar = false;
					} else {
						
					//-----------------------------------------------------------------------------	
						System.out.println("Se ha realizado una operacion en el servidor por el usuario "+ hilo.getName());
						String nombreSol = "";
						
						//menu con estructura switch
						
						
						
						switch (opcion) { 
							//Busqueda por ISBN
						    case 1:
							for (int i=0;i<listaLibros.size();i++) {
								
								
								if (listaLibros.get(i).getIsbn().equals(valorCons)) {
									nombreSol = listaLibros.get(i).toString();	
								}
								
							}
						    	
						     break;
						     //Busqueda por nombre del libro
						   case 2:
							   for (int i=0;i<listaLibros.size();i++) {
									
									
									if (listaLibros.get(i).getNombre().equalsIgnoreCase(valorCons)) {
										nombreSol = listaLibros.get(i).toString();	
									}
									
								}
						    break;
						    
						    //Busqueda por nombre de autor (hjay varios)
						   case 3:
							   
							  
							   
							   for (int i=0;i<listaLibros.size();i++) {
									
									
									if (listaLibros.get(i).getAutor().equalsIgnoreCase(valorCons)) {
										
									
										
										nombreSol += (listaLibros.get(i).toString());
								
									}
								}
						 
						    break;
						    
						   case 4:
							   
							   Libro aux;
								
							   String nombre = codigos[3];
							   String isbn = codigos[4];
							   String autor = codigos[5];
							   String precio = codigos [6];
							   
							  aux = new Libro (nombre, isbn, autor, precio);
							  
							   listaLibros.add(aux);
							   
						   break;
						    //default:
						     
						  }
						
						
						//Contamos las letras que tiene la frase que nos han mandado
						//int numeroLetras = texto.trim().length();
						//System.out.println(hilo.getName() + " dice: " + texto + " y tiene " 
								//+ numeroLetras + " letras");
						//Le mandamos la respuesta al cliente
						salida.println(nombreSol);
					}
					
				
					
					//-------------------------------------------------------------------------------
				
				}
				
				//Cerramos el socket
				socketAlCliente.close();
				//Notese que si no cerramos el socket ni en el servidor ni en el cliente, mantendremos
				//la comunicacion abierta
			} catch (IOException e) {
				System.err.println("HiloLibros: Error de entrada/salida");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("HiloLibros: Error");
				e.printStackTrace();
			}
		}
	}




