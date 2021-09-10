package producto;

import usuario.Usuario;

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

	@Override
	public void agregarAtracciones(Usuario usuario) {
		usuario.getAtraccionesElectas().add(this);
		this.ocuparAtraccion();
	}
	
	@Override
	public boolean esProductoYaElecto(Usuario usuario) {
		return usuario.getAtraccionesElectas().contains(this);
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
