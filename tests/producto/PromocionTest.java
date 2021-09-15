package producto;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromocionTest {
	List<Atraccion> listaAventura, listaPaisaje, listaPaisaje2, listaDegustacion;
	List<Promocion> listaPromociones, listaEsperada;

	Atraccion atrMoria, atrMordor, atrLothlorien, atrLaComarca, atrCafayate, atrCalafate;
	Promocion packAventura1, packAventura2, packDegustacion, packPaisaje, packPaisaje2, packPaisaje3;

	@Before
	public void setUp() throws Exception {
		listaPromociones = new LinkedList<Promocion>();
		listaEsperada = new LinkedList<Promocion>();
		listaAventura = new LinkedList<Atraccion>();
		listaPaisaje = new LinkedList<Atraccion>();
		listaDegustacion = new LinkedList<Atraccion>();
		listaPaisaje2 = new LinkedList<Atraccion>();

		atrMoria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		atrMordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA);
		atrLothlorien = new Atraccion("Lothlorien", 35, 1, 30, TipoDeAtraccion.DEGUSTACION);
		atrLaComarca = new Atraccion("La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);
		atrCafayate = new Atraccion("Cafayate", 10, 3, 100, TipoDeAtraccion.PAISAJE);
		atrCalafate = new Atraccion("Calafate", 20, 2, 50, TipoDeAtraccion.PAISAJE);

		listaAventura.add(atrMoria);
		listaAventura.add(atrMordor);
		listaDegustacion.add(atrLothlorien);
		listaDegustacion.add(atrLaComarca);
		listaPaisaje.add(atrCafayate);
		listaPaisaje2.add(atrCalafate);

		packAventura1 = new Porcentual("PACK AVENTURA 1", TipoDeAtraccion.AVENTURA, listaAventura, 20);// 28
		packAventura2 = new AxB("PACK AVENTURA 2", TipoDeAtraccion.AVENTURA, listaAventura, atrMordor); // 10
		packDegustacion = new Porcentual("PACK DEGUSTACION 1", TipoDeAtraccion.DEGUSTACION, listaDegustacion, 10);// 34.2
		packPaisaje = new AxB("PACK PAISAJE 1", TipoDeAtraccion.PAISAJE, listaPaisaje, atrCafayate); // 0
		packPaisaje2 = new Absoluta("PACK PAISAJE 2", TipoDeAtraccion.PAISAJE, listaPaisaje, 50); // 50
		packPaisaje3 = new Absoluta("PACK PAISAJE 3", TipoDeAtraccion.PAISAJE, listaPaisaje2, 20); // 20

		listaPromociones.add(packAventura1);
		listaPromociones.add(packAventura2);
		listaPromociones.add(packPaisaje);
		listaPromociones.add(packDegustacion);
		listaPromociones.add(packPaisaje2);
		listaPromociones.add(packPaisaje3);

	}

	@Test
	public void validarPreciosDePromocionesTest() {
		assertEquals(34.2, packDegustacion.getCosto(), 0); // Costo
		assertEquals(28, packAventura1.getCosto(), 0);
		assertEquals(10, packAventura2.getCosto(), 0);
		assertEquals(5, packAventura1.getDuracion(), 0); // Duracion
		assertEquals(3, packPaisaje.getDuracion(), 0);
		assertEquals(2, packPaisaje3.getDuracion(), 0);
	}

	@Test
	public void ordenarPromocionesPorPaisajeTest() {
		Collections.sort(listaPromociones, new OrdenarProductosPorPreferencia(TipoDeAtraccion.PAISAJE));

		listaEsperada.add(packPaisaje2);
		listaEsperada.add(packPaisaje3);
		listaEsperada.add(packPaisaje);
		listaEsperada.add(packDegustacion);
		listaEsperada.add(packAventura1);
		listaEsperada.add(packAventura2);

		assertEquals(listaEsperada, listaPromociones);
	}

	@Test
	public void ordenarPromocionesPorAventuraTest() {
		Collections.sort(listaPromociones, new OrdenarProductosPorPreferencia(TipoDeAtraccion.AVENTURA));

		listaEsperada.add(packAventura1);
		listaEsperada.add(packAventura2);
		listaEsperada.add(packPaisaje2);
		listaEsperada.add(packDegustacion);
		listaEsperada.add(packPaisaje3);
		listaEsperada.add(packPaisaje);

		assertEquals(listaEsperada, listaPromociones);
	}

	@Test
	public void ordenarPromocionesPorDegustacionTest() {
		Collections.sort(listaPromociones, new OrdenarProductosPorPreferencia(TipoDeAtraccion.DEGUSTACION));

		listaEsperada.add(packDegustacion);
		listaEsperada.add(packPaisaje2);
		listaEsperada.add(packAventura1);
		listaEsperada.add(packPaisaje3);
		listaEsperada.add(packAventura2);
		listaEsperada.add(packPaisaje);

		assertEquals(listaEsperada, listaPromociones);
	}

	@Test
	public void promocionAxBTest() {
		Promocion axb = new AxB("Pack AxB", TipoDeAtraccion.AVENTURA, listaAventura, atrMordor);

		assertEquals(10, axb.getCosto(), 0); // Solo el de Moria
		assertEquals(5, axb.getDuracion(), 0); // Moria y el premio
	}

	@Test
	public void creandoPromocionInvalidaTest() {
		boolean excepcion = false, excepcion2=false;
		try {
			@SuppressWarnings("unused")
			Promocion p3 = new Porcentual("PACK DEGUSTACION 1", TipoDeAtraccion.AVENTURA, listaDegustacion, 10);// INVALIDA
		} catch (Exception e) {
			excepcion = true;
		}
		assertTrue(excepcion);
		
		try {
			List<Atraccion> listaInvalida= new LinkedList<Atraccion>();
			listaInvalida.add(atrMordor);
			listaInvalida.add(atrCalafate);
			listaInvalida.add(atrCafayate);
			@SuppressWarnings("unused")
			Promocion p6 = new Absoluta("PACK PAISAJE 3", TipoDeAtraccion.PAISAJE, listaInvalida, 20); //INVALIDA
		} catch(Exception e) {
			excepcion2=true;
		}
		assertTrue(excepcion2);
	}

}
