package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import excepciones.ValorNegativo;
import producto.Atraccion;
import producto.TipoDeAtraccion;
import usuario.Usuario;

public class ArchivoUsuarios { // , implements leible

	public static LinkedList<Usuario> leerArchivo() {
		FileReader fr = null;
		BufferedReader br = null;

		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();

		try {
			fr = new FileReader(
					"/home/miquigentile/eclipse-workspace/TurismoGit/TurismoEnLaTierraMedia/src/archivos/atracciones.txt");
			br = new BufferedReader(fr);

			String linea = br.readLine(); // Leemos linea con caracteristicas

			while ((linea = br.readLine()) != null) {

				try {
					Usuario nuevoUsuario = crearUsuario(linea);
					if (nuevoUsuario != null) {
						usuarios.add(nuevoUsuario);
						nuevoUsuario=null;
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
		return usuarios;
	}

	private static Usuario crearUsuario(String lineaArchivo) {
		String[] caracteristicasDelUsuario = lineaArchivo.split(",");

		String nombre = caracteristicasDelUsuario[0];
		double monedasDisponibles = Double.parseDouble(caracteristicasDelUsuario[1]);
		double tiempoDisponible = Double.parseDouble(caracteristicasDelUsuario[2]);
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(caracteristicasDelUsuario[3]);

		if (monedasDisponibles < 0 || tiempoDisponible < 0) {
			throw new ValorNegativo("Fue pasado un valor negativo");
		}
		return new Usuario(nombre, monedasDisponibles, tiempoDisponible, tipo);
	}
}
