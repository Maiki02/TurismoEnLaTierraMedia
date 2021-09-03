package usuario;

import java.util.LinkedList;
import java.util.List;

import producto.*;

public class Usuario {

	private String nombre;
	@SuppressWarnings("unused")
	private double presupuestoInicial;
	private double horasDisponibles;
	private TipoDeAtraccion tipoFavorito;
	private double totalAPagar;
	private double monedasDisponibles;
	private List<Producto> productosComprados;
	//private List<Atraccion> atraccionesElectas;

	public Usuario(String nombre, double presupuesto, double horasDisponibles, TipoDeAtraccion tipoFavorito) {
		this.nombre = nombre;
		this.presupuestoInicial = presupuesto;
		this.horasDisponibles = horasDisponibles;
		this.tipoFavorito=tipoFavorito;
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
	
	public double descontarMonedas(Producto producto) {
		//No verificamos si el importe a pagar es mayor a sus monedas disponibles porque
		//solo la invocamos si se cumple el puedeComprar
		this.monedasDisponibles -= producto.getCosto();
		this.totalAPagar+=producto.getCosto();
		return monedasDisponibles;
	}

	public double descontarHorasDisponibles(Producto producto) {
		horasDisponibles -= producto.getDuracion();
		return horasDisponibles;
	}

	public boolean puedeComprar(Producto producto) {
		return (getHorasDisponibles() >= producto.getDuracion()) && (getMonedasDisponibles() >= producto.getCosto());
	}

	 /*public boolean esAtraccionYaElegida(Atraccion atraccion) {
		 return atraccionesElectas.contains(atraccion);
	 }*/
	 

	public void comprarProducto(Producto producto) {
		
		if (puedeComprar(producto)) {
			descontarMonedas(producto);
			descontarHorasDisponibles(producto);
			productosComprados.add(producto);
			
		}
	}

}
