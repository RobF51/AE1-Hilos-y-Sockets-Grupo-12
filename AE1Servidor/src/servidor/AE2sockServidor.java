package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AE2sockServidor {
	
	public static final int PUERTO = 2015;

	static class Libro {
		
		private String nombre;
		private String isbn;
		private String autor;
		private String precio;
		
		
		public Libro(String nombre, String isbn, String autor, String precio) {
			super();
			this.nombre = nombre;
			this.isbn = isbn;
			this.autor = autor;
			this.precio = precio;
		}


		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public String getIsbn() {
			return isbn;
		}


		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}


		public String getAutor() {
			return autor;
		}


		public void setAutor(String autor) {
			this.autor = autor;
		}


		public String getPrecio() {
			return precio;
		}


		public void setPrecio(String precio) {
			this.precio = precio;
		}

	}

		
		public static void main(String[] args) throws InterruptedException {
			
			ArrayList<Libro> listaLibros = new ArrayList<>();
			
			listaLibros.add(new Libro("El señor de los Anillos", " 8445000667", "Tolkien", "30"));
			listaLibros.add(new Libro("La isla del tesoro", "8491050884", "Stevenson", "8.99"));
			listaLibros.add(new Libro("Los pilares de la tierra", "8466341781", "Follet", "19.99"));
			listaLibros.add(new Libro("Dune", "9788417347628", "Herbert", "10.99"));
			listaLibros.add(new Libro("Don Quijote de La Mancha", "9788497962179", "Cervantes", "14.99"));
			
			System.out.println("      APLICACIÓN DE SERVIDOR      ");
			System.out.println("----------------------------------");
			
			
			InputStreamReader entrada = null;
			
			PrintStream salida = null;
			
	        Socket socketAlCliente = null;
			
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			
	try (ServerSocket serverSocket = new ServerSocket()){			
				
				//Decimos al server socket que escuche peticiones desde el puerto
				//que hayamos establecido
				serverSocket.bind(direccion);
				
				//Vamos a llevar la cuenta del numero de peticiones que nos llegan
				int peticion = 0;
				
				//Estamos continuamente escuchando, es lo normal dentro del comportamiento
				//de un servidor, un programa que no para nunca
				
				while(true){		
					
					System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);
					
					//En este punto, se parara el programa, hasta que entre la peticion de 
					//un cliente, y sera en ese momento cuando se cree un objeto Socket
					socketAlCliente = serverSocket.accept();
					System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");
					
					entrada = new InputStreamReader(socketAlCliente.getInputStream());
					BufferedReader bf = new BufferedReader(entrada);
									
					//El servidor se quedaría aquí parado hasta que el cliente nos mande
					//informacion, es decir, cuando haga un salida.println(INFORMACION);				
					String stringRecibido = bf.readLine();//3-4
					
					//Hay que tener en cuenta que toda comunicacion entre cliente y servidor
					//esta en formato de cadena de texto
					System.out.println("SERVIDOR: Me ha llegado del cliente: " + stringRecibido);
					//Como sabemos que el cliente nos envia un 3-7, hacemos un split por "-"
					//para obtener la información.
					String[] consulta = stringRecibido.split("-");
					
					int iOpcion = Integer.parseInt(consulta[0]);
					
					while (iOpcion != 5) {
						
						switch (iOpcion) {
						
						case 1:
						
						
							
						}
					
					}
					//Hay que tener en cuenta que es posible que los servidores tarden en responder
					//Thread.sleep(15000);
					
					//Es en este momento cuando calculamos la suma
					int resultado = iNumero1 + iNumero2;//7 
					System.out.println("SERVIDOR: El calculo de los numeros es: " + resultado);
									
					//Mandamos el resultado al cliente
					salida = new PrintStream(socketAlCliente.getOutputStream());
					salida.println(resultado);	
					
					//Si hemos llegado hasta aqui, cerramos las conexiones
					socketAlCliente.close();
				}
			} catch (IOException e) {
				System.err.println("SERVIDOR: Error de entrada/salida");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("SERVIDOR: Error -> " + e);
				e.printStackTrace();
			}
		
	}

}
