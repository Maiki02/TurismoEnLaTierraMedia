package parqueDeAtracciones;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import usuario.*;
import producto.*;

public class ParqueDeAtracciones {

	private List<Usuario> usuarios;
	private List<Producto> productos;

	public ParqueDeAtracciones(List<Usuario> usuarios, List<Atraccion> atracciones, List<Promocion> promociones) {
		this.usuarios = usuarios;
		this.productos = crearListaDeProductos(atracciones, promociones);
	}

	private List<Producto> crearListaDeProductos(List<Atraccion> atracciones, List<Promocion> promociones) {
		productos = new LinkedList<Producto>();

		for (Producto atraccion : atracciones)
			productos.add(atraccion);
		for (Producto promocion : promociones)
			productos.add(promocion);

		return productos;
	}

	public void verProductos() {
		for (Producto producto : this.productos)
			System.out.println(producto);
	}

	private void ofrecerProductoAlUsuario(Usuario usuario, Producto producto) {
		
		if (usuario.puedeComprar(producto)) { //Si puede comprar y la atraccion no fue electa
			System.out.println(producto); //Mostramos el producto
			boolean seAcepto = true; // Aca ejecutariamos la linea de comando
			if (seAcepto) {
				usuario.comprarProducto(producto);//Compra el producto
				
				//Si el producto es una Promocion,
				//Las atracciones involucradas deben ser agregadas a la lista de atraccionesElectas
				
				//Si el producto es una Atraccion, 
				//La atraccion debe ser agregada a la lista de atraccionesElectas.
				
				//Una idea es que las atracciones electas se encargue el usuario.
				//No es lo mismo una atraccionElecta, que lasAtraccionesInvolucradas de la promocion,
				//que los productosCompradosDelUsuario
			}
		}
		
	}
	
	private void ofrecerProductosAlUsuario(Usuario usuario) {
		Collections.sort(productos, new OrdenarProductosPorPreferencia(usuario.getTipoFavorito()));

		for (Producto producto : this.productos) {

			ofrecerProductoAlUsuario(usuario, producto);
			
		}
	}
	
	
	
	public void ofrecerProductosALosUsuarios() {

		for (Usuario usuario : this.usuarios) {
			ofrecerProductosAlUsuario(usuario);
		}
	}
}
