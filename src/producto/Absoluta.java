package producto;

import java.util.List;

import excepciones.AtraccionDeDistintoTipo;
import excepciones.ValorNegativo;

public class Absoluta extends Promocion {

	private double valorDeBeneficio;

	public Absoluta(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones, 
			double valorDeBeneficio, int id) throws ValorNegativo, AtraccionDeDistintoTipo {
		super(nombre, tipoAtraccion, atracciones, id);
		setValorDeBeneficio(valorDeBeneficio);
	}

	/*
	 * @Pre: dado un valor que representa el costo final del paquete
	 * @Post: será asignado en caso de que este valor NO sea negativo.
	 */
	private void setValorDeBeneficio(double valorDeBeneficio) throws ValorNegativo {
		super.verificarValor(valorDeBeneficio);
		this.valorDeBeneficio = valorDeBeneficio;
	}

	@Override
	public double getCosto() {
		return this.valorDeBeneficio;
	}

}