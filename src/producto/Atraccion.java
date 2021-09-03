package producto;

import java.util.Objects;

public class Atraccion extends Producto implements Comparable<Atraccion> {
	
	private int cuposDisponibles;
	public boolean ocuparAtraccion;
	private int cupoInicial;

	public Atraccion(String nombre, double costo, double duracion, int cupo, TipoDeAtraccion tipoAtraccion) {

		super(nombre, tipoAtraccion, duracion, costo);
		this.cupoInicial = cupo;
		this.cuposDisponibles = cupo;
	}

	private boolean quedanCuposDisponibles() {
		return cuposDisponibles > 0;
	}

	public boolean ocuparAtraccion() {
		if (this.quedanCuposDisponibles()) {
			cuposDisponibles--;
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Atraccion o) {
		return 0;
	}

	public int getCuposMaximos() {
		return cupoInicial;
	}

	@Override
	public String toString() {
		return "Atraccion [nombre=" + super.getNombre() + ", TipoDeAtraccion: " + super.getTipoAtraccion() + 
				", Duracion: " + super.getDuracion() + ", Costo: " + super.getCosto() + ", cuposDisponibles=" + 
				cuposDisponibles + ", ocuparAtraccion=" + ocuparAtraccion + ", cupoInicial=" + cupoInicial + "]";
	}
	
	

}
