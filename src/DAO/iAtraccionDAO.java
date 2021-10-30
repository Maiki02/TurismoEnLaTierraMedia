package DAO;

import java.util.List;

import producto.Atraccion;


public interface iAtraccionDAO {
	public List<Atraccion> listar();

	public int actualizar(Atraccion atraccion);

}
