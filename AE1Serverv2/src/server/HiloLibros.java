package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class HiloLibros implements Runnable {

	private Thread hilo;
	private static int numCliente = 0;
	private Socket socketAlCliente;

	//Se crea el hilo desde el server
	public HiloLibros(Socket socketAlCliente) {
		numCliente++;
		hilo = new Thread(this, "Cliente_" + numCliente);
		this.socketAlCliente = socketAlCliente;
		hilo.start();
	}

	@Override
	public void run() {
		ArrayList<Libro> listaLibros = new ArrayList<Libro>();
		Libro libro1 = new Libro("El senor de los Anillos", "84", "Tolkien", "30");
		Libro libro2 = new Libro("El Hobbit", "89", "Tolkien", "8.99");
		Libro libro3 = new Libro("Los pilares de la tierra", "87", "Follet", "19.99");
		Libro libro4 = new Libro("Dune", "978", "Herbert", "10.99");
		Libro libro5 = new Libro("Don Quijote de La Mancha", "979", "Cervantes", "14.99");
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);

		// Comunicamos con el hilo
		System.out.println("Estableciendo comunicacion con " + hilo.getName());

		// Igualamos todas las conexiones de entrada, salida y modo de lectura a
		// nulo para que quede libre para almacenar los datos
		PrintStream salida = null;
		InputStreamReader entrada = null;
		BufferedReader entradaBuffer = null;

		try {
			// salida del server a cliente
			salida = new PrintStream(socketAlCliente.getOutputStream());

			// Entrada del servidor al cliente
			entrada = new InputStreamReader(socketAlCliente.getInputStream());

			// Aqui asignamos lo introducido en "entrada" a entradaBuffer
			entradaBuffer = new BufferedReader(entrada);

			String texto = "";
			boolean continuar = true;

			// Procesaremos entradas hasta que el mensaje del cliente sea 0
			while (continuar) {
				// Leemos lo introducido desde entrada a entradaBuffer y
				// lo almacenamos en la variable texto
				texto = entradaBuffer.readLine();

				// Dividimos el texto para idenficar que quiere hacer el usuario
				// y tambien tener la informacion por otro lado

				String[] codigos = texto.split("-");
				

				int opcion = Integer.parseInt(codigos[0]);

				if (codigos[0].equals("0")) {
					// Mandamos la senal de "0" para que el cliente sepa que
					// vamos a cortar la comunicacion
					salida.println("OK");
					System.out.println("HILO SERVIDOR: " + hilo.getName() + " ha cerrado la comunicacion");

					// Alteramos el bool para cerrar el loop del hilo
					continuar = false;
				} else {
					
					String valorCons = codigos[1];
					
					System.out.println("HILO SERVIDOR: Se ha realizado una operacion en el servidor por el usuario "
							+ hilo.getName());
					String nombreSol = "";

					// Menu con estructura switch
					switch (opcion) {
					// Busqueda por ISBN
					case 1:
						for (int i = 0; i < listaLibros.size(); i++) {
							if (listaLibros.get(i).getIsbn().equals(valorCons)) {
								nombreSol = listaLibros.get(i).toString();
							}
						}
						break;

					// Busqueda por nombre del libro
					case 2:
						for (int i = 0; i < listaLibros.size(); i++) {
							if (listaLibros.get(i).getNombre().equalsIgnoreCase(valorCons)) {
								nombreSol = listaLibros.get(i).toString();
							}
						}
						break;

					// Busqueda por nombre de autor (hay varios)
					case 3:
						for (int i = 0; i < listaLibros.size(); i++) {
							if (listaLibros.get(i).getAutor().equalsIgnoreCase(valorCons)) {
								nombreSol += (listaLibros.get(i).toString());
							}
						}
						break;

					case 4:
						// Anadir libro
						Libro aux;
						String nombre = codigos[2];
						String isbn = codigos[3];
						String autor = codigos[4];
						String precio = codigos[5];
						aux = new Libro(nombre, isbn, autor, precio);
						listaLibros.add(aux);
						nombreSol = listaLibros.get(listaLibros.size() - 1).toString();
						break;

					default:
						break;
					}
					// Le mandamos la respuesta al cliente
					salida.println(nombreSol);
				}
			}
			// Cerramos el socket
			socketAlCliente.close();

			// Notese que si no cerramos el socket ni en el servidor ni en el
			// cliente, mantendremos
			// la comunicacion abierta
		} catch (IOException e) {
			System.err.println("HiloLibros: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("HiloLibros: Error");
			e.printStackTrace();
		}
	}
}