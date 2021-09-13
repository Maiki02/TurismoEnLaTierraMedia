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
	/*
	 * @Pre:
	 * @Post: agrega la atraccion a la lista de atracciones elegidas del ususario y resta un cupo a la atraccion
	 */
	@Override
	public void agregarAtracciones(Usuario usuario) {
		usuario.getAtraccionesElectas().add(this);
		this.ocuparAtraccion();
	}
	/*
	 * @Pre:
	 * @Post: devuelve true si el producto ya fue vendido
	 */
	@Override
	public boolean esProductoYaElecto(Usuario usuario) {
		return usuario.getAtraccionesElectas().contains(this);
	}
	
	@Override
	public String toString() {
		return "Atraccion:" + super.toString();
	}
	
	

}
