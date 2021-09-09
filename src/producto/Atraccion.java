package producto;

import java.util.Objects;

public class Atraccion extends Producto{
	
	private int cuposDisponibles;
	public boolean ocuparAtraccion;
	private int cupoInicial;

	public Atraccion(String nombre, double costo, double duracion, int cupo, TipoDeAtraccion tipoAtraccion) {
		super(nombre, tipoAtraccion, duracion, costo);
		this.cupoInicial = cupo;
		this.cuposDisponibles = cupo;
	}
	
	/*
	 * @Pre:
	 * @Post: retorna true en caso de que la atraccion tenga cupos disponibles (caso contrario false)
	 */
	private boolean quedanCuposDisponibles() {
		return cuposDisponibles > 0;
	}

	/*
	 * @Pre:
	 * @Post: retorna true en caso de que la atraccion pueda ocuparse
	 */
	public boolean ocuparAtraccion() {
		if (this.quedanCuposDisponibles()) {
			cuposDisponibles--;
			return true;
		}
		return false;
	}

	/*
	 * @Pre:
	 * @Post: retorna la cantidad de cupos maximos que puede tener la atraccion
	 */
	public int getCuposMaximos() {
		return cupoInicial;
	}

	@Override
	public String toString() {
		return super.toString() + "," + cuposDisponibles;
	}
	
	

}
