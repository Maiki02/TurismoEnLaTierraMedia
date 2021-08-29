package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import producto.Atraccion;
import producto.TipoDeAtraccion;

public class ArchivoAtracciones {
	
	public static Atraccion[] leerArchivo() {

		
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader("atraccion.txt");
			br = new BufferedReader(fr);
			
			String linea;
			linea=br.readLine(); //Leemos el entero
			int cantidadAtracciones= Integer.parseInt(linea);
			int atraccionesRegistradas=0;
			
			Atraccion[] atracciones= new Atraccion[cantidadAtracciones];
			br.readLine(); //Leemos linea con caracteristicas

			while ((linea = br.readLine()) != null) {
				try {
					String[] caracteristicasPorAtraccion = linea.split(",");
					
					String nombre= caracteristicasPorAtraccion[0];
					double costo= Double.parseDouble(caracteristicasPorAtraccion[1]);
					double tiempo= Double.parseDouble(caracteristicasPorAtraccion[2]);
					int cupo= Integer.parseInt(caracteristicasPorAtraccion[3]);
					TipoDeAtraccion tipo= TipoDeAtraccion.valueOf(caracteristicasPorAtraccion[4]);
					
					if(costo < 0 || tiempo < 0 || cupo<=0) {
						throw new ValorNegativo;
					}
					
					atracciones[atraccionesRegistradas++] = new Atraccion(nombre, costo,tiempo,cupo,tipo);
					
				} catch (ValorNegativo ne) {
					
					System.err.println(rne.getMessage());
					
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leídos no es un double");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
