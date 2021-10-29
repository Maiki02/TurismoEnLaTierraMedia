
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import connection.ConexionBDD;
import producto.Atraccion;
import producto.Producto;
import producto.Promocion;
import producto.TipoDeAtraccion;
import usuario.Usuario;

public class UsuarioDAOImpl implements iUsuarioDAO {

	private static final String SQL_LISTAR = "SELECT id_usuario, nombre_usuario, tiempo_usuario, monedas_usuario, tipo_atraccion, total_a_pagar, total_horas_gastadas "
			+ "FROM usuarios "
			+ "LEFT JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = usuarios.id_tipo_atraccion_favorito";
	private static final String SQL_ACTUALIZAR = "UPDATE usuarios SET tiempo_usuario = ?, monedas_usuario= ?, total_a_pagar= ?, total_horas_gastadas= ? WHERE id_usuario = ?";

	public List<Usuario> listarUsuarios(List<Atraccion> atracciones, List<Promocion> promociones) throws SQLException {
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
			TipoDeAtraccion tipoFavorito = TipoDeAtraccion.valueOf(rs.getString("tipo_atraccion"));
			double totalAPagar = rs.getDouble("total_a_pagar");
			double totalHorasGastadas = rs.getDouble("total_horas_gastadas");

			List<Producto> productosComprados = buscarProductosComprados(idUsuario, atracciones, promociones);

			usuario = new Usuario(idUsuario, nombre, monedasDisponibles, horasDisponibles, tipoFavorito, totalAPagar,
					totalHorasGastadas, productosComprados);

			usuarios.add(usuario);
		}

		return usuarios;

	}


	private List<Producto> buscarProductosComprados(int idUsuario, List<Atraccion> atracciones,
			List<Promocion> promociones) throws SQLException {
		Connection conn = ConexionBDD.getConexion();
		PreparedStatement instruccion = conn.prepareStatement("SELECT * FROM compra_del_usuario WHERE id_usuario=?");
		
		instruccion.setInt(1, idUsuario);

		ResultSet rs = instruccion.executeQuery();

		Map<Integer, Atraccion> mapDeAtraccionesPorID = Atraccion.crearMapDeAtracciones(atracciones);
		Map<Integer, Promocion> mapDePromocionesPorID = Promocion.crearMapDePromociones(promociones);
		List<Producto> productosComprados = new LinkedList<Producto>();
		while (rs.next()) {
			Integer idPromocion = rs.getInt("id_promocion_comprada");
			Integer idAtraccion = rs.getInt("id_atraccion_comprada");
			Producto productoComprado = null;

			if (idPromocion == null) {
				productoComprado = mapDeAtraccionesPorID.get(idAtraccion);
			} else {
				productoComprado = mapDePromocionesPorID.get(idPromocion);
			}

			productosComprados.add(productoComprado);
		}

		return productosComprados;
	}

	@Override
	public int actualizar(Usuario usuario) throws SQLException {
		Connection conn = ConexionBDD.getConexion();
		PreparedStatement instruccion = conn.prepareStatement(SQL_ACTUALIZAR);
		instruccion.setDouble(1, usuario.getHorasDisponibles());
		instruccion.setDouble(2, usuario.getMonedasDisponibles());
		instruccion.setDouble(3, usuario.getTotalAPagar());
		instruccion.setDouble(4, usuario.getHorasGastadas());
		instruccion.setDouble(5, usuario.getID());
		
		actualizarProductosComprados(usuario);
	
		return instruccion.executeUpdate();
	}

	private static void actualizarProductosComprados(Usuario usuario) throws SQLException {
		List<Producto> productosComprados= usuario.getProductosComprados();
		Connection conn = ConexionBDD.getConexion();
		String sql="INSERT INTO compra_del_usuario (id_usuario, id_promocion_comprada, id_atraccion_comprada) VALUES (?, ?, ?);";
		
		for(Producto producto: productosComprados) {
	
			PreparedStatement instruccion = conn.prepareStatement(sql);
			instruccion.setInt(1, usuario.getID());
			if(producto.esPromocion()){
				instruccion.setInt(2, producto.getID());
				instruccion.setInt(3, (Integer) null);
			} else if(!producto.esPromocion()) {
				instruccion.setInt(2, (Integer) null);
				instruccion.setInt(3, producto.getID());
			}
			instruccion.executeUpdate();
		}
		
	}


}
