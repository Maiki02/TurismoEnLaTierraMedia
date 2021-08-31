package producto;

public class Atraccion extends Producto implements Comparable<Atraccion> {

	private int cuposDisponibles;
	public boolean ocuparAtraccion;
	private int cupo;

	public Atraccion(String nombre, TipoDeAtraccion tipoAtraccion, double duracion, double costo, int cupo) {

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
		System.out.println("No tiene más cupos");
		return false;
	}

	@Override
	public int compareTo(Atraccion o) {
		return 0;
	}

}
