package producto;

import java.util.ArrayList;
import java.util.List;

public class Promocion extends Producto {

	protected List<Atraccion> atracciones;

	public Promocion(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones) {
		// Preguntar al profe
		super(nombre, tipoAtraccion, 0, 0);
		this.atracciones = atracciones;
	}

	@Override
	public double getCosto() {
		double costo = 0;

		for (Atraccion atraccion : atracciones)
			costo += atraccion.getCosto();
		
		return costo;
	}

	@Override
	public double getDuracion() {

		double duracion = 0;

		for (Atraccion atraccion : atracciones) {
			duracion += atraccion.getDuracion();

		}
		return duracion;
	}

	public double importeAPagar() {
		return getCosto();
	}

}
