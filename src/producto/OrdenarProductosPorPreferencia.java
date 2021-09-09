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
			if (o1.esPromocion() && o2.esPromocion()) {
				// ambas son promo, compara por el costo
				if (Double.compare(o1.getCosto(), o2.getCosto()) == 0) {
					// si tienene el mismo costo, comparo por tiempo
					return -Double.compare(o1.getDuracion(), o2.getDuracion());
				} else {
					return -Double.compare(o1.getCosto(), o2.getCosto());
				}
			} else {
				return -Boolean.compare(o1.esPromocion(), o2.esPromocion());
			}
		} else if (o1.getTipoAtraccion() != this.preferenciaUsuario
				&& o2.getTipoAtraccion() != this.preferenciaUsuario) {
			if (o1.esPromocion() && o2.esPromocion()) {
				// ambas son promos, entonces compara por el costo
				if (Double.compare(o1.getCosto(), o2.getCosto()) == 0) {
					// mismo costo, comparo por tiempo
					return -Double.compare(o1.getDuracion(), o2.getDuracion());
				} else {
					return -Double.compare(o1.getCosto(), o2.getCosto());
				}
			} else if (!o1.esPromocion() && !o2.esPromocion()) {
				// ninguna es promo, compara por costo
				if (Double.compare(o1.getCosto(), o2.getCosto()) == 0) {
					// mismo costo, comparo por tiempo finalmente
					return -Double.compare(o1.getDuracion(), o2.getDuracion());
				} else {
					return -Double.compare(o1.getCosto(), o2.getCosto());
				}
			} else {
				return -Boolean.compare(o1.esPromocion(), o2.esPromocion());
			}
		} else {
			// una es preferida y la otra no
			if (o1.getTipoAtraccion() == this.preferenciaUsuario)
				return -1;
			return 1;
		}
	}

}
