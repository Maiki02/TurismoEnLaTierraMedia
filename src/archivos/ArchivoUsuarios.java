package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import excepciones.CantidadDatosInvalidos;
import excepciones.ValorNegativo;
import producto.TipoDeAtraccion;
import usuario.Usuario;

public class ArchivoUsuarios { // , implements leible
	private static final int DATOS_ESPERADOS_POR_LINEA = 4;

	public static List<Usuario> leerArchivoUsuarios() {
		FileReader fr = null;
		BufferedReader br = null;
		List<Usuario> usuarios = new LinkedList<Usuario>();

		try {
			fr = new FileReader("archivos/usuarios.txt");
			br = new BufferedReader(fr);

			String linea = br.readLine(); // Leemos linea con caracteristicas
			while ((linea = br.readLine()) != null) {
				try {
					Usuario nuevoUsuario = crearUsuario(linea);
					usuarios.add(nuevoUsuario);

				} catch (ValorNegativo ne) {
					System.err.println(ne.getMessage() + " en: " + linea);
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leidos no es un numero valido en: " + linea);
				} catch (IllegalArgumentException iae) {
					System.err.println("Tipo de atraccion o descuento no reconocido en: " + linea);
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
		return usuarios;
	}

	private static Usuario crearUsuario(String linea)
			throws ValorNegativo, IllegalArgumentException, NumberFormatException, CantidadDatosInvalidos {

		String[] datos = linea.split(",");
		if (datos.length != DATOS_ESPERADOS_POR_LINEA) {
			throw new CantidadDatosInvalidos("Cantidad de datos invalidos en: " + linea);
		}

		String nombre = datos[0].toUpperCase();
		double monedasDisponibles = Double.parseDouble(datos[1]);
		double tiempoDisponible = Double.parseDouble(datos[2]);
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(datos[3].toUpperCase());

		if (monedasDisponibles < 0 || tiempoDisponible < 0) {
			throw new ValorNegativo("Fue pasado un valor negativo");
		}
		return new Usuario(nombre, monedasDisponibles, tiempoDisponible, tipo);
	}

	public static void escribirUsuarios(List<Usuario> listaUsuario) {
		
		if (listaUsuario != null) {

			for (Usuario usuario : listaUsuario) {
				try {
					escribirUsuario(usuario);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void escribirUsuario(Usuario usuario) throws IOException {

		PrintWriter salida = new PrintWriter(new FileWriter(usuario.getNombre() + "." + "out"));

		// NOMBRE
		// COMPRA: LISTA DE PRODUCTOS
		// TOTAL A PAGAR: Int total a Pagar
		// TIEMPO A INVERTIR: Int horasGastadas

		salida.println(usuario.getNombre());
		salida.print("COMPRA:");
		salida.println(usuario.getProductosComprados());
		salida.print("TOTAL A PAGAR: ");
		salida.println(usuario.getTotalAPagar());
		salida.print("TIEMPO A INVERTIR: ");
		salida.println(usuario.getHorasGastadas());

		salida.close();
	}

}
