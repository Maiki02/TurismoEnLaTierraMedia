package usuario;

import java.util.LinkedList;
import java.util.List;

import excepciones.ValorNegativo;
import producto.*;

public class Usuario {

	private int id;
	private String nombre;
	private double monedasDisponibles;
	private double horasDisponibles;
	private TipoDeAtraccion tipoFavorito;
	private double totalAPagar;
	private double totalHorasGastadas; // Horas a jugar
	private List<Producto> productosComprados;

	public Usuario(String nombre, double presupuesto, double horasDisponibles, TipoDeAtraccion tipoFavorito, int id)
			throws ValorNegativo {
		this.id = id;
		this.nombre = nombre;
		setHorasDisponibles(horasDisponibles);
		setMonedasDisponibles(presupuesto);
		this.tipoFavorito = tipoFavorito;
		this.productosComprados = new LinkedList<Producto>();

	}
	
	public Usuario(int idUsuario, String nombre, double monedasDisponibles, double horasDisponibles, 
			TipoDeAtraccion tipoFavorito, double totalAPagar, double totalHorasGastadas, List<Producto> productosComprados) {
		this.id = idUsuario;
		this.nombre = nombre;
		setHorasDisponibles(horasDisponibles);
		setMonedasDisponibles(monedasDisponibles);
		this.tipoFavorito = tipoFavorito;
		this.totalAPagar=totalAPagar;
		this.totalHorasGastadas= totalHorasGastadas;
		this.productosComprados = productosComprados;
	}

	// Setters:
	private void setMonedasDisponibles(double presupuesto) throws ValorNegativo {
		ValorNegativo.verificarValor(presupuesto);
		this.monedasDisponibles = presupuesto;

	}

	private void setHorasDisponibles(double horasDisponibles) throws ValorNegativo {

		ValorNegativo.verificarValor(horasDisponibles);
		this.horasDisponibles = horasDisponibles;
	}

	// Getters:
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

	public List<Producto> getProductosComprados() {
		return productosComprados;
	}

	public double getHorasGastadas() {
		return totalHorasGastadas;
	}

	public int getID() {
		return this.id;
	}
	// -----------------------------------------------------

	private void descontarMonedas(Producto producto) {
		// No verificamos si el importe a pagar es mayor a sus monedas disponibles
		// porque solo la invocamos si se cumple el puedeComprar
		this.monedasDisponibles -= producto.getCosto();
		this.totalAPagar += producto.getCosto();
	}

	/*
	 * @Pre:
	 * 
	 * @Post: descuenta la cantidad de horas necesarias del producto
	 */
	private void descontarHorasDisponibles(Producto producto) {
		horasDisponibles -= producto.getDuracion();
		totalHorasGastadas += producto.getDuracion();
	}

	/*
	 * @Pre:
	 * 
	 * @Post: retorna true en caso de que el usuario cuente con monedas disponibles
	 */

	private boolean leAlcanzanLasMonedas(Producto producto) {
		return this.monedasDisponibles >= producto.getCosto();
	}

	/*
	 * @Pre:
	 * 
	 * @Post: retorna true en caso de que el usuario cuente con horas disponibles
	 */

	private boolean leAlcanzanLasHoras(Producto producto) {
		return this.horasDisponibles >= producto.getDuracion();
	}

	/*
	 * @Pre:
	 * 
	 * @Post: retorna true en caso de que el usuario cuente con monedas,horas y
	 * cupos disponibles
	 */
	public boolean puedeComprar(Producto producto) {
		return leAlcanzanLasMonedas(producto) && leAlcanzanLasHoras(producto) && producto.quedanCuposDisponibles();
	}

	/*
	 * @Pre:
	 * 
	 * @Post: Si puede comprar lo agregaa a la lista de productos comprados
	 */
	public void comprarProducto(Producto producto) {

		if (puedeComprar(producto)) {
			descontarMonedas(producto);
			descontarHorasDisponibles(producto);

			productosComprados.add(producto); // Lo agregamos a lista de productos comprados
			producto.ocuparAtraccion();
		}
	}

	@Override
	public String toString() {
		return "Usuario: " + nombre + " Tipo favorito: " + this.tipoFavorito + "\n Monedas: " + this.monedasDisponibles + " Horas: " + this.horasDisponibles;
	}

}
