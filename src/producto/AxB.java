package producto;

import java.util.List;

public class AxB extends Promocion {

	private Atraccion premio;

	public AxB(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones, Atraccion premio) {
		super(nombre, tipoAtraccion, atracciones);
		System.out.println("Constructor AXB");
		this.premio = premio;
	}
	
	@Override
	public double getCosto() {
		return super.getCosto() - premio.getCosto();
	}
	
}