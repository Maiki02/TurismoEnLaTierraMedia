package DAO;

import java.sql.SQLException;
import java.util.List;

import producto.Atraccion;
import producto.Promocion;
import usuario.*;

public interface iUsuarioDAO  {
    
	public List<Usuario> listarUsuarios(List<Atraccion> atracciones, List<Promocion> promociones) throws SQLException;

	public int actualizar(Usuario usuario) throws SQLException;
}