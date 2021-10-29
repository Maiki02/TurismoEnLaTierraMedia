package DAO;

import java.sql.SQLException;
import java.util.List;

import excepciones.AtraccionDeDistintoTipo;
import excepciones.ValorNegativo;
import producto.*;

public interface iPromocionDAO {

	public abstract List<Promocion> listarPromocionesValidas(List<Atraccion> atracciones) throws SQLException, AtraccionDeDistintoTipo, ValorNegativo;

}
