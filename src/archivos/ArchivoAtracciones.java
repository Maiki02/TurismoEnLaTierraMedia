package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import excepciones.ValorNegativo;
import producto.Atraccion;
import producto.Promocion;
import producto.TipoDeAtraccion;

public class ArchivoAtracciones { // , implements leible

	public static Atraccion crearAtraccion(String lineaArchivo) {
		String[] caracteristicasPorAtraccion = lineaArchivo.split(",");

		String nombre = caracteristicasPorAtraccion[0];
		double costo = Double.parseDouble(caracteristicasPorAtraccion[1]);
		double tiempo = Double.parseDouble(caracteristicasPorAtraccion[2]);
		int cupo = Integer.parseInt(caracteristicasPorAtraccion[3]);
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(caracteristicasPorAtraccion[4]);

		if (costo < 0 || tiempo < 0 || cupo <= 0) {
			throw new ValorNegativo("Fue pasado un valor negativo");
		}
		return new Atraccion(nombre, costo, tiempo, cupo, tipo);
	}

	public static LinkedList<Atraccion> leerArchivo() {
		FileReader fr = null;
		BufferedReader br = null;

		LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
		// *********
		// CREO QUE EN VEZ DE HACER LINKED LIST, SERIA CONVENIENTE HACER UNA COLECCION
		// PARA QUE NO SE REPITAN LOS NOMBRES DE LAS ATRACCIONES (Nuestro criterio de
		// equals va a ser
		// que dos atracciones son iguales si tienen el mismo nombre)
		// *********

		try {
			fr = new FileReader(
					"/home/miquigentile/eclipse-workspace/TurismoGit/TurismoEnLaTierraMedia/src/archivos/atracciones.txt");
			br = new BufferedReader(fr);

			String linea = br.readLine(); // Leemos linea con caracteristicas

			while ((linea = br.readLine()) != null) {

				try {
					Atraccion atraccionNueva = crearAtraccion(linea);
					if (atraccionNueva != null) {
						atracciones.add(atraccionNueva);
						atraccionNueva=null;
					}

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
		return atracciones;
	}
}
