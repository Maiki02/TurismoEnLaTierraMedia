package DAO;

import java.sql.*;
import java.util.*;
import connection.ConexionBDD;

import producto.Atraccion;
import producto.TipoDeAtraccion;

public class AtraccionDAOImpl implements iAtraccionDAO {
	private static final String SQL_LISTAR = "SELECT id_atraccion, nombre_atraccion, costo_atraccion, tiempo_atraccion, cupo, tipo_atraccion "
			+ "FROM atracciones JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = atracciones.id_tipo_atraccion";
	
	private static final String SQL_ACTUALIZAR = "UPDATE atracciones SET cupo = ? WHERE id_atraccion = ?;";
	

	@Override
	public List<Atraccion> listar() throws SQLException {
		Connection conn = null;
		PreparedStatement instruccion = null;
		ResultSet rs = null;
		Atraccion atraccion = null;
		List<Atraccion> atracciones = new ArrayList<Atraccion>();

		conn = ConexionBDD.getConexion();
		instruccion = conn.prepareStatement(SQL_LISTAR);
		rs = instruccion.executeQuery();

		while (rs.next()) {
			int id_atraccion = rs.getInt("id_atraccion");
			String nombre_atraccion = rs.getString("nombre_atraccion");
			Double costo_atraccion = rs.getDouble("costo_atraccion");
			Double tiempo_atraccion = rs.getDouble("tiempo_atraccion");
			int cupo = rs.getInt("cupo");
			TipoDeAtraccion tipo_atraccion = TipoDeAtraccion.valueOf(rs.getString("tipo_atraccion"));

			atraccion = new Atraccion(nombre_atraccion, costo_atraccion, tiempo_atraccion, cupo, tipo_atraccion,
					id_atraccion);

			atracciones.add(atraccion);
		}
		return atracciones;
	}

	@Override
	public int actualizar(Atraccion atraccion) throws SQLException {
		Connection conn = null;
		PreparedStatement instruccion = null;

		conn = ConexionBDD.getConexion();
		instruccion = conn.prepareStatement(SQL_ACTUALIZAR);
		instruccion.setInt(1, atraccion.getCuposDisponibles());
		instruccion.setInt(2, atraccion.getID());
		
		return instruccion.executeUpdate(); // nos devuelve la cantidad de registros afectados
	}


}