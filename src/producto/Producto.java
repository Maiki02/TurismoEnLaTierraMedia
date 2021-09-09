package producto;

import java.util.List;

public abstract class Producto {
	protected String nombre;
	protected TipoDeAtraccion tipoAtraccion;
	protected double duracion;
	protected double costo;
	protected List<Atraccion> atracciones;

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

	public List<Atraccion> getAtracciones(){
		return this.atracciones;
	}
	
	/*
	 * @Pre:
	 * @Post: retorna true en caso de que la atraccion tenga cupos disponibles (caso contrario false)
	 */
	public boolean esPromocion() {
		return false;
	}

	@Override
	public String toString() {
		return "Producto: "+ nombre + "," + tipoAtraccion + "," + duracion + ","+ costo;
	}
	
	

}
