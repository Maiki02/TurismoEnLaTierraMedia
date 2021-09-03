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

public class ArchivosPromociones {
	private static final int DATOS_ESPERADOS_POR_LINEA = 5;

	// Crea una promocion a partir de la linea de un arcchivo

	private static Promocion crearPromocion(String linea, Map<String, Atraccion> atraccionesPorNombre)
			throws ValorNegativo, IllegalArgumentException, NumberFormatException, AtraccionDeDistintoTipo,
			CantidadDatosInvalidos {

		String[] datos = linea.split(",");
		if (datos.length > DATOS_ESPERADOS_POR_LINEA)
			throw new CantidadDatosInvalidos("Cantidad de datos invalidos en: " + linea);

		String nombrePack = datos[3];

		TipoDePromocion tipoPromocion = TipoDePromocion.valueOf(datos[0].toUpperCase());
		TipoDeAtraccion tipoAtraccion = TipoDeAtraccion.valueOf(datos[2].toUpperCase());

		List<Atraccion> atraccionesInvolucradas = atraccionesInvolucradas(datos, atraccionesPorNombre);
		if (!sonAtraccionesValidas(atraccionesInvolucradas, tipoAtraccion))
			throw new AtraccionDeDistintoTipo("Hay atracciones que no son del mismo tipo que el pack");

		if (tipoPromocion == TipoDePromocion.AXB) {
			String nombreAtraccionDePremio = datos[1];
			Atraccion premio = atraccionesPorNombre.get(nombreAtraccionDePremio);
			if (!esAtraccionValida(premio, tipoAtraccion))
				throw new AtraccionDeDistintoTipo("El premio no es del mismo tipo que el pack");
			// VER QUE EXCEPCION OCURRE CUANDO LA ATRACCION NO ESTA
			return new AxB(nombrePack, tipoAtraccion, atraccionesInvolucradas, premio);
		}

		double premio = Double.parseDouble(datos[1]);
		if (premio < 0)
			throw new ValorNegativo("Su descuento absoluto o porcentual no puede ser negativo");

		if (tipoPromocion == TipoDePromocion.PORCENTUAL)
			return new Porcentual(nombrePack, tipoAtraccion, atraccionesInvolucradas, premio);
		if (tipoPromocion == TipoDePromocion.DESCUENTO)
			return new Absoluta(nombrePack, tipoAtraccion, atraccionesInvolucradas, premio);

		return null;
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

	// A partir de una linea de un archivo, se fija en su tercer columna en adelante
	// y a cada nombre de atraccion
	// lo mete en una LinkedList.
	private static List<Atraccion> atraccionesInvolucradas(String[] datos,
			Map<String, Atraccion> atraccionesPorNombre) {

		List<Atraccion> atraccionesInvolucradas = new LinkedList<Atraccion>();

		for (String nombreAtraccion : datos) {
			if (atraccionesPorNombre.containsKey(nombreAtraccion)) // A partir del 4to elemento puede dar true
				atraccionesInvolucradas.add(atraccionesPorNombre.get(nombreAtraccion));
		}
		return atraccionesInvolucradas;
	}

	private static Map<String, Atraccion> crearMapDeAtracciones(List<Atraccion> atracciones) {
		Map<String, Atraccion> atraccionesPorNombre = new HashMap<String, Atraccion>();
		for (Atraccion atraccion : atracciones) {
			atraccionesPorNombre.put(atraccion.getNombre(), atraccion);
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
					Promocion promocion = crearPromocion(linea, atraccionesPorNombre);
					promociones.add(promocion);

				} catch (ValorNegativo ne) {
					System.err.println(ne.getMessage());
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leidos no es un numero valido en: " + linea);
				} catch (IllegalArgumentException iae) {
					System.err.println("Tipo de atraccion no reconocida en: " + linea);
				} catch (CantidadDatosInvalidos cdi) {
					System.err.println(cdi.getMessage());
				} catch (Exception e) {
					e.getMessage();
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
