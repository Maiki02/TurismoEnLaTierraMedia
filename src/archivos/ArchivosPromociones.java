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
	private static Promocion crearPromocion(String[] datos, TipoDePromocion tipoPromocion,
			List<Atraccion> atraccionesInvolucradas, List<Atraccion> atracciones) {
		
		String nombrePack=datos[3];
		TipoDeAtraccion tipoAtraccion=TipoDeAtraccion.valueOf(datos[2]);
		
		
		if (tipoPromocion == TipoDePromocion.AXB) {
			String nombreAtraccionDePremio = datos[1];
			Atraccion premio = atracciones.get(atracciones.indexOf(nombreAtraccionDePremio));
			// VER QUE EXCEPCION OCURRE CUANDO LA ATRACCION NO ESTÁ
			return new AxB(, tipo, atraccionesInvolucradas, premio);
		}

		double premio = Double.parseDouble(datos[1]);
		if (premio < 0)
			throw new ValorNegativo("Su descuento absoluto o porcentual no puede ser negativo");

		if (tipoPromocion == TipoDePromocion.PORCENTUAL)
			return new Porcentual("nombre", tipo, atraccionesInvolucradas, premio);
		if (tipoPromocion == TipoDePromocion.DESCUENTO)
			return new Absoluta("nombre", tipo, atraccionesInvolucradas, premio);

		return null;

	}

	// A partir de una linea de un archivo, se fija en su tercer columna en adelante
	// y a cada nombre de atraccion
	// lo mete en una LinkedList.
	private static List<Atraccion> atraccionesInvolucradas(String[] datos, List<Atraccion> atracciones) {

		List<Atraccion> atraccionesInvolucradas = new LinkedList<Atraccion>();
		Map<String, Atraccion> atraccionesPorNombre = crearMapDeAtracciones(atracciones);
		
		int vecesRecorrido = 0;
		for (String nombreAtraccion : datos) {
			
			//A partir del 5to dato de la linea, comienzan las atracciones involucradas en la promocion
			if (vecesRecorrido > 4) {
				
					atraccionesInvolucradas.add(atracciones.get(index));
				// ** ES UN METODO INEFICIENTE, NO SE SI IMPORTARÁ SUPONIENDO QUE SON 10
				// ATRACCIONES
			}

			vecesRecorrido++;
		}

		return atraccionesInvolucradas;

	}

	private static Map<String, Atraccion> crearMapDeAtracciones(List<Atraccion> atracciones){
		Map<String, Atraccion> atraccionesPorNombre = new HashMap<String, Atraccion>();
		for(Atraccion atraccion: atracciones) {
			atraccionesPorNombre.put(atraccion.getNombre(), atraccion);
		}
		return atraccionesPorNombre;
	}
	
	public static List<Promocion> leerArchivo(List<Atraccion> atracciones) {
		FileReader fr = null;
		BufferedReader br = null;

		LinkedList<Promocion> promociones = new LinkedList<Promocion>();

		try {
			fr = new FileReader("archivos/promociones.txt");
			br = new BufferedReader(fr);

			String linea = br.readLine(); // Leemos linea con caracteristicas
			while ((linea = br.readLine()) != null) {
				try {
					String[] datos = linea.split(",");
					if (datos.length < DATOS_ESPERADOS_POR_LINEA)
						throw new CantidadDatosInvalidos("Cantidad de datos invalidos en: " + linea);

					TipoDePromocion tipoPromocion = TipoDePromocion.valueOf(datos[0]);
					List<Atraccion> atraccionesInvolucradas = atraccionesInvolucradas(datos, atracciones);
					Promocion promocion = crearPromocion(datos, tipo, atraccionesInvolucradas, atracciones);
					promociones.add(promocion);

				} catch (ValorNegativo ne) {
					System.err.println(ne.getMessage());
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leídos no es un numero válido");
				} catch (IllegalArgumentException iae) {
					System.err.println("Tipo de atraccion no reconocida");
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
