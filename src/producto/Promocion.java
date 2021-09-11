package producto;

import java.util.List;

import usuario.Usuario;

public class Promocion extends Producto {
//Preguntar al profe abstract
	List<Atraccion> atracciones;
	
	public Promocion(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones) {
		super(nombre, tipoAtraccion);
		this.atracciones = atracciones;
		this.costo=calcularCosto();
		this.duracion=calcularDuracion();
	}

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
	
	public List<Atraccion> getAtracciones(){
		return this.atracciones;
	}
	
	//---------------------------------------
	
	@Override
	public boolean esPromocion() {
		return true;
	}

	public boolean quedanCuposDisponibles() {
		for(Atraccion atraccion: atracciones) {
			if( !atraccion.quedanCuposDisponibles() ) return false;
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
