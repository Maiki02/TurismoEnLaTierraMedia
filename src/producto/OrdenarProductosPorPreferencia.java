package producto;

import java.util.Comparator;

public class OrdenarProductosPorPreferencia implements Comparator<Producto> {

	private TipoDeAtraccion preferenciaUsuario;

	public OrdenarProductosPorPreferencia(TipoDeAtraccion tipoPreferidoUsuario) {
		preferenciaUsuario = tipoPreferidoUsuario;
	}

	@Override
	public int compare(Producto o1, Producto o2) {
		if (o1.getTipoAtraccion() == this.preferenciaUsuario && o2.getTipoAtraccion() == this.preferenciaUsuario) {
			// ambas son preferidas, compara por lo siguiente (si son promo o no)
			if (compararSiAmbasSonPromo(o1, o2) == 0) {
				// ambas son promo, compara por el costo
				return compararPorCostoOduracion(o1, o2);
			} else {
				return compararSiAmbasSonPromo(o1, o2);
			}
		} else if (o1.getTipoAtraccion() != this.preferenciaUsuario
				&& o2.getTipoAtraccion() != this.preferenciaUsuario) {
			if (o1.esPromocion() && o2.esPromocion()) {
				return compararPorCostoOduracion(o1, o2);
			} else if (!o1.esPromocion() && !o2.esPromocion()) {
				return compararPorCostoOduracion(o1, o2);
			} else {
				return compararSiAmbasSonPromo(o1, o2);
			}
		} else {
			// una es preferida y la otra no
			if (o1.getTipoAtraccion() == this.preferenciaUsuario)
				return -1;
			return 1;
		}
	}

	private int compararSiAmbasSonPromo(Producto o1, Producto o2) {
		return -Boolean.compare(o1.esPromocion(), o2.esPromocion());
	}

	private int compararPorCostoOduracion(Producto o1, Producto o2) {
		if (compararPorCosto(o1,o2) == 0) {
			// si tienene el mismo costo, comparo por tiempo
			return compararPorDuracion(o1,o2);
		} else {
			return compararPorCosto(o1,o2);
		}
	}
	
	private int compararPorCosto(Producto o1, Producto o2) {
		return -Double.compare(o1.getCosto(), o2.getCosto());
	}
	
	private int compararPorDuracion(Producto o1, Producto o2) {
		return -Double.compare(o1.getDuracion(), o2.getDuracion());
	}

}
