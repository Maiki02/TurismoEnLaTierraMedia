package producto;

import excepciones.ValorNegativo;
import usuario.Usuario;

public class Atraccion extends Producto {

	private int cuposDisponibles;

	public Atraccion(String nombre, double costo, double duracion, int cupo, TipoDeAtraccion tipoAtraccion)
			throws ValorNegativo {
		super(nombre, tipoAtraccion, duracion, costo);
		setCupo(cupo);
	}
	
	//Setters
	/*
	 * @Pre: dado un valor que representa un cupo
	 * @Post: establece ese valor al atributo de cuposDisponibles en caso de que no sea negativo
	 */
	private void setCupo(int cupo) throws ValorNegativo {
		super.verificarValor(cupo);
		this.cuposDisponibles = cupo;

	}

	// Getters
	public int getCuposDisponibles() {
		return cuposDisponibles;
	}

	/*
	 * @Pre:
	 * 
	 * @Returns: retorna true en caso de que la atraccion tenga cupos disponibles (caso
	 * contrario false)
	 */
	@Override
	public boolean quedanCuposDisponibles() {
		return cuposDisponibles > 0;
	}

	/*
	 * @Pre:
	 * @Post: si quedan lugares, la atraccion descuenta un cupo
	 * @Return: retorna true en caso de que la atraccion pueda ocuparse
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

	/*
	 * @Pre:
	 * @Post: agrega la atraccion a la lista de atracciones electas del usuario
	 * @Return: retorna true en caso de que se pueda agregar.
	 */
	@Override
	public boolean esProductoYaElecto(Usuario usuario) {
		return usuario.getAtraccionesElectas().contains(this);
	}

	@Override
	public String toString() {
		return "Atraccion:" + super.toString();
	}

	@Override
	public boolean contiene(Producto producto) {
		if(producto.esPromocion()) {
			return producto.contiene(this);
		}
		return this.equals(producto);
	}
}
