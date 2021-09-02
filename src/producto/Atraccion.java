package producto;

import java.util.Objects;

public class Atraccion extends Producto implements Comparable<Atraccion> {
	private String nombre;
	private int cuposDisponibles;
	public boolean ocuparAtraccion;
	private int cupo;

	public Atraccion(String nombre, double costo, double duracion, int cupo, TipoDeAtraccion tipoAtraccion) {

		super(nombre, tipoAtraccion, duracion, costo);
		this.cupo = cupo;
		this.cuposDisponibles = cupo;
	}

	public boolean quedanCuposDisponibles() {
		return cuposDisponibles > 0;
	}

	public boolean ocuparAtraccion() {
		if (this.quedanCuposDisponibles()) {
			cuposDisponibles--;
			return true;
		}
		System.out.println("No tiene m�s cupos");
		return false;
	}

	@Override
	public int compareTo(Atraccion o) {
		return 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return Objects.equals(nombre, other.nombre);
	}

}
