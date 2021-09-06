package producto;

public abstract class Producto {
	protected String nombre;
	protected TipoDeAtraccion tipoAtraccion;
	protected double duracion;
	protected double costo;

	public Producto(String nombre, TipoDeAtraccion tipoAtraccion, double duracion, double costo) {
		this.nombre = nombre.toUpperCase();
		this.tipoAtraccion = tipoAtraccion;
		this.duracion = duracion;
		this.costo = costo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public double getCosto() {
		return this.costo;
	}

	public double getDuracion() {
		return this.duracion;
	}

	public TipoDeAtraccion getTipoAtraccion() {
		return tipoAtraccion;
	}

	public boolean esPromocion() {
		return false;
	}

	@Override
	public String toString() {
		return "Producto: "+ nombre + "," + tipoAtraccion + "," + duracion + ","+ costo;
	}
	
	

}
