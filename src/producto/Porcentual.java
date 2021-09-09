package producto;


import java.util.List;

public class Porcentual extends Promocion {

	private double porcentajeDescuento;

	public Porcentual(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones,
			double porcentajeDescuento) {
		super(nombre, tipoAtraccion, atracciones);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	//Getters:
	@Override
	public double getCosto() { //Representa el importe a pagar para comprar la promocion
		return super.getCosto() - this.calcularDescuento();
	}

	private double calcularDescuento() {
		return super.getCosto() * this.porcentajeDescuento / 100;
	}

	
}