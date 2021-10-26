package DAO;

import java.sql.SQLException;
import java.util.List;

import producto.*;

public interface PromocionDAO extends GenericDAO<Promocion> {
	public List<Promocion> listarPromocionesValidas(List<Atraccion> atracciones) throws SQLException;

	public int insertar(Promocion promocion, TipoDePromocion tipoPromocion) throws SQLException;
}
