package producto;

import usuario.Usuario;

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
	public Producto(String nombre, TipoDeAtraccion tipoAtraccion) {
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
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
	
	/*
	 * @pre
	 * @post:
	 * @returns:
	 */
	public abstract boolean quedanCuposDisponibles();
	public abstract void agregarAtracciones(Usuario usuario);
	public abstract boolean esProductoYaElecto(Usuario usuario);

	@Override
	public String toString() {
		return nombre + " Tipo:" + this.tipoAtraccion + " Precio:" + getCosto() + " Horas:" + getDuracion();
	}
	
	

}
