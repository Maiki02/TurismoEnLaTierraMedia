package producto;

public abstract class Producto {
	protected String nombre;
	protected TipoDeAtraccion tipoAtraccion;
	protected double duracion;
	protected double costo;

	public Producto(String nombre, TipoDeAtraccion tipoAtraccion, double duracion, double costo) {
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
		this.duracion = duracion;
		this.costo = costo;
	}
	
	//Getters: retornan sus atributos
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
	
	//-------------------------------------------
	
	/*
	 * @Pre:
	 * @Post: retorna true en caso de que la atraccion tenga cupos disponibles (caso contrario false)
	 */
	public boolean esPromocion() {
		return false;
	}
	
	public abstract boolean quedanCuposDisponibles();

	@Override
	public String toString() {
		return nombre + " Tipo:" + this.tipoAtraccion + " Precio:" + getCosto() + " Horas:" + getDuracion();
	}
	
	

}
