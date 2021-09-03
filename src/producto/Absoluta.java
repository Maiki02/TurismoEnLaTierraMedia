package producto;


import java.util.ArrayList;
import java.util.List;


public class Absoluta extends Promocion{
	
	private double valorDeBeneficio;

	public Absoluta(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones,
			double valorDeBeneficio) {
		super(nombre, tipoAtraccion, atracciones);
		this.valorDeBeneficio = valorDeBeneficio;
	}
	
	public double importeAPagar() {
		return this.valorDeBeneficio;
	}

}