package producto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import usuario.Usuario;

public class ProductoTests {

	Producto a1;
	Producto a2;
	Producto a3;
	Usuario u1;
	
	@Before
	public void setUp() {
		a1 = new Atraccion("disney", 100, 12, 50, TipoDeAtraccion.AVENTURA );
		a2 = new Atraccion("universal", 50, 5, 30, TipoDeAtraccion.PAISAJE );
		a3 = new Atraccion("parque de la costa", 20, 10, 1, TipoDeAtraccion.DEGUSTACION);	
	}

	@After
	public void tearDown() {
		a1 = null;
		a2 = null;
		a3 = null;
	}
	
	@Test
	public void tieneCupo() {
		assertTrue(a1.quedanCuposDisponibles());
		assertTrue(a2.quedanCuposDisponibles());
		assertTrue(a3.quedanCuposDisponibles());	
	}
	
	
	@Test
	public void costosValidos() {
		assertEquals(100, a1.getCosto(), 0);
		assertEquals(50, a2.getCosto(), 0);
		assertEquals(20, a3.getCosto(), 0);	
	}
	
	@Test
	public void tiemposValidos() {
		assertEquals(12, a1.getDuracion(), 0);
		assertEquals(5, a2.getDuracion(), 0);
		assertEquals(10, a3.getDuracion(), 0);
	}
	
	@Test
	public void ocupoTodosLosCupos() {
		u1 = new Usuario("pepe", 100, 10, TipoDeAtraccion.AVENTURA);
		assertTrue(a3.quedanCuposDisponibles());
		a3.agregarAtracciones(u1);
		assertFalse(a3.quedanCuposDisponibles());
	}

}
