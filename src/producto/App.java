package producto;

import java.util.LinkedList;
import java.util.List;

import archivos.ArchivoAtracciones;
import archivos.ArchivoUsuarios;
import archivos.ArchivosPromociones;
import usuario.Usuario;



public class App {

	private List<Usuario> listaUsuarios;
	private List<Atraccion> listaAtracciones;
	private List<Promocion> listaPromociones;
	private List<Producto> listaProductos;
	
	public App () {
		this.listaUsuarios = new LinkedList<>();
		this.listaAtracciones = new LinkedList<>();
		this.listaPromociones = new LinkedList<>();
		this.listaProductos = new LinkedList<>();
	}
	
	public static void main(String[] args) {
		ArchivoUsuarios archivoUsuarios = new ArchivoUsuarios();
		ArchivoAtracciones archivoAtracciones = new ArchivoAtracciones();
		ArchivosPromociones archivoPromociones = new ArchivosPromociones();
		
		App parque = new App();
		
		parque.listaUsuarios = archivoUsuarios.leerArchivoUsuarios();
		
		for (Usuario usuario : parque.listaUsuarios) {
			System.out.println(usuario);
		}
		
		System.out.println("##################################################################################");
		
		parque.listaAtracciones = archivoAtracciones.leerArchivoAtracciones();
		
		for (Atraccion atraccion : parque.listaAtracciones) {
			System.out.println(atraccion);
		}
		
		System.out.println("##################################################################################");
		
		parque.listaPromociones = archivoPromociones.leerArchivoPromociones(parque.listaAtracciones);
		
		for (Promocion promocion : parque.listaPromociones) {
			System.out.println(promocion);
		}
		
		System.out.println("##################################################################################");
		
	}
	
}
