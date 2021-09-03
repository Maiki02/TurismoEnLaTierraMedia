package usuario;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import producto.Atraccion;
import producto.TipoDeAtraccion;

public class usuarioTest {

	// usuario: nombre, presupuesto, horas disponibles, total a pagar, monedas
	// disponibles
	// atraccion: nombre, costo, duracion, cupo, tipo
	Usuario u = new Usuario("Juan", 1000, 10, TipoDeAtraccion.AVENTURA);
	Atraccion p = new Atraccion("Moria", 300, 2, 10, TipoDeAtraccion.AVENTURA);

	@Test
	public void test_puedeComprar() {
		Assert.assertTrue(u.puedeComprar(p));
	}

	@Test
	public void test_descontarMonedas() {
		assertEquals(700, u.descontarMonedas(p), 0.001);
	}

	@Test
	public void test_descontarHorasDisponibles() {
		assertEquals(8, u.descontarHorasDisponibles(p), 0.001);
	}

	@Test
	public void test_comprarProducto() {
		u.comprarProducto(p);

	}

}
