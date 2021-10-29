package parqueDeAtracciones;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import DAO.*;
import comparador.OrdenarProductosPorPreferencia;
import excepciones.*;
import usuario.*;
import producto.*;

public class ParqueDeAtracciones {

	private List<Usuario> usuarios;
	private List<Producto> productos;
	private List<Atraccion> atracciones;
	private List<Promocion> promociones;

	public ParqueDeAtracciones() throws SQLException, AtraccionDeDistintoTipo, ValorNegativo {
		iPromocionDAO promocionDAO= DAOFactory.getPromocionDAO();
		iAtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		iUsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		
		atracciones = atraccionDAO.listar();		
		promociones = promocionDAO.listarPromocionesValidas(atracciones);

		this.productos = crearListaDeProductos(atracciones, promociones);
		this.usuarios = usuarioDAO.listarUsuarios(atracciones,promociones);
	}
	/*
	 * @Pre: Deben estar cargadas las listas de atracciones y promociones.
	 * @Post: Crea lista de productos con atracciones y promociones.
	 */
	private List<Producto> crearListaDeProductos(List<Atraccion> atracciones, List<Promocion> promociones) {
		productos = new LinkedList<Producto>();

		for (Producto promocion : promociones)
			productos.add(promocion);
		for (Producto atraccion : atracciones)
			productos.add(atraccion);

		return productos;
	}

	/*
	 * @Post: Habilita al usuario para ingresar por teclado su elecci�n de producto.
	 */
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
	
	/*
	 * @Pre:Lista creada de productos.
	 * @Post: Ofrece producto al usuario y lo compra si el mismo es aceptado.
	 */
	private void ofrecerProductoAlUsuario(Usuario usuario, Producto producto) {
		String opcion="";
		boolean contiene=producto.esProductoYaElecto(usuario);
		
		if (usuario.puedeComprar(producto) && !contiene ) { 
			
			System.out.println(producto); //Mostramos el producto
			opcion=preguntarSiQuiereAtraccion();//Preguntamos si lo quiere
			
			
			if (opcion.equals("S")) {
				usuario.comprarProducto(producto);//Compra el producto
			}
		}
	}

	/*
	 * @Pre: Lista de productos ya creada.
	 * @Post: Le ofrece al usuario cada producto ordenado por preferencia.
	 */
	private void ofrecerProductosAlUsuario(Usuario usuario) {
		Collections.sort(productos, new OrdenarProductosPorPreferencia(usuario.getTipoFavorito()));
		for (Producto producto : this.productos) {
			ofrecerProductoAlUsuario(usuario, producto);
		}
		System.out.println("\n");
	}
	/*
	 * @Pre
	 * @Post: A cada usuario le ofrece productos.
	 */
	public void ofrecerProductosALosUsuarios() throws SQLException {
		System.out.println();
		for (Usuario usuario : this.usuarios) {
			System.out.println(usuario);
			ofrecerProductosAlUsuario(usuario);
		}
	
		iUsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		iAtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		for(Usuario usuario: usuarios) {
			usuarioDAO.actualizar(usuario);
		}
		
		for(Atraccion atraccion: atracciones) {
			atraccionDAO.actualizar(atraccion);
		}
	}
}
