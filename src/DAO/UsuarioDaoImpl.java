package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConexionBDD;
import producto.TipoDeAtraccion;
import usuario.Usuario;

public class UsuarioDaoImpl implements UsuarioDAO{
	private static final String SQL_LISTAR = "SELECT id_usuario, nombre_usuario, tiempo_usuario, monedas_usuario, id_tipo_atraccion_favorito, total_a_pagar, total_horas_gastadas" 
      + "FROM usuario";
	private static final String SQL_INSERTAR = "INSERT INTO usuarios nombre_usuario, tiempo_usuario, monedas_usuario, id_tipo_atraccion_favorito, total_a_pagar, total_horas_gastadas VALUES (?, ?, ?, ?, ?,?)";
	private static final String SQL_ACTUALIZAR ="UPDATE usuarios SET nombre_usuario = ?, tiempo_usuario = ?, monedas_usuario= ?, id_tipo_atraccion_favorito= ?, total_a_pagar= ?, total_horas_gastadas= ? WHERE id_usuario = ?";
	private static final String SQL_ELIMINAR = "DELETE FROM usuarios WHERE id_usuario = ?";






	

	@Override
	public List<Usuario> listarUsuarios() throws SQLException {
		Connection conn = null;
		PreparedStatement instruccion = null;
		ResultSet rs = null;
		Usuario usuario = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		conn = ConexionBDD.getConexion();
		instruccion = conn.prepareStatement(SQL_LISTAR);
		rs = instruccion.executeQuery();


			while (rs.next()) {
				int idUsuario = rs.getInt("id_usuario");
				String nombre = rs.getString("nombre_usuario");
				Double monedasDisponibles = rs.getDouble("monedas_usuario");
				Double horasDisponibles = rs.getDouble("tiempo_usuario");
				TipoDeAtraccion tipoFavorito = TipoDeAtraccion.valueOf(rs.getString("id_tipo_atraccion_favorito"));

				usuario = new Usuario(nombre, monedasDisponibles, horasDisponibles, tipoFavorito, idUsuario);

				usuarios.add(usuario);
			}

	
		return usuarios;
	
	}

	@Override
	public int insertar(Usuario usuario) throws SQLException {
		Connection conn = null;
		PreparedStatement instruccion = null;
		int registros = 0;
		
			conn = ConexionBDD.getConexion();
			instruccion = conn.prepareStatement(SQL_INSERTAR);
			instruccion.setString(1, usuario.getNombre());
			instruccion.setDouble(2, usuario.getMonedasDisponibles());
			instruccion.setDouble(3, usuario.getHorasDisponibles());
			instruccion.setString(4, usuario.getTipoFavorito().toString());
			registros = instruccion.executeUpdate(); 

		
		return registros;
	}


	@Override
	public int actualizar(Usuario usuario) throws SQLException {
		Connection conn = null;
		PreparedStatement instruccion = null;
		int registros = 0;

		
			conn = ConexionBDD.getConexion();
			instruccion = conn.prepareStatement(SQL_ACTUALIZAR);
			instruccion.setString(1, usuario.getNombre());
			instruccion.setDouble(2, usuario.getMonedasDisponibles());
			instruccion.setDouble(3, usuario.getHorasDisponibles());
			instruccion.setString(4, usuario.getTipoFavorito().toString());
			instruccion.setInt(5, usuario.getID());

			registros = instruccion.executeUpdate();



		return registros;

	}

	@Override
	public int eliminar(Usuario usuario) throws SQLException {

		Connection conn = null;
		PreparedStatement instruccion = null;
		int registros = 0;

		conn = ConexionBDD.getConexion();
		instruccion = conn.prepareStatement(SQL_ELIMINAR);
		instruccion.setInt(1, usuario.getID());
		registros = instruccion.executeUpdate();

		return registros;
	}


	@Override
	public List<Usuario> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int countAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int insert(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int update(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(Usuario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}}
