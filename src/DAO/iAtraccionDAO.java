package DAO;

import java.sql.SQLException;
import java.util.List;

import producto.Atraccion;


public interface iAtraccionDAO {
	public List<Atraccion> listar() throws SQLException;

	public int actualizar(Atraccion atraccion) throws SQLException;

}
