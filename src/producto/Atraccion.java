package producto;

import java.util.Objects;

public class Atraccion extends Producto implements Comparable<Atraccion> {
	private String nombre;
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
		//System.out.println("No tiene mas cupos");
		return false;
	}

	@Override
	public int compareTo(Atraccion o) {
		return 0;
	}

	public String getNombre() {
		return nombre;
	}

	/*public void setNombre(String nombre) {
		this.nombre = nombre;
	}*/

	public int getCuposMaximos() {
		return cupoInicial;
	}

}
