package producto;


import java.util.ArrayList;


public class Absoluta extends Promocion{
	
	private double valorDeBeneficio;

	public Absoluta(String nombre, TipoDeAtraccion tipoAtraccion, ArrayList<Atraccion> atracciones,
			double valorDeBeneficio) {
		super(nombre, tipoAtraccion, atracciones);
		this.valorDeBeneficio = valorDeBeneficio;
	}
	
	public double importeAPagar() {
		double importeAPagar = this.getCosto() - this.valorDeBeneficio;
		
		return importeAPagar;
	}

}