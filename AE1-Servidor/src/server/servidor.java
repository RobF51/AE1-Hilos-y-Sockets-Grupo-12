package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {
	
public static final int PUERTO = 2017;
	
	public static void main(String[] args) throws InterruptedException {
		
	
		System.out.println("      APLICACIÓN DE SERVIDOR      ");
		System.out.println("----------------------------------");
		
		
		//el canal de entrada de datos (por donde el cliente nos manda la info)
		InputStreamReader entrada = null;
		
		
		//canal de salida de datos
		PrintStream salida = null;
		
		
		//esta clase nos permite comunicarnos con el cliente
		//lo crea el coket server cuando acepta la peticion
		
		Socket socketAlCliente = null;
		
		InetSocketAddress direccion = new InetSocketAddress(PUERTO);
		
		
		//-------------------------------
		
		try (ServerSocket serverSocket = new ServerSocket()){
			
			//decimos al server socket que escuche peticiones desde puerto
			
			serverSocket.bind(direccion);
			
			
			//cuenta de numeor de peticiones
			int peticion = 0;
			
			
			while(true){		
				
				System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);
				
				//se para el program ahasta que lelgue una peticion y se cree el
				//objeto socket
				socketAlCliente = serverSocket.accept();
				System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");
				
				
				entrada = new InputStreamReader(socketAlCliente.getInputStream());
				
				BufferedReader bf = new BufferedReader(entrada);
								
				//el servitor para hasta llegeu info, cuando haga un salida.println(info);				
				String stringRecibido = bf.readLine();//el ISBN
				
				//toda comunicacion entre cliente y servidor es en strings

				System.out.println("SERVIDOR: Me ha llegado del cliente: " + stringRecibido);
				
				String titulo="hola";
				
				
				System.out.println("SERVIDOR: El titulo del libro es: " + titulo);
								
				// el resultado al cliente
				salida = new PrintStream(socketAlCliente.getOutputStream());
				salida.println(titulo);	
				
				//cerramos la conexion
				socketAlCliente.close();
			}
			//el chorro de catchs
			} catch (IOException e) {
			System.err.println("SERVIDOR: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error -> " + e);
			e.printStackTrace();
		}
	}//FIN DEL PROGRAMA

			
			
			
	}	


