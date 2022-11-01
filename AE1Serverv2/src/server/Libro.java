package server;

public class Libro {

	public String nombre;
	public String isbn;
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

	@Override
	public String toString() {
		return " Libro [nombre=" + nombre + ", isbn=" + isbn + ", autor=" + autor + ", precio=" + precio + "];";
	}

}