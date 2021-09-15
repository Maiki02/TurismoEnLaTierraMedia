package producto;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import usuario.Usuario;

public class ProductoTest {
	List<Producto> productos;
	List<Atraccion> listaAventura, listaPaisaje, listaInvalida, listaDegustacion;
	Atraccion a1,a2,a3,a4,a5,a6,a7,a8;
	Promocion p1,p2,p3,p4,p5,p6;
	
	@Before
	public void setUp() {
		listaAventura = new LinkedList<Atraccion>();
		listaDegustacion = new LinkedList<Atraccion>();
		listaPaisaje = new LinkedList<Atraccion>();
		listaInvalida = new LinkedList<Atraccion>();

		a1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		a2 = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		a3 = new Atraccion("Lothlorien", 35, 1, 30, TipoDeAtraccion.DEGUSTACION);
		a4 = new Atraccion("La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		a5 = new Atraccion("Cafayate", 10, 3, 100, TipoDeAtraccion.PAISAJE);
		a6 = new Atraccion("Calafate", 20, 2, 50, TipoDeAtraccion.PAISAJE);
		a7 = new Atraccion("universal", 50, 5, 30, TipoDeAtraccion.PAISAJE );
		a8 = new Atraccion("parque de la costa", 20, 10, 1, TipoDeAtraccion.DEGUSTACION);	

		listaAventura.add(a1);//Aventura
		listaAventura.add(a2);
		listaPaisaje.add(a5);//Paisaje
		listaPaisaje.add(a6);
		listaPaisaje.add(a7);

		p1 = new Porcentual("PACK AVENTURA 1", TipoDeAtraccion.AVENTURA, listaAventura, 20);// Mon:28 Hor: 5
		p2 = new AxB("PACK AVENTURA 2", TipoDeAtraccion.AVENTURA, listaAventura, a2); // Mon:10 Hor: 5
		p4 = new AxB("PACK PAISAJE 1", TipoDeAtraccion.PAISAJE, listaPaisaje, a7); // Mon:30 Hor: 10
		p5 = new Absoluta("PACK PAISAJE 2", TipoDeAtraccion.PAISAJE, listaPaisaje, 0); // Mon: 0 Hor: 10

		//Creamos lista de productos
		productos=new LinkedList<Producto>();
		
		productos.add(a1);
		productos.add(a2);
		productos.add(a3);
		productos.add(a4);
		productos.add(a5);
		productos.add(a6);
		productos.add(a7);
		productos.add(a8);
		productos.add(p1);
		productos.add(p2);
		productos.add(p4);
		productos.add(p5);
	}
	
	@Test
	public void ordenandoListaDeProductosTest() {
		Collections.sort(productos, new OrdenarProductosPorPreferencia(TipoDeAtraccion.AVENTURA));
		
		List<Producto> listaEsperada=new LinkedList<Producto>();
		listaEsperada.add(p1);
		listaEsperada.add(p2);
		listaEsperada.add(a2);
		listaEsperada.add(a1);
		listaEsperada.add(p4);
		listaEsperada.add(p5);
		listaEsperada.add(a7);
		listaEsperada.add(a3);
		listaEsperada.add(a8);
		listaEsperada.add(a6);
		listaEsperada.add(a5);
		listaEsperada.add(a4);
		
		assertEquals(listaEsperada, productos);
	}
	
	@Test
	public void ocupandoTodosLosProductosTest() {
		Usuario usuario= new Usuario("Manolo", 100, 100, TipoDeAtraccion.DEGUSTACION);
		for(Producto producto: productos) {
			producto.agregarAtracciones(usuario); //Todos los productos deberían haberse agregado y haberse ocupado.
		}
		
		assertEquals(3, a1.getCuposDisponibles());
		assertEquals(1, a2.getCuposDisponibles());
		assertEquals(29, a3.getCuposDisponibles());
		assertEquals(149, a4.getCuposDisponibles());
		assertEquals(97, a5.getCuposDisponibles());
		assertEquals(47, a6.getCuposDisponibles());
		assertEquals(27, a7.getCuposDisponibles());
		assertEquals(0, a8.getCuposDisponibles());
	}
	
	@Test
	public void ocupandoProductoQueNoSePuedeOcuparMasTest() {
		a8.ocuparAtraccion();
		assertEquals(0, a8.getCuposDisponibles());
		boolean seOcupo=a8.ocuparAtraccion();
		assertFalse(seOcupo);
	}
	
	@Test
	public void sonPromocionesTest() {
		assertFalse(a1.esPromocion());
		assertTrue(p1.esPromocion());
	}
	
	@Test
	public void esProductoYaElectoTest() {
		Usuario usuario= new Usuario("Manolo", 100, 100, TipoDeAtraccion.DEGUSTACION);
		usuario.comprarProducto(a1);
		usuario.comprarProducto(p4);
		
		assertTrue(a1.esProductoYaElecto(usuario));
		assertFalse(a2.esProductoYaElecto(usuario));
		assertTrue(a6.esProductoYaElecto(usuario));
		assertTrue(a7.esProductoYaElecto(usuario));
		assertTrue(p4.esProductoYaElecto(usuario));
		assertTrue(p5.esProductoYaElecto(usuario)); //Contiene las mismas atracciones que p5
		assertTrue(p1.esProductoYaElecto(usuario));
		assertFalse(a8.esProductoYaElecto(usuario));

	}
	

}
