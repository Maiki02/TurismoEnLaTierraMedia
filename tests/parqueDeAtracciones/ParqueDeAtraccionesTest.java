package parqueDeAtracciones;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import archivos.ArchivoAtracciones;
import archivos.ArchivoPromociones;
import producto.Atraccion;
import producto.OrdenarProductosPorPreferencia;
import producto.Producto;
import producto.Promocion;
import producto.TipoDeAtraccion;
import usuario.Usuario;

public class ParqueDeAtraccionesTest {

	ParqueDeAtracciones pa = new ParqueDeAtracciones();
	List<Atraccion> atracciones = ArchivoAtracciones.leerArchivoAtracciones();
	List<Promocion> promociones = ArchivoPromociones.leerArchivoPromociones(atracciones);
	List<Producto> productos = new LinkedList<Producto>();
	List<Usuario> usuarios;

	@Test
	public void creaListaProductos() {
		List<Producto> a = pa.getProductos();
		assertTrue(a != null);
	}

	@Test
	public void contieneAtracciones() {
		boolean ban = false;
		for (Atraccion atraccion : atracciones) {
			if (pa.getProductos().contains(atraccion)) {
				ban = true;
			}
		}
		assertTrue(ban);
	}
	@Test
	public void contienePromociones() {
		boolean ban = false;
		for (Promocion promocion : promociones) {
			if (pa.getProductos().contains(promocion)) {
				ban = true;
			}
		}
		assertTrue(ban);
	}
}
