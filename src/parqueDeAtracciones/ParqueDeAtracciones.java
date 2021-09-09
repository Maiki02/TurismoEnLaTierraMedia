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

	private String preguntarSiQuiereAtraccion() {
		System.out.println("Ingrese 'S' o 'N' si lo desea o no comprar");
		String opcion=""; //Va a ser S o N
		@SuppressWarnings("resource")
		Scanner sc = new Scanner (System.in);
		opcion=sc.next();//S o N o Y
		opcion.toUpperCase();
		
		while(! (opcion.equals("S") || opcion.equals("N") )) {
			System.out.println("Ingrese un comando válido: 'S' o 'N'");
			opcion=sc.next();
		}
		
		System.out.println();
		return opcion;
		
	}
	
	
	private void ofrecerProductoAlUsuario(Usuario usuario, Producto producto) {
		String opcion="";
		
		if (usuario.puedeComprar(producto) && !usuario.esProductoYaElecto(producto)) { //Si puede comprar y la atraccion no fue electa
			System.out.println(producto); //Mostramos el producto
			System.out.println("");
			opcion=preguntarSiQuiereAtraccion();
			
			if (opcion.equals("S")) {
				usuario.comprarProducto(producto);//Compra el producto y hace mas operaciones
				
				//Una idea es que las atracciones electas se encargue el usuario.
				//No es lo mismo una atraccionElecta, que lasAtraccionesInvolucradas de la promocion,
				//que los productosCompradosDelUsuario
			}
		}
	}
	
	private void ofrecerProductosAlUsuario(Usuario usuario) {
		Collections.sort(productos, new OrdenarProductosPorPreferencia(usuario.getTipoFavorito()));

		for (Producto producto : this.productos)
			ofrecerProductoAlUsuario(usuario, producto);
		
	}
	
	public void ofrecerProductosALosUsuarios() {
		for (Usuario usuario : this.usuarios)
			ofrecerProductosAlUsuario(usuario);
		
		//Es momento de escribirlos
		ArchivoUsuarios.escribirUsuarios(usuarios);
	}
}
