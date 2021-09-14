package parqueDeAtracciones;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.*;

import archivos.*;
import producto.*;
import usuario.Usuario;

public class ParqueDeAtraccionesTest {

	ParqueDeAtracciones pa;
	List<Atraccion> atracciones;
	List<Promocion> promociones;
	List<Producto> productos;
	List<Usuario> usuarios;

	@Before
	public void setUp() {
		pa= new ParqueDeAtracciones();
		atracciones=ArchivoAtracciones.leerArchivoAtracciones();
		promociones= ArchivoPromociones.leerArchivoPromociones(atracciones);
		productos= new LinkedList<Producto>();
	}
	
	
	@Test
	public void creaListaProductos() {
		List<Producto> a = pa.getProductos();
		assertTrue(a != null);
	}

	@Test
	public void contieneAtracciones() {
		boolean ban = false;
		productos.addAll(atracciones);
		for (Producto atraccion : atracciones) {
			if (productos.contains(atraccion)) {
				ban = true;
			}
		}
		assertTrue(ban);
	}
	
	@Test
	public void contienePromociones() {
		boolean ban = false;
		productos.addAll(promociones);
		for (Promocion promocion : promociones) {
			if (productos.contains(promocion)) {
				ban = true;
			}
		}
		assertTrue(ban);
	}
}
