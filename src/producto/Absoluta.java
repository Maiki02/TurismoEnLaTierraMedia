package producto;

import java.util.List;


public class Absoluta extends Promocion{
	
	private double valorDeBeneficio;

	public Absoluta(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones,
			double valorDeBeneficio) {
		super(nombre, tipoAtraccion, atracciones);
		this.valorDeBeneficio = valorDeBeneficio;
	}
	
	@Override
	public double importeAPagar() {
		return this.valorDeBeneficio;
	}

}