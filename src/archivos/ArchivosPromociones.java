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
	private static Promocion crearPromocion(String[] datos, List<Atraccion> atracciones) 
			throws ValorNegativo, IllegalArgumentException, NumberFormatException{
		
		String nombrePack=datos[3];
		
		TipoDePromocion tipoPromocion = TipoDePromocion.valueOf(datos[0].toUpperCase());
		TipoDeAtraccion tipoAtraccion=TipoDeAtraccion.valueOf(datos[2].toUpperCase());
		
		Map<String, Atraccion> atraccionesPorNombre = crearMapDeAtracciones(atracciones);
		List<Atraccion> atraccionesInvolucradas = atraccionesInvolucradas(datos, atraccionesPorNombre);
		
		if (tipoPromocion == TipoDePromocion.AXB) {
			String nombreAtraccionDePremio = datos[1];
			Atraccion premio = atraccionesPorNombre.get(nombreAtraccionDePremio);
			// VER QUE EXCEPCION OCURRE CUANDO LA ATRACCION NO ESTÁ
			return new AxB(nombrePack, tipoAtraccion, atraccionesInvolucradas, premio);
		}

		double premio = Double.parseDouble(datos[1]);
		if (premio < 0)
			throw new ValorNegativo("Su descuento absoluto o porcentual no puede ser negativo");

		if (tipoPromocion == TipoDePromocion.PORCENTUAL)
			return new Porcentual(nombrePack, tipoAtraccion, atraccionesInvolucradas, premio);
		if (tipoPromocion == TipoDePromocion.DESCUENTO)
			return new Absoluta(nombrePack, tipoAtraccion, atraccionesInvolucradas, premio);

	}

	// A partir de una linea de un archivo, se fija en su tercer columna en adelante
	// y a cada nombre de atraccion
	// lo mete en una LinkedList.
	private static List<Atraccion> atraccionesInvolucradas(String[] datos, 
			Map<String, Atraccion> atraccionesPorNombre) {

		List<Atraccion> atraccionesInvolucradas = new LinkedList<Atraccion>();
		
		for (String nombreAtraccion : datos) {
			if(atraccionesPorNombre.containsKey(nombreAtraccion)) //A partir del 4to elemento puede dar true
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

	public static List<Promocion> leerArchivo(List<Atraccion> atracciones) {
		FileReader fr = null;
		BufferedReader br = null;

		List<Promocion> promociones = new LinkedList<Promocion>();

		try {
			fr = new FileReader("archivos/promociones.txt");
			br = new BufferedReader(fr);

			String linea = br.readLine(); // Leemos linea con caracteristicas
			while ((linea = br.readLine()) != null) {
				try {
					String[] datos = linea.split(",");
					if (datos.length > DATOS_ESPERADOS_POR_LINEA)
						throw new CantidadDatosInvalidos("Cantidad de datos invalidos en: " + linea);

					Promocion promocion = crearPromocion(datos, atracciones);
					promociones.add(promocion);

				} catch (ValorNegativo ne) {
					System.err.println(ne.getMessage());
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leídos no es un numero válido en: " + linea);
				} catch (IllegalArgumentException iae) {
					System.err.println("Tipo de atraccion no reconocida");
				} catch (CantidadDatosInvalidos cdi) {
					System.err.println(cdi.getMessage());
				} catch (Exception e) {
					e.getMessage();
				}
			}
		} catch (IOException e) { // Se abrió incorrectamente el archivo
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
