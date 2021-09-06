package usuario;

import java.util.LinkedList;
import java.util.List;

import producto.*;

public class Usuario {

	private String nombre;
	// private double presupuestoInicial;
	private double horasDisponibles;
	private TipoDeAtraccion tipoFavorito;
	private double totalAPagar;
	private double monedasDisponibles;
	private double horasGastadas;
	private List<Producto> productosComprados;
	// private List<Atraccion> atraccionesElectas;

	public Usuario(String nombre, double presupuesto, double horasDisponibles, TipoDeAtraccion tipoFavorito) {
		this.nombre = nombre;
		// this.presupuestoInicial = presupuesto;
		this.horasDisponibles = horasDisponibles;
		this.tipoFavorito = tipoFavorito;
		this.productosComprados = new LinkedList<Producto>();

		this.monedasDisponibles = presupuesto;
	}

	public double getHorasDisponibles() {
		return horasDisponibles;
	}

	public double getMonedasDisponibles() {
		return monedasDisponibles;
	}

	public double getTotalAPagar() {
		return totalAPagar;
	}

	public TipoDeAtraccion getTipoFavorito() {
		return tipoFavorito;
	}

	public String getNombre() {
		return this.nombre;
	}

	private double descontarMonedas(Producto producto) {
		// No verificamos si el importe a pagar es mayor a sus monedas disponibles
		// porque
		// solo la invocamos si se cumple el puedeComprar
		this.monedasDisponibles -= producto.getCosto();
		this.totalAPagar += producto.getCosto();
		return monedasDisponibles;
	}

	private void descontarHorasDisponibles(Producto producto) {
		horasDisponibles -= producto.getDuracion();
		horasGastadas += producto.getDuracion();
	}

	public boolean puedeComprar(Producto producto) {
		return (getHorasDisponibles() >= producto.getDuracion()) && (getMonedasDisponibles() >= producto.getCosto());
	}

	/*
	 * public boolean esAtraccionYaElegida(Producto producto) { 
	 * Si es Atraccion -> Verifica si se eligio en la lista de Atracciones
	 * Si es Promocion -> Verifica cada atraccionInvolucrada en la lista de Atracciones
	 */

	public void comprarProducto(Producto producto) {

		if (puedeComprar(producto)) {
			descontarMonedas(producto);
			descontarHorasDisponibles(producto);
			
			productosComprados.add(producto);
			
			/* Si era una atraccion -> Agrega la atraccion a una nueva lista
			 * Si era una Promocion -> Agrega cada elemento de la lista de AtraccionesInvolucradas a la nueva lista
			 * 
			 * 
			 * 
			 */
		}
	}

	public List<Producto> getProductosComprados() {
		return productosComprados;
	}

	public double getHorasGastadas() {
		return horasGastadas;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", horasDisponibles=" + horasDisponibles + ", tipoFavorito=" + tipoFavorito
				+ ", totalAPagar=" + totalAPagar + ", monedasDisponibles=" + monedasDisponibles + ", horasGastadas="
				+ horasGastadas + ", productosComprados=" + productosComprados + "]";
	}

}
