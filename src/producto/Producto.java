package producto;

public abstract class Producto {
	
	private String nombre;
	private TipoDeAtraccion tipoAtraccion;
	private double duracion;
	private double costo;
	
	protected double getCosto() {
		return this.costo;
	}
	
	protected double getDuracion() {
		return this.duracion;
	}
	
}
