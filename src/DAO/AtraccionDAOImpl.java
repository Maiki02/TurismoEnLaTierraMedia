package DAO;

import java.sql.*;
import java.util.*;
import connection.ConexionBDD;
import excepciones.*;
import producto.*;

public class AtraccionDAOImpl implements iAtraccionDAO {
	private static final String SQL_LISTAR = "SELECT id_atraccion, nombre_atraccion, costo_atraccion, tiempo_atraccion, cupo, tipo_atraccion "
			+ "FROM atracciones JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = atracciones.id_tipo_atraccion";

	private static final String SQL_ACTUALIZAR = "UPDATE atracciones SET cupo = ? WHERE id_atraccion = ?;";

	@Override
	public List<Atraccion> listar() {
		try {
			List<Atraccion> atracciones = new ArrayList<Atraccion>();

			Connection conn = ConexionBDD.getConexion();
			PreparedStatement instruccion = conn.prepareStatement(SQL_LISTAR);
			ResultSet rs = instruccion.executeQuery();
			int id_atraccion = 0; // La declaramos acá para mostrarla en el catch

			while (rs.next()) {
				try {
					id_atraccion = rs.getInt("id_atraccion");
					String nombre_atraccion = rs.getString("nombre_atraccion");
					Double costo_atraccion = rs.getDouble("costo_atraccion");
					Double tiempo_atraccion = rs.getDouble("tiempo_atraccion");
					int cupo = rs.getInt("cupo");
					TipoDeAtraccion tipo_atraccion = TipoDeAtraccion.valueOf(rs.getString("tipo_atraccion"));

					Atraccion atraccion = new Atraccion(nombre_atraccion, costo_atraccion, tiempo_atraccion, cupo,
							tipo_atraccion, id_atraccion);

					atracciones.add(atraccion);
				} catch (ValorNegativo ne) {
					System.err.println(ne.getMessage() + " en la id: " + id_atraccion);
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leidos no es un numero valido en la id: " + id_atraccion);
				} catch (IllegalArgumentException iae) {
					System.err.println("Tipo de atraccion no reconocida en la id: " + id_atraccion);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
			return atracciones;

		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	@Override
	public int actualizar(Atraccion atraccion) {
		try {
			Connection conn = null;
			PreparedStatement instruccion = null;

			conn = ConexionBDD.getConexion();
			instruccion = conn.prepareStatement(SQL_ACTUALIZAR);
			instruccion.setInt(1, atraccion.getCuposDisponibles());
			instruccion.setInt(2, atraccion.getID());

			return instruccion.executeUpdate(); // nos devuelve la cantidad de registros afectados
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

}