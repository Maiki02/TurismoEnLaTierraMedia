package producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.ValorNegativo;

public class Atraccion extends Producto {

	private int cuposDisponibles;

	public Atraccion(String nombre, double costo, double duracion, int cupo, TipoDeAtraccion tipoAtraccion, int id)
			throws ValorNegativo {
		super(nombre, tipoAtraccion, duracion, costo, id);
		setCupo(cupo);
	}

	// Setters

	private void setCupo(int cupo) throws ValorNegativo {
		ValorNegativo.verificarValor(cupo);
		this.cuposDisponibles = cupo;

	}

	// Getters
	public int getCuposDisponibles() { // Creo que no usamos este metodo
		return cuposDisponibles;
	}

	@Override
	public boolean quedanCuposDisponibles() {
		return cuposDisponibles > 0;
	}

	/*
	 * @Pre:
	 * 
	 * @Post: retorna true en caso de que la atraccion pueda ocuparse
	 */
	public boolean ocuparAtraccion() {
		if (this.quedanCuposDisponibles()) {
			cuposDisponibles--;
			return true;
		}
		return false;
	}

	/*
	 * @Pre:
	 * 
	 * @Post: retorna la cantidad de cupos disponibles que tiene la atraccion
	 */

	@Override
	public String toString() {
		return "Atraccion:" + super.toString();
	}

	public static Map<Integer, Atraccion> crearMapDeAtracciones(List<Atraccion> atracciones) {
		Map<Integer, Atraccion> mapaDeAtracciones = new HashMap<Integer, Atraccion>();

		for (Atraccion atraccion : atracciones) {
			mapaDeAtracciones.put(Integer.valueOf(atraccion.getID()), atraccion);
		}

		return mapaDeAtracciones;

	}

	@Override
	public boolean contiene(Producto producto) {
		if (producto.esPromocion()) {
			return producto.contiene(this);
		}
		return this.equals(producto);
	}

}