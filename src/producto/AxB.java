package producto;

import java.util.ArrayList;
import java.util.List;

public class AxB extends Promocion {

	private Atraccion premio;

	public AxB(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones, Atraccion premio) {
		super(nombre, tipoAtraccion, atracciones);
		this.premio = premio;
	}

	public Atraccion devolverPremio() {
		return premio;
	}
	
	public double importeAPagar() {
		double importeTotal = this.getCosto() - premio.getCosto();
		return importeTotal;
	}
	
	public double cantidadDeHorasNecesarias() {
		double duracionTotal = super.getDuracion() + premio.getDuracion();
		return duracionTotal;
		
	}
	
}