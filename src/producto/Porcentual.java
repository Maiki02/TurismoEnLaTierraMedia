package producto;

import java.util.ArrayList;
import java.util.List;

public class Porcentual extends Promocion {

	private double porcentajeDescuento;

	public Porcentual(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones,
			double porcentajeDescuento) {
		super(nombre, tipoAtraccion, atracciones);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	private double calcularDescuento() {
		return super.getCosto() * this.porcentajeDescuento / 100;
	}

	@Override
	public double importeAPagar() {
		return super.getCosto() - this.calcularDescuento();
	}

}