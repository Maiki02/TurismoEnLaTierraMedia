package parqueDeAtracciones;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import archivos.ArchivoAtracciones;
import archivos.ArchivoPromociones;
import archivos.ArchivoUsuarios;
import usuario.*;
import producto.*;

public class ParqueDeAtracciones {

	private List<Usuario> usuarios;
	private List<Producto> productos;

	public ParqueDeAtracciones() {
		this.usuarios = ArchivoUsuarios.leerArchivoUsuarios();
		List<Atraccion> atracciones= ArchivoAtracciones.leerArchivoAtracciones();
		List<Promocion> promociones= ArchivoPromociones.leerArchivoPromociones(atracciones);
		this.productos = crearListaDeProductos(atracciones, promociones);
	}

	private List<Producto> crearListaDeProductos(List<Atraccion> atracciones, List<Promocion> promociones) {
		productos = new LinkedList<Producto>();

		for (Producto promocion : promociones)
			productos.add(promocion);
		for (Producto atraccion : atracciones)
			productos.add(atraccion);

		return productos;
	}

	public void verProductos() {
		for (Producto producto : this.productos)
			System.out.println(producto);
	}
	
	public List<Producto> getProductos(){
		return this.productos;
	}
	

	private String preguntarSiQuiereAtraccion() {
		System.out.println("Ingrese 'S' o 'N' si lo desea o no comprar: ");
		String opcion=""; //Va a ser S o N
		@SuppressWarnings("resource")
		Scanner sc = new Scanner (System.in);
		opcion=sc.next();//S o N o Y
		opcion=opcion.toUpperCase();
		
		while(! (opcion.equals("S") || opcion.equals("N") )) {
			System.out.println("Ingrese un comando válido: 'S' o 'N': ");
			opcion=sc.next();
			opcion=opcion.toUpperCase();
		}
		
		return opcion;
		
	}
	
	
	private void ofrecerProductoAlUsuario(Usuario usuario, Producto producto) {
		String opcion="";
		
		if (usuario.puedeComprar(producto) && !producto.esProductoYaElecto(usuario) ) { 
			
			System.out.println(producto); //Mostramos el producto
			opcion=preguntarSiQuiereAtraccion();//Preguntamos si lo quiere
			
			
			if (opcion.equals("S")) {
				usuario.comprarProducto(producto);//Compra el producto
			}
		}
	}
	
	private void ofrecerProductosAlUsuario(Usuario usuario) {
		Collections.sort(productos, new OrdenarProductosPorPreferencia(usuario.getTipoFavorito()));
		for (Producto producto : this.productos) {
			ofrecerProductoAlUsuario(usuario, producto);
		}
		System.out.println("\n");
	}
	
	public void ofrecerProductosALosUsuarios() {
		System.out.println();
		for (Usuario usuario : this.usuarios) {
			System.out.println(usuario);
			ofrecerProductosAlUsuario(usuario);
		}
		
		//Es momento de escribirlos
		ArchivoUsuarios.escribirUsuarios(usuarios);
	}
}
