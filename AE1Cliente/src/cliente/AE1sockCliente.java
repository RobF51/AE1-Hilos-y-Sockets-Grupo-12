package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AE1sockCliente {
	
	public static final int PUERTO = 2015;
	public static final String IP_SERVER = "localhost";

	public static void main(String[] args) {
		System.out.println("        APLICACIÓN CLIENTE         ");
		System.out.println("-----------------------------------");
		System.out.println("Introduzca una opción:");
		System.out.println("1. Consultar libro por ISBN");
		System.out.println("2. Consultar libro por título");
		System.out.println("3. Consultar libro por autor");
		System.out.println("4. Añadir un nuevo libro");
		System.out.println("5. Salir");

		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		
		try (Scanner sc = new Scanner(System.in);){

			boolean continuar = true;			
			
			do {		
				System.out.println("Introduzca la opción que desea y su correspondiente valor");
				String opcion = sc.nextLine();
				String valor = sc.nextLine();
				String consulta = opcion + "-" + valor;	
				
				Socket socketAlServidor = new Socket();
				socketAlServidor.connect(direccionServidor);
				System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
				System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER 
						+ " por el puerto " + PUERTO);	
				
				PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
				salida.println(consulta);

				System.out.println("CLIENTE: Esperando al resultado del servidor...");	
				InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
				BufferedReader bf = new BufferedReader(entrada);
				String resultado = bf.readLine();				
				System.out.println("CLIENTE: El resultado de la suma es: " + resultado);
				
				System.out.println("CLIENTE: Quiere continuar? S/N");
				String sContinuar = sc.nextLine();
				if (sContinuar.equalsIgnoreCase("n")) {
					continuar = false;
				}
				
				//Cerramos el socket para cerrar las conexiones.
				//Si cerramos un socket se cierran los inputStream y outputStream
				//que tenga asociados
				socketAlServidor.close();
			} while (continuar);			
		}catch (UnknownHostException e) {
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


