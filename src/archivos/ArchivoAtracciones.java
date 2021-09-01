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

	public static Atraccion crearAtraccion(String[] datos) 
			throws ValorNegativo, IllegalArgumentException, NumberFormatException {
		String nombre = datos[0]; // Capaz que conviene pasar el nombre a mayusculas o minusculas
		double costo = Double.parseDouble(datos[1]);
		double tiempo = Double.parseDouble(datos[2]);
		int cupo = Integer.parseInt(datos[3]);
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(datos[4]);

		if (costo < 0 || tiempo < 0 || cupo <= 0) {
			throw new ValorNegativo("Fue pasado un valor negativo");
		}

		return new Atraccion(nombre, costo, tiempo, cupo, tipo);
	}

	public static List<Atraccion> leerArchivo() {
		FileReader fr = null;
		BufferedReader br = null;
		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		// *********
		// CREO QUE EN VEZ DE HACER LINKED LIST, SERIA CONVENIENTE HACER UNA COLECCION
		// PARA QUE NO SE REPITAN LOS NOMBRES DE LAS ATRACCIONES (Nuestro criterio de
		// equals va a ser
		// que dos atracciones son iguales si tienen el mismo nombre)
		// *********

		try {
			fr = new FileReader("archivos/atracciones.txt");
			br = new BufferedReader(fr);

			String linea = br.readLine(); // Leemos linea con caracteristicas
			while ((linea = br.readLine()) != null) {
				try {
					String[] datos = linea.split(",");
					if (datos.length != DATOS_ESPERADOS_POR_LINEA) 
						throw new CantidadDatosInvalidos("Cantidad de datos invalidos en: " + linea);
					
					Atraccion atraccionNueva = crearAtraccion(datos);
					if (atraccionNueva != null) {
						atracciones.add(atraccionNueva);
					}

				} catch (ValorNegativo ne) {
					System.err.println(ne.getMessage() + " en: " + linea);
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leídos no es un numero válido en: " + linea);
				} catch (IllegalArgumentException iae) {
					System.err.println("Tipo de atraccion no reconocida en: " + linea);
				} catch (CantidadDatosInvalidos cdi) {
					System.err.println(cdi.getMessage()+ " en: " + linea);
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
		return atracciones;
	}
}
