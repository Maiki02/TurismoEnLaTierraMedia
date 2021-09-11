package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import excepciones.*;
import producto.*;

public class ArchivoPromociones {
	private static final int DATOS_ESPERADOS_POR_LINEA = 5;

	// Crea una promocion a partir de la linea de un arcchivo
	private static Promocion crearPromocion(String linea, Map<String, Atraccion> atraccionesPorNombre)
			throws ValorNegativo, IllegalArgumentException, NumberFormatException, AtraccionDeDistintoTipo,
			CantidadDatosInvalidos, AtraccionInexistente {

		String[] datos = linea.split(",");

		if (datos.length < DATOS_ESPERADOS_POR_LINEA)
			throw new CantidadDatosInvalidos("Cantidad de datos invalidos en: " + linea);

		String nombrePack = datos[3];

		TipoDePromocion tipoPromocion = TipoDePromocion.valueOf(datos[0]);
		TipoDeAtraccion tipoAtraccion = TipoDeAtraccion.valueOf(datos[2]);

		List<Atraccion> atraccionesInvolucradas = atraccionesInvolucradas(datos, atraccionesPorNombre);

		if (!sonAtraccionesValidas(atraccionesInvolucradas, tipoAtraccion))
			throw new AtraccionDeDistintoTipo("Hay atracciones que no son del mismo tipo que el pack");

		if (tipoPromocion == TipoDePromocion.AXB) {
			String nombreAtraccionDePremio = datos[1];

			if (!atraccionesPorNombre.containsKey(nombreAtraccionDePremio))
				throw new AtraccionInexistente("Su premio es invalido en: " + linea);
			Atraccion premio = atraccionesPorNombre.get(nombreAtraccionDePremio);

			if (!esAtraccionValida(premio, tipoAtraccion))
				throw new AtraccionDeDistintoTipo("El premio no es del mismo tipo que el pack");
			atraccionesInvolucradas.add(premio); // Agregamos el premio a las atraccionesInvolucradas

			return new AxB(nombrePack, tipoAtraccion, atraccionesInvolucradas, premio);
		}

		double premio = Double.parseDouble(datos[1]);
		if (premio < 0)
			throw new ValorNegativo("Su descuento absoluto o porcentual no puede ser negativo");

		if (tipoPromocion == TipoDePromocion.PORCENTUAL)
			return new Porcentual(nombrePack, tipoAtraccion, atraccionesInvolucradas, premio);
		else {
			return new Absoluta(nombrePack, tipoAtraccion, atraccionesInvolucradas, premio);
		}

	}

	private static boolean esAtraccionValida(Atraccion atraccion, TipoDeAtraccion tipoAtraccion) {
		return atraccion.getTipoAtraccion() == tipoAtraccion;
	}

	private static boolean sonAtraccionesValidas(List<Atraccion> atracciones, TipoDeAtraccion tipoAtraccion) {
		for (Atraccion atraccion : atracciones) {
			if (!esAtraccionValida(atraccion, tipoAtraccion)) // Si es una atraccion invalida devuelve false
				return false;
		}
		return true;
	}

	// A partir de una linea de un archivo, se fija en su cuarta columna en adelante
	// y a cada nombre de atraccion
	// lo mete en una LinkedList.
	private static List<Atraccion> atraccionesInvolucradas(String[] datos, Map<String, Atraccion> atraccionesPorNombre)
			throws AtraccionInexistente {

		List<Atraccion> atraccionesInvolucradas = new LinkedList<Atraccion>();

		for (int i = 4; i < datos.length; i++) { // Leemos las atracciones que incluyen a la promocion
			if (atraccionesPorNombre.containsKey(datos[i])) {// Si la contiene, se agrega la atraccion
				atraccionesInvolucradas.add(atraccionesPorNombre.get(datos[i]));
			} else
				throw new AtraccionInexistente(datos[i] + " es una atraccion inexistente.");
			// Si no la contiene, lanzamos una excepcion
		}
		return atraccionesInvolucradas;
	}

	public static Map<String, Atraccion> crearMapDeAtracciones(List<Atraccion> atracciones) {
		Map<String, Atraccion> atraccionesPorNombre = new HashMap<String, Atraccion>();
		for (Atraccion atraccion : atracciones) {
			atraccionesPorNombre.put(atraccion.getNombre(), atraccion); // Creacion de mapa
		}
		return atraccionesPorNombre;
	}

	public static List<Promocion> leerArchivoPromociones(List<Atraccion> atracciones) {
		FileReader fr = null;
		BufferedReader br = null;

		List<Promocion> promociones = new LinkedList<Promocion>();
		Map<String, Atraccion> atraccionesPorNombre = crearMapDeAtracciones(atracciones);

		try {
			fr = new FileReader("archivos/promociones.txt");
			br = new BufferedReader(fr);

			String linea = br.readLine(); // Leemos linea con caracteristicas
			while ((linea = br.readLine()) != null) {
				try {

					Promocion promocion = crearPromocion(linea.toUpperCase(), atraccionesPorNombre);
					promociones.add(promocion);

				} catch (ValorNegativo ne) {
					System.err.println(ne.getMessage());
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leidos no es un numero valido en: " + linea);
				} catch (IllegalArgumentException iae) {
					System.err.println("Tipo de atraccion o promocion no reconocida en: " + linea);
				} catch (CantidadDatosInvalidos cdi) {
					System.err.println(cdi.getMessage());
				} catch (AtraccionInexistente ai) {
					System.err.println(ai.getMessage());
				} catch (AtraccionDeDistintoTipo addt) {
					System.err.println(addt.getMessage() + " en: " + linea);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		} catch (IOException e) { // Se abria incorrectamente el archivo
			e.printStackTrace();
		} finally { // Cerramos el archivo
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return promociones;
	}
}
