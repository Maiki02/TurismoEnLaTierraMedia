package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import excepciones.*;
import producto.*;

public class ArchivoAtracciones { // , implements leible
	private static final int DATOS_ESPERADOS_POR_LINEA = 5;

	private static Atraccion crearAtraccion(String linea)
			throws ValorNegativo, IllegalArgumentException, NumberFormatException, CantidadDatosInvalidos {

		String[] datos = linea.split(",");

		if (datos.length != DATOS_ESPERADOS_POR_LINEA)
			throw new CantidadDatosInvalidos("Cantidad de datos invalidos en: " + linea);
		
		String nombre = datos[0];
		double costo = Double.parseDouble(datos[1]);
		double tiempo = Double.parseDouble(datos[2]);
		int cupo = Integer.parseInt(datos[3]);
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(datos[4]);

		return new Atraccion(nombre, costo, tiempo, cupo, tipo);
	}
	
	public static List<Atraccion> leerArchivoAtracciones() {
		FileReader fr = null;
		BufferedReader br = null;
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		try {
			fr = new FileReader("archivos/atracciones.txt");
			br = new BufferedReader(fr);
			String linea = br.readLine(); // Leemos linea con caracteristicas
			while ((linea = br.readLine()) != null) {
				tratamientoDeExcepcion(atracciones, linea);
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
		return atracciones;
	}

	private static void tratamientoDeExcepcion(List<Atraccion> atracciones, String linea) {
		try {
			Atraccion nuevaAtraccion = crearAtraccion(linea.toUpperCase());
			atracciones.add(nuevaAtraccion);
			
		} catch (ValorNegativo ne) {
			System.err.println(ne.getMessage() + " en: " + linea);
		} catch (NumberFormatException e) {
			System.err.println("Uno de los datos leidos no es un numero valido en: " + linea);
		} catch (IllegalArgumentException iae) {
			System.err.println("Tipo de atraccion no reconocida en: " + linea);
		} catch (CantidadDatosInvalidos cdi) {
			System.err.println(cdi.getMessage() + " en: " + linea);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
