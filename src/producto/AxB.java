package producto;

import java.util.List;

import excepciones.AtraccionDeDistintoTipo;

public class AxB extends Promocion {

	private Atraccion premio;

	public AxB(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones, 
			Atraccion premio, int id) throws AtraccionDeDistintoTipo {
		super(nombre, tipoAtraccion, atracciones, id);
		this.premio = premio; //El premio está contenido en la lista de atracciones
		//Por lo tanto si es de distinto tipo, será verificado en su padre.
	}

	@Override
	public double getCosto() {
		return super.getCosto() - premio.getCosto();
	}

}