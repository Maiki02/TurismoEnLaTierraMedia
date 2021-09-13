package usuario;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import producto.*;

public class usuarioTest {

	// usuario: nombre, presupuesto, horas disponibles, total a pagar, monedas
	// disponibles
	// atraccion: nombre, costo, duracion, cupo, tipo
	Usuario u; 
	Producto a, a2, a3;
	Producto[] p;
	Promocion promo1, promo2, promo3;
	
	
	@Before
	public void setUp() {
		 a=new Atraccion("Moria", 300, 2, 10, TipoDeAtraccion.AVENTURA);
		 a2= new Atraccion("Mordor", 1000, 12, 10, TipoDeAtraccion.AVENTURA);
		 a3= new Atraccion("Bosque Negro", 400, 12, 10, TipoDeAtraccion.DEGUSTACION);
		 u=new Usuario("Juan", 1000, 10, TipoDeAtraccion.AVENTURA);
		 p=new Producto[3];
		 promo1 = new AxB;
		 promo2 = new Absoluta;
		 promo3 = new Porcentual("Bosque Negro", 400, 12, 10, TipoDeAtraccion.DEGUSTACION);
		
		 
	
		 
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
	public void comprarProductosYpromocionesTest() {
		;
		
		
	}
	
	@Test
	public void comprarProductosQueNoSonDeSuTipoTest() {
		u.comprarProducto(a3);
		assertNotEquals(a3.getTipoAtraccion(),u.getTipoFavorito());
		
		
	}
	
	@Test
	public void comprarAtraccionesYVerificarAtraccionesElectas() {
		u.comprarProducto(a);
		assertTrue(u.esProductoYaElecto(a));	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
