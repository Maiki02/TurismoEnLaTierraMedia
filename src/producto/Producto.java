package producto;

import java.util.Objects;

import excepciones.ValorNegativo;
import usuario.Usuario;

public abstract class Producto {
	protected String nombre;
	protected TipoDeAtraccion tipoAtraccion;
	protected double duracion;
	protected double costo;

	public Producto(String nombre, TipoDeAtraccion tipoAtraccion, double duracion, double costo) throws ValorNegativo {
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
		setDuracion(duracion);
		setCosto(costo);
	}

	public Producto(String nombre, TipoDeAtraccion tipoAtraccion) {
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
	}

	// Setters:
	/*
	 * @Pre: dado un valor que representa un costo
	 * @Post: establece ese valor al atributo de costo en caso de que no sea negativo
	 */
	private void setCosto(double costo) throws ValorNegativo {
		verificarValor(costo);
		this.costo = costo;
	}

	/*
	 * @Pre: dado un valor que representa la duracion de un producto
	 * @Post: establece ese valor al atributo de duracion en caso de que no sea negativo
	 */
	private void setDuracion(double duracion) throws ValorNegativo {
		verificarValor(duracion);
		this.duracion = duracion;
	}

	protected void verificarValor(double valor) throws ValorNegativo {
		if (valor < 0) {
			throw new ValorNegativo("Fue pasado un valor negativo");
		}
	}

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

	// -------------------------------------------

	public boolean esPromocion() {
		return false;
	}

	public abstract boolean quedanCuposDisponibles();

	public abstract void agregarAtracciones(Usuario usuario);

	public abstract boolean esProductoYaElecto(Usuario usuario);

	@Override
	public String toString() {
		return nombre + " Tipo:" + this.tipoAtraccion + " Precio:" + getCosto() + " Horas:" + getDuracion();
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, duracion, nombre, tipoAtraccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo)
				&& Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
				&& Objects.equals(nombre, other.nombre) && tipoAtraccion == other.tipoAtraccion;
	}

}
