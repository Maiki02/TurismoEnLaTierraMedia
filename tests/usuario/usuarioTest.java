package usuario;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import producto.*;

public class usuarioTest {

	// usuario: nombre, presupuesto, horas disponibles, total a pagar, monedas
	// disponibles
	// atraccion: nombre, costo, duracion, cupo, tipo
	List<Atraccion> listaAventura, listaPaisaje, listaDegustacion;
	Usuario u;
	Atraccion a, a2, a3, a4;
	Producto[] p;

	@Before
	public void setUp() {
		a = new Atraccion("Moria", 300, 2, 10, TipoDeAtraccion.AVENTURA);
		a2 = new Atraccion("Mordor", 1100, 12, 10, TipoDeAtraccion.AVENTURA);
		a3 = new Atraccion("Bosque Negro", 400, 12, 10, TipoDeAtraccion.DEGUSTACION);
		a4 = new Atraccion("MinasTirith", 1200, 9, 10, TipoDeAtraccion.PAISAJE);
		u = new Usuario("Juan", 1000, 10, TipoDeAtraccion.AVENTURA);
		p = new Producto[3];
		listaAventura = new LinkedList<Atraccion>();
		listaPaisaje = new LinkedList<Atraccion>();
		listaDegustacion = new LinkedList<Atraccion>();

		listaAventura.add(a);
		listaDegustacion.add(a3);
		listaPaisaje.add(a4);

	}

	@Test
	public void test_puedeComprar() {
		assertTrue(u.puedeComprar(a));
		u.comprarProducto(a);
		assertFalse(u.puedeComprar(a2));
		assertEquals(700, u.getMonedasDisponibles(), 0);
		assertEquals(300, u.getTotalAPagar(), 0);
	}

	@Test
	public void test_comprarProducto() {
		u.comprarProducto(a);
		assertTrue(u.getProductosComprados().contains(a));
	}

	@Test
	public void comprarAbsolutaTest() {
		Producto abso = new Absoluta("Pack3", TipoDeAtraccion.PAISAJE, listaPaisaje, 400);
		u.comprarProducto(abso);
		assertEquals(400, u.getTotalAPagar(), 0);

	}

	@Test
	public void comprarPorcentualTest() {
		Producto porc = new Porcentual("Pack 1", TipoDeAtraccion.AVENTURA, listaAventura, 20);
		u.comprarProducto(porc);
		assertEquals(240, u.getTotalAPagar(), 0);

	}

	@Test
	public void comprarAxBTest() {
		Promocion axb = new AxB("Pack2", TipoDeAtraccion.DEGUSTACION, listaPaisaje, a2);
		u.comprarProducto(axb);
		assertEquals(100, u.getTotalAPagar(), 0);

	}

	@Test
	public void comprarProductosQueNoSonDeSuTipoTest() {
		u.comprarProducto(a3);
		assertNotEquals(a3.getTipoAtraccion(), u.getTipoFavorito());

	}

	@Test
	public void comprarAtraccionesYVerificarAtraccionesElectas() {
		u.comprarProducto(a);
		assertTrue(u.esProductoYaElecto(a));
	}

}
