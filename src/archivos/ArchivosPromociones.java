package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import excepciones.*;
import producto.*;

public class ArchivosPromociones {

	// Crea una promocion a partir de la linea de un arcchivo
	private static Promocion crearPromocion(String[] caracteristicasPorPromocion, TipoDePromocion tipo,
			LinkedList<Atraccion> atraccionesInvolucradas, LinkedList<Atraccion> atracciones) {

		if (tipo == TipoDePromocion.AXB) {

			String nombreAtraccionDePremio = caracteristicasPorPromocion[1];
			Atraccion premio = atracciones.get(atracciones.indexOf(nombreAtraccionDePremio));
			// VER QUE EXCEPCION OCURRE CUANDO LA ATRACCION NO ESTÁ

			return new AxB("nombre", tipo, atraccionesInvolucradas, premio);
		} else {

			double premio = Double.parseDouble(caracteristicasPorPromocion[1]);
			if (premio < 0) {
				throw new ValorNegativo("Su descuento absoluto o porcentual no puede ser negativo");
			}

			if (tipo == TipoDePromocion.PORCENTUAL) {
				return new Porcentual("nombre", tipo, atraccionesInvolucradas, premio);
			} else if (tipo == TipoDePromocion.DESCUENTO) {
				return new Absoluta("nombre", tipo, atraccionesInvolucradas, premio);
			}

		}

		return null;

	}

	// A partir de una linea de un archivo, se fija en su tercer columna en adelante
	// y a cada nombre de atraccion
	// lo mete en una LinkedList.
	public static LinkedList<Atraccion> atraccionesInvolucradas(String[] caracteristicasPorPromocion,
			LinkedList<Atraccion> atracciones) {

		LinkedList<Atraccion> atraccionesInvolucradas = new LinkedList<Atraccion>();

		int vecesRecorrido = 0;
		for (String nombreAtraccion : caracteristicasPorPromocion) {
			
			if (vecesRecorrido <= 1) {
				//Primero recorremos 2 veces, que son los campos de tipo y premio.
				vecesRecorrido++;
			} else {
				// Si la atraccion tiene el mismo nombre que la que dice en el archivo, la
				// agregamos
				int index = atracciones.indexOf(nombreAtraccion);
				if (index != -1)
					atraccionesInvolucradas.add(atracciones.get(index));
				// ** ES UN METODO INEFICIENTE, NO SE SI IMPORTARÁ SUPONIENDO QUE SON 10
				// ATRACCIONES

			}
		}

		return atraccionesInvolucradas;

	}

	public static LinkedList<Promocion> leerArchivo(LinkedList<Atraccion> atracciones) {
		FileReader fr = null;
		BufferedReader br = null;

		LinkedList<Promocion> promociones = new LinkedList<Promocion>();

		try {
			fr = new FileReader(
					"/home/miquigentile/eclipse-workspace/TurismoGit/TurismoEnLaTierraMedia/src/archivos/atracciones.txt");
			br = new BufferedReader(fr);
			String linea = br.readLine(); // Leemos linea con caracteristicas

			while ((linea = br.readLine()) != null) {

				try {
					String[] caracteristicasPorPromocion = linea.split(",");

					TipoDePromocion tipo = TipoDePromocion.valueOf(caracteristicasPorPromocion[0]);
					LinkedList<Atraccion> atraccionesInvolucradas;
					atraccionesInvolucradas = atraccionesInvolucradas(caracteristicasPorPromocion, atracciones);
					Promocion promocion;
					promocion = crearPromocion(caracteristicasPorPromocion, tipo, atraccionesInvolucradas, atracciones);
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
