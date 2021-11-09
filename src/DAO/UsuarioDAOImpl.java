
package DAO;

import java.sql.*;
import java.util.*;

import conexion.ConexionBDD;
import excepciones.*;
import producto.*;
import usuario.Usuario;

public class UsuarioDAOImpl implements iUsuarioDAO {

	private static final String SQL_LISTAR = "SELECT id_usuario, nombre_usuario, tiempo_usuario, monedas_usuario, tipo_atraccion, total_a_pagar, total_horas_gastadas "
			+ "FROM usuarios "
			+ "LEFT JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = usuarios.id_tipo_atraccion_favorito";
	private static final String SQL_ACTUALIZAR = "UPDATE usuarios SET tiempo_usuario = ?, monedas_usuario= ?, total_a_pagar= ?, total_horas_gastadas= ? WHERE id_usuario = ?";

	public List<Usuario> listarUsuarios(Map<Integer, Atraccion> mapDeAtraccionesPorID,
			Map<Integer, Promocion> mapDePromocionesPorID) {
		try {
			Connection conn = null;
			PreparedStatement instruccion = null;
			ResultSet rs = null;
			Usuario usuario = null;
			List<Usuario> usuarios = new ArrayList<Usuario>();

			conn = ConexionBDD.getConexion();
			instruccion = conn.prepareStatement(SQL_LISTAR);
			rs = instruccion.executeQuery();

			int idUsuario = 0; // La declaramos acá para que pueda ingresar a los catch
			while (rs.next()) {
				try {
					idUsuario = rs.getInt("id_usuario");
					String nombre = rs.getString("nombre_usuario");
					Double monedasDisponibles = rs.getDouble("monedas_usuario");
					Double horasDisponibles = rs.getDouble("tiempo_usuario");
					TipoDeAtraccion tipoFavorito = TipoDeAtraccion.valueOf(rs.getString("tipo_atraccion"));
					double totalAPagar = rs.getDouble("total_a_pagar");
					double totalHorasGastadas = rs.getDouble("total_horas_gastadas");

					List<Producto> productosComprados = buscarProductosComprados(idUsuario, mapDeAtraccionesPorID,
							mapDePromocionesPorID);

					usuario = new Usuario(idUsuario, nombre, monedasDisponibles, horasDisponibles, tipoFavorito,
							totalAPagar, totalHorasGastadas, productosComprados);

					usuarios.add(usuario);
				} catch (ValorNegativo ne) {
					System.err.println(ne.getMessage() + "en id_usuario: " + idUsuario);
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leidos no es un numero valido en id_usuario: " + idUsuario);
				} catch (IllegalArgumentException iae) {
					System.err.println("Tipo de atraccion no reconocida en id_usuario: " + idUsuario);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}

			return usuarios;
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}

	}

	private List<Producto> buscarProductosComprados(int idUsuario, Map<Integer, Atraccion> mapDeAtraccionesPorID,
			Map<Integer, Promocion> mapDePromocionesPorID) {
		try {
			Connection conn = ConexionBDD.getConexion();
			PreparedStatement instruccion = conn
					.prepareStatement("SELECT * FROM compra_del_usuario WHERE id_usuario=?");
			instruccion.setInt(1, idUsuario);
			ResultSet rs = instruccion.executeQuery();

			List<Producto> productosComprados = new ArrayList<Producto>();
			while (rs.next()) {
				Integer idPromocion = rs.getInt("id_promocion_comprada");
				Integer idAtraccion = rs.getInt("id_atraccion_comprada");
				Producto productoComprado = null;

				if (idPromocion == 0) {
					productoComprado = mapDeAtraccionesPorID.get(idAtraccion);
				} else {
					productoComprado = mapDePromocionesPorID.get(idPromocion);
				}

				productosComprados.add(productoComprado);
			}

			return productosComprados;
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	@Override
	public int actualizar(Usuario usuario) {
		try {
			Connection conn = ConexionBDD.getConexion();
			PreparedStatement instruccion = conn.prepareStatement(SQL_ACTUALIZAR);
			instruccion.setDouble(1, usuario.getHorasDisponibles());
			instruccion.setDouble(2, usuario.getMonedasDisponibles());
			instruccion.setDouble(3, usuario.getTotalAPagar());
			instruccion.setDouble(4, usuario.getHorasGastadas());
			instruccion.setDouble(5, usuario.getID());

			actualizarProductosComprados(usuario);

			return instruccion.executeUpdate();
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	private static void actualizarProductosComprados(Usuario usuario) {
		try {
			List<Producto> productosComprados = usuario.getProductosComprados();
			Connection conn = ConexionBDD.getConexion();
			String sql = "INSERT INTO compra_del_usuario (id_usuario, id_promocion_comprada, id_atraccion_comprada) VALUES (?, ?, ?);";

			for(int i = usuario.getCantidadProductosViejos(); i<productosComprados.size(); i++) {
				PreparedStatement instruccion = conn.prepareStatement(sql);
				instruccion.setInt(1, usuario.getID());
				
				Producto productoAAgregar = productosComprados.get(i);

				if (productoAAgregar.esPromocion()) {
					instruccion.setInt(2, productoAAgregar.getID());
					instruccion.setString(3, null);
				} else if (!productoAAgregar.esPromocion()) {
					instruccion.setString(2, null);
					instruccion.setInt(3, productoAAgregar.getID());
				}
				instruccion.executeUpdate();
			}

		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}

	}
}
