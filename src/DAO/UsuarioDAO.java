package DAO;

import java.sql.SQLException;
import java.util.List;

import producto.Producto;
import usuario.*;

public interface UsuarioDAO extends GenericDAO<Usuario> {
    
	public List<Usuario> listarUsuarios() throws SQLException;

	public int insertar(Usuario usuario) throws SQLException;

	public int actualizar(Usuario usuario) throws SQLException;

	public int eliminar(Usuario usuario) throws SQLException;
}
