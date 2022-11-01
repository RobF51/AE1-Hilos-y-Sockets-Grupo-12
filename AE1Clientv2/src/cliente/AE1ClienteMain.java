package cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//En este ejemplo vamos a hacer que el cliente mande frases al servidor
	//y el servidor tendrá que contar el numero de letras que tiene la frase
	//La conexion se mantendra abierta hasta que el cliente mande la palabra
	//"FIN" al servidor.

	//En este caso se usara una misma conexion para todo el intercambio de mensajes
	//del cliente al servidor

public class AE1ClienteMain {
	
	

		
		// IP y Puerto a la que nos vamos a conectar
		public static final int PUERTO = 2011;
		public static final String IP_SERVER = "localhost";
		
		public static void main(String[] args) {
			System.out.println("        APLICACIÓN CLIENTE         ");
			System.out.println("-----------------------------------");

			InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
			
			try (Scanner sc = new Scanner(System.in)){
							
				System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
				
				//creamos nuevo socket
				
				Socket socketAlServidor = new Socket();
				
				//conectamos con el server desde el socket creado
				
				socketAlServidor.connect(direccionServidor);
				
			//confirmamos al usuario al conexion
				
				System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER + 
						" por el puerto " + PUERTO);
				
				
				//Establecemos el canal para enviar los datos
				InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
				//leemos en forma de "lineas" para optimizar
				BufferedReader entradaBuffer = new BufferedReader(entrada);
				//establecemos canal de salida
				PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
				
				
				
				
				//bool para llevar constancia de si el usuario quiere terminar
				
				boolean continuar = true;
				do {
					//inicializamos string que es la variable que almacenara lo que introduce el usuario
					
					String codeOpc = "";
					String codeFun = "";
					String codeSum = "";
					
					String codeNombre = "";
					String codeIsbn = "";
					String codeAutor="";
					String codePrecio = "";
					
					
					
					
					
					System.out.println("CLIENTE: \n 1. Consultar libro por ISBN \n "
							+ "2. Consultar libro por titulo \n "
							+ "3. Consultar libros por autor \n "
							+ "4. Anadir libro \n "
							+ "0. Salir (FIN para terminar): ");
					
					//se pide al usuario introducir datos y se asignan a la variable texto
					
					System.out.println("Introduzca la opcion");
					codeOpc = sc.nextLine();
					int codeOpcInt = Integer.parseInt(codeOpc);
					
			
					
					switch (codeOpcInt) {
						case 0:
							System.out.println("CLIENTE: Se va a cerrar la conexion");
							salida.println(codeOpcInt);
							break;
							
						case 1:
							System.out.println("CLIENTE: Introduzca el ISBN que quiere buscar (ej: 87)");
							codeIsbn = sc.nextLine();
							codeSum= codeOpc + "-" + codeIsbn;
							
						
							break;
							
						case 2:
							System.out.println("CLIENTE: Introduzca el Titulo que quiere buscar (ej: Dune)");
							codeNombre = sc.nextLine();
							codeSum= codeOpc + "-" + codeNombre;
							
							break;
							
						case 3:
							System.out.println("CLIENTE: Introduzca el Autor que quiere buscar (ej: Tolkien)");
							codeAutor = sc.nextLine();
							codeSum= codeOpc + "-" + codeAutor;
							
							break;
							
						case 4:
							System.out.println("CLIENTE: Introduzca el Titulo que quiere añadir (ej: Dune)");
							codeNombre = sc.nextLine();
							
							System.out.println("CLIENTE: Introduzca el Isbn que quiere añadir (ej: 87)");
							
							
							codeIsbn = sc.nextLine();
							
							System.out.println("CLIENTE: Introduzca el autor que quiere añadir (ej: Tolkien)");
							
					
							codeAutor = sc.nextLine();
							
							System.out.println("CLIENTE: Introduzca el precio que quiere añadir (ej: 45)");
							
					
							codePrecio = sc.nextLine();
							
							
							
							codeSum = codeOpc + "-" + codeFun + "-" + codeNombre + "-" + codeIsbn + "-" + codeAutor + "-" +codePrecio ;
						
							break;
							
							

						default:
							break;
					}
					
				
					//emitimos un print de "texto" a la varaible de salida "salida" y se manda al server
					
					salida.println(codeSum);
					
					//esperamos respuesta
					
					System.out.println("CLIENTE: Esperando respuesta ...... ");	
					
					//se inicializa la variable respuesta para almacenar la respuesta del servidor (lo que nos interesa)
					String respuesta = entradaBuffer.readLine();
					
					
					//if else. si la respuesta del server es "OK" (ocurre cuando se pone "fin" al ejecutarse el hilo)				
					if("OK".equalsIgnoreCase(respuesta)) {
						continuar = false;
					}else {
						//emitimos la respuesta almacenada en la variable "respuesta" a traves de system.out.println
						System.out.println("CLIENTE: " + respuesta);
					}				
				}while(continuar);
				
				
				
				//Cerramos la conexion
				socketAlServidor.close();
				
				System.out.println("CLIENTE: se ha cerrado la conexión");
				
				
			} catch (UnknownHostException e) {
				System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("CLIENTE: Error de entrada/salida");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("CLIENTE: Error -> " + e);
				e.printStackTrace();
			}
			
			System.out.println("CLIENTE: Fin del programa");
		}
		
	}



