package producto;

import java.util.List;

import excepciones.AtraccionDeDistintoTipo;
import excepciones.ValorNegativo;

public class Absoluta extends Promocion {

	private double valorDeBeneficio;

	public Absoluta(String nombre, TipoDeAtraccion tipoAtraccion, List<Atraccion> atracciones, 
			double valorDeBeneficio) throws ValorNegativo, AtraccionDeDistintoTipo {
		super(nombre, tipoAtraccion, atracciones);
		setValorDeBeneficio(valorDeBeneficio);
	}

	private void setValorDeBeneficio(double valorDeBeneficio) throws ValorNegativo {
		super.verificarValor(valorDeBeneficio);
		this.valorDeBeneficio = valorDeBeneficio;
	}

	@Override
	public double getCosto() {
		return this.valorDeBeneficio;
	}

}