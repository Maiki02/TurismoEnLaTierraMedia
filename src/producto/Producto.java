package producto;

public abstract class Producto {
	private String nombre;
	private TipoDeAtraccion tipoAtraccion;
	private double duracion;
	private double costo;

	public Producto(String nombre, TipoDeAtraccion tipoAtraccion, double duracion, double costo) {
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
		this.duracion = duracion;
		this.costo = costo;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	protected double getCosto() {
		return this.costo;
	}

	protected double getDuracion() {
		return this.duracion;
	}

}
