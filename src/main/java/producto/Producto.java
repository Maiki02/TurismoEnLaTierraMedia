package producto;

import java.util.Iterator;

import excepciones.ValorNegativo;
import usuario.Usuario;

public abstract class Producto {
	protected String nombre;
	protected TipoDeAtraccion tipoAtraccion;
	protected double duracion;
	protected double costo;
	protected int id;

	public Producto(String nombre, TipoDeAtraccion tipoAtraccion, double duracion, double costo, int id)
			throws ValorNegativo {
		this.id = id;
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
		setDuracion(duracion);
		setCosto(costo);
	}

	public Producto(String nombre, TipoDeAtraccion tipoAtraccion, int id) {
		this.id = id;
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
	}

	// Setters:
	private void setCosto(double costo) throws ValorNegativo {
		ValorNegativo.verificarValor(costo);
		this.costo = costo;
	}

	private void setDuracion(double duracion) throws ValorNegativo {
		ValorNegativo.verificarValor(duracion);
		this.duracion = duracion;
	}

	//---------------------------------
	
	// Getters: retornan sus atributos
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

	public Integer getID() {
		return this.id;
	}

	// -------------------------------------------

	/*
	 * @Pre:
	 * 
	 * @Post: retorna true en caso de que la atraccion tenga cupos disponibles (caso
	 * contrario false)
	 */
	public boolean esPromocion() {
		return false;
	}

	public abstract boolean quedanCuposDisponibles();

	public abstract boolean ocuparAtraccion();

	@Override
	public String toString() {
		return nombre + " Tipo:" + this.tipoAtraccion + " Precio:" + getCosto() + " Horas:" + getDuracion();
	}

	public abstract boolean contiene(Producto producto);
	
	public boolean esProductoYaElecto(Usuario usuario) {
		Iterator<Producto> iter = usuario.getProductosComprados().iterator();
		boolean contiene = false;
		while (!contiene && iter.hasNext()) {
			contiene = this.contiene(iter.next());
		}
		return contiene;
	}

}
