package producto;

import java.util.List;

import excepciones.AtraccionDeDistintoTipo;
import excepciones.AtraccionInexistente;
import usuario.Usuario;

public abstract class Promocion extends Producto {
	List<Atraccion> atracciones;

	public Promocion(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones)
			throws AtraccionDeDistintoTipo {
		super(nombre, tipoAtraccion);
		setAtracciones(atracciones, tipoAtraccion);
		this.costo = calcularCosto();
		this.duracion = calcularDuracion();
	}

	private void setAtracciones(List<Atraccion> atracciones, TipoDeAtraccion tipoAtraccion)
			throws AtraccionDeDistintoTipo {
		if (!sonAtraccionesValidas(atracciones, tipoAtraccion)) {
			throw new AtraccionDeDistintoTipo("Hay atracciones que no son del tipo: " + tipoAtraccion);
		}
		this.atracciones = atracciones;
	}

	private static boolean sonAtraccionesValidas(List<Atraccion> atracciones, TipoDeAtraccion tipoAtraccion) {
		for (Atraccion atraccion : atracciones) {
			if (!esAtraccionValida(atraccion, tipoAtraccion)) // Si es una atraccion invalida devuelve false
				return false;
		}
		return true;
	}

	private static boolean esAtraccionValida(Atraccion atraccion, TipoDeAtraccion tipoAtraccion) {
		return atraccion.getTipoAtraccion() == tipoAtraccion;
	}

	// Getters:
	public List<Atraccion> getAtracciones() {
		return this.atracciones;
	}

	// ----------------------------------------

	// Calcular duracion y costo:
	public double calcularCosto() {
		double costo = 0;
		for (Atraccion atraccion : atracciones)
			costo += atraccion.getCosto();

		return costo;
	}

	public double calcularDuracion() {
		double duracion = 0;
		for (Atraccion atraccion : atracciones)
			duracion += atraccion.getDuracion();

		return duracion;
	}

	// ---------------------------------------

	@Override
	public boolean esPromocion() {
		return true;
	}

	public boolean quedanCuposDisponibles() {
		for (Atraccion atraccion : atracciones) {
			if (!atraccion.quedanCuposDisponibles())
				return false;
		}
		return true;
	}

	@Override
	public void agregarAtracciones(Usuario usuario) {
		for (Atraccion atraccion : this.atracciones) {
			usuario.getAtraccionesElectas().add(atraccion);
			atraccion.ocuparAtraccion();
		}
	}

	@Override
	public boolean esProductoYaElecto(Usuario usuario) {
		for (Atraccion atraccionAComprar : this.atracciones) {
			if (usuario.getAtraccionesElectas().contains(atraccionAComprar))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Promocion:" + super.toString();
	}

}
