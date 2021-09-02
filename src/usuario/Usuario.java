package usuario;

import producto.Atraccion;
import producto.Producto;
import producto.Promocion;
import producto.TipoDeAtraccion;

public class Usuario {

	private String nombre;
	private double presupuesto;
	private double horasDisponibles;
	private double totalAPagar;
	private double monedasDisponibles;
	private Producto productoComprado[];
	private int cantidad_productosComprados;

	public Usuario(String nombre, double presupuesto, double horasDisponibles, double totalAPagar,
			double monedasDisponibles, int cant_productosComprados) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.horasDisponibles = horasDisponibles;
		this.totalAPagar = totalAPagar;
		this.monedasDisponibles = presupuesto;
		this.cantidad_productosComprados = 0;
		this.productoComprado = new Producto[20];
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

	public double descontarMonedas(Producto producto) {
		monedasDisponibles -= producto.getCosto();
		return monedasDisponibles;
	}

	public double descontarHorasDisponibles(Producto producto) {
		horasDisponibles -= producto.getDuracion();
		return horasDisponibles;
	}

	public boolean puedeComprar(Producto producto) {
		return (getHorasDisponibles() >= producto.getDuracion()) && (getMonedasDisponibles() >= producto.getCosto());
	}

	 /*public boolean esAtraccionYaElegida() {
		 
	 }*/
	 

	public void comprarProducto(Producto producto) {
		
		if (puedeComprar(producto)) {
			descontarMonedas(producto);
			descontarHorasDisponibles(producto);
				productoComprado[cantidad_productosComprados] = producto;
				cantidad_productosComprados++;
		}
	}
}
