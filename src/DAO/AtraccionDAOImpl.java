package DAO;

import java.sql.*;
import java.util.*;
import connection.ConexionBDD;

import producto.Atraccion;
import producto.TipoDeAtraccion;

public class AtraccionDAOImpl implements iAtraccionDAO {
	private static final String SQL_LISTAR = "SELECT id_atraccion, nombre_atraccion, costo_atraccion, tiempo_atraccion, cupo, tipo_atraccion FROM atracciones";
	private static final String SQL_INSERTAR = "INSERT INTO atracciones(nombre_atraccion, costo_atraccion, tiempo_atraccion, cupo, tipo_atraccion) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_ACTUALIZAR = "UPDATE atracciones SET nombre_atraccion = ?, costo_atraccion = ?, tiempo_atraccion = ?, cupo = ?, tipo_atraccion = ? WHERE id__atraccion = ?";
	private static final String SQL_ELIMINAR = "DELETE FROM atracciones WHERE id_atraccion = ?";

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
	public int insertar(Atraccion atraccion) throws SQLException {
		Connection conn = null;
		PreparedStatement instruccion = null;
		int registros = 0;

		conn = ConexionBDD.getConexion();
		instruccion = conn.prepareStatement(SQL_INSERTAR);
		instruccion.setString(1, atraccion.getNombre());
		instruccion.setDouble(2, atraccion.getCosto());
		instruccion.setDouble(3, atraccion.getDuracion());
		instruccion.setInt(4, atraccion.getCuposDisponibles());
		instruccion.setString(5, atraccion.getTipoAtraccion().toString());
		registros = instruccion.executeUpdate(); // nos devuelve la cantidad de registros afectados

		return registros;
	}

	@Override
	public int actualizar(Atraccion atraccion) throws SQLException {
		Connection conn = null;
		PreparedStatement instruccion = null;
		int registros = 0;

		conn = ConexionBDD.getConexion();
		instruccion = conn.prepareStatement(SQL_INSERTAR);
		instruccion.setString(1, atraccion.getNombre());
		instruccion.setDouble(2, atraccion.getCosto());
		instruccion.setDouble(3, atraccion.getDuracion());
		instruccion.setInt(4, atraccion.getCuposDisponibles());
		instruccion.setString(5, atraccion.getTipoAtraccion().toString());
		registros = instruccion.executeUpdate(); // nos devuelve la cantidad de registros afectados

		return registros;
	}

	@Override
	public int eliminar(Atraccion atraccion) throws SQLException {
		Connection conn = null;
		PreparedStatement instruccion = null;
		int registros = 0;

		conn = ConexionBDD.getConexion();
		instruccion = conn.prepareStatement(SQL_ELIMINAR);
		instruccion.setInt(1, atraccion.getIdAtraccion());
		registros = instruccion.executeUpdate();

		return registros;
	}
}