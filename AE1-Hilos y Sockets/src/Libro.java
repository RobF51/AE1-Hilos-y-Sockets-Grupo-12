
public class Libro implements Runnable {
	
	private String nombre;
	private String isbn;
	private String autor;
	private String precio;
	
	
	public Libro(String nombre, String isbn, String autor, String precio){
		super();
		this.nombre = nombre;
		this.isbn = isbn;
		this.autor = autor;
		this.precio = precio;
		
		
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}



	public String getNombre() {
		return nombre;
	}



	public String getIsbn() {
		return isbn;
	}



	public String getAutor() {
		return autor;
	}



	public String getPrecio() {
		return precio;
	}

}




	