import java.util.InputMismatchException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		 
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; 
 
        while (!salir) {
 

            System.out.println("1. Consultar libro por título");
            System.out.println("2. Consultar libro por ISBN");
            System.out.println("3. Consultar libro por autor");
            System.out.println("4. Salir");
 
            try {
 
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
 
                switch (opcion) {
                    case 1:
                        
                        break;
                    case 2:
                     
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
 
    }
 
	
}
