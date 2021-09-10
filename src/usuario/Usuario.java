package usuario;

import java.util.LinkedList;
import java.util.List;

import producto.*;

public class Usuario {

	private String nombre;
	private double monedasDisponibles;
	private double horasDisponibles;
	private TipoDeAtraccion tipoFavorito;
	private double totalAPagar;
	private double totalHorasGastadas; //Horas a jugar
	private List<Producto> productosComprados;
	private List<Atraccion> atraccionesElectas;

	public Usuario(String nombre, double presupuesto, double horasDisponibles, TipoDeAtraccion tipoFavorito) {
		this.nombre = nombre;
		// this.presupuestoInicial = presupuesto;
		this.horasDisponibles = horasDisponibles;
		this.tipoFavorito = tipoFavorito;
		this.productosComprados = new LinkedList<Producto>();
		this.atraccionesElectas= new LinkedList<Atraccion>();
		this.monedasDisponibles = presupuesto;
	}

	//Getters:
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
	
	public List<Atraccion> getAtraccionesElectas(){
		return this.atraccionesElectas;
	}
	//-----------------------------------------------------

	private void descontarMonedas(Producto producto) {
		// No verificamos si el importe a pagar es mayor a sus monedas disponibles
		// porque solo la invocamos si se cumple el puedeComprar
		this.monedasDisponibles -= producto.getCosto();
		this.totalAPagar += producto.getCosto();
	}

	private void descontarHorasDisponibles(Producto producto) {
		horasDisponibles -= producto.getDuracion();
		totalHorasGastadas += producto.getDuracion();
	}

	private boolean leAlcanzanLasMonedas(Producto producto) {
		return this.monedasDisponibles >= producto.getCosto();
	}

	private boolean leAlcanzanLasHoras(Producto producto) {
		return this.horasDisponibles >= producto.getDuracion();
	}

	public boolean puedeComprar(Producto producto) {
		return leAlcanzanLasMonedas(producto) && leAlcanzanLasHoras(producto) && producto.quedanCuposDisponibles();
	}

	public boolean esProductoYaElecto(Producto producto) {
		if (producto instanceof Atraccion) { //Si es atraccion, buscamos en su lista de atracciones electas si está
			Atraccion atr = (Atraccion) producto;
			return atraccionesElectas.contains(atr);
		} else if (producto instanceof Promocion) { //Si es promocion, buscamos en su lista de atracciones involucradas
			
			Promocion prom = (Promocion) producto; //si es que cada atraccion ya se eligio.
			
			for (Atraccion atraccionAComprar : prom.getAtracciones())
				if (atraccionesElectas.contains(atraccionAComprar)) return true;
		}
		return false;
	}

	
	public void comprarProducto(Producto producto) {

		if (puedeComprar(producto)) {
			descontarMonedas(producto);
			descontarHorasDisponibles(producto);
			
			productosComprados.add(producto); //Lo agregamos a lista de productos comprados
			producto.agregarAtracciones(this);
		}
	}

	@Override
	public String toString() {
		return "Usuario: " + nombre + " Tipo favorito: " + this.tipoFavorito;
	}

}
