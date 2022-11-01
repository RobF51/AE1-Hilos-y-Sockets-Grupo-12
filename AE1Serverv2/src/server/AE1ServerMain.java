package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

//En este caso, vamos a abrir un hilo por cada peticion que haga el servidor
//para así poder procesar varias peticiones simultaneas de diferentes clientes

public class AE1ServerMain {

	public static final int PUERTO = 2011;

	public static void main(String[] args) {
		System.out.println("      APLICACIÓN DE SERVIDOR CON HILOS     ");
		System.out.println("-------------------------------------------");

		int peticion = 0;

		// se crea socket y se conecta al puerto
		try (ServerSocket servidor = new ServerSocket()) {
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			// conexion
			servidor.bind(direccion);

			// confirmamos
			System.out.println(
					"SERVIDOR: Esperando peticion por el puerto " + PUERTO);

			while (true) {

				// Por cada peticion de cliente aceptada se me crea un objeto
				// socket diferente
				Socket socketAlCliente = servidor.accept();

				System.out.println("SERVIDOR: peticion numero " + ++peticion
						+ " recibida");
				// Abrimos un hilo nuevo y liberamos el hilo principal para que
				// pueda
				// recibir peticiones de otros clientes
				new HiloLibros(socketAlCliente);
			}
		} catch (IOException e) {
			System.err.println("SERVIDOR: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error");
			e.printStackTrace();
		}
	}
}
