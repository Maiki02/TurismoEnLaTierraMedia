package producto;

public class Atraccion extends Producto{
	
	private int cuposDisponibles;

	public Atraccion(String nombre, double costo, double duracion, int cupo, TipoDeAtraccion tipoAtraccion) {	
		super(nombre, tipoAtraccion, duracion, costo);
		this.cuposDisponibles = cupo;
	}
	
	//Getters
	public int getCuposDisponibles() { //Creo que no usamos este metodo
		return cuposDisponibles;
	}

	/*
	 * @Pre:
	 * @Post: retorna true en caso de que la atraccion tenga cupos disponibles (caso contrario false)
	 */
	@Override
	public boolean quedanCuposDisponibles() {
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
	 * @Post: retorna la cantidad de cupos disponibles que tiene la atraccion
	 */
	
	@Override
	public String toString() {
		return "Atraccion:" + super.toString();
	}
	
	

}
