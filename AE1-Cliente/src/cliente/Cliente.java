/**
 * 
 */
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Roberto
 *
 */
public class Cliente {
	
	
	public static final int PUERTO = 2017;
	public static final String IP_SERVER = "localhost";
	
	
	public static void main(String[] args) {
		
		System.out.println("        APLICACIÓN CLIENTE         ");
		System.out.println("-----------------------------------");
		
		
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		
		try (Scanner sc = new Scanner(System.in);
				Socket socketAlServidor = new Socket()){
						
				//Pedimos los datos
				System.out.println("CLIENTE: Introduzca el ISBN");
				String isbn = sc.nextLine();//introducir isbn
				
				System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
				socketAlServidor.connect(direccionServidor);			
				System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER 
						+ " por el puerto " + PUERTO);	
				
				//se crea el objeto para mandar la info al servidor
				PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
				
				
				//se manda informacion por el stream
				salida.println(isbn);
				
				//este objeto nos permite leer la respuesta del rserver
				InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
		
				//leemos linea a linea
				BufferedReader bf = new BufferedReader(entrada);
				
				//mensaje para saber que esto funciona
				System.out.println("CLIENTE: Esperando al resultado del servidor...");
		
				//se para el hilo main hasta que se haga un println (el server responde)
				String titulo = bf.readLine();
				
				System.out.println("CLIENTE: El libro es: " + titulo);
				
				
				
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
		
				
				
	}
}


