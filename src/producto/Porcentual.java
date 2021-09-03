package producto;

import java.util.ArrayList;

public class Porcentual extends Promocion {

	private double porcentajeDescuento;

	public Porcentual(String nombre, TipoDeAtraccion tipoAtraccion, ArrayList<Atraccion> atracciones,
			double porcentajeDescuento) {
		super(nombre, tipoAtraccion, atracciones);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	private double calcularDescuento() {
		double descuentoCalculado = this.getCosto() * this.porcentajeDescuento / 100;
		return descuentoCalculado;
	}

	@Override
	public double importeAPagar() {
		double importeTotal = this.getCosto() - this.calcularDescuento();
		return importeTotal;

	}

}