package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import producto.*;

public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public List<Promocion> listarPromocionesValidas(List<Atraccion> atracciones) throws SQLException {
		Connection conn = ConexionBDD.getConnection();
		PreparedStatement instruccion = conn.prepareStatement(
				"SELECT id_promocion, nombre_promocion, tipo_promocion, tipo_atraccion, costo_promocion, descuento_promocion, nombre_atraccion"
						+ "FROM promociones"
						+ "LEFT JOIN tipo_promocion ON tipo_promocion.id_tipo_promocion = promociones.id_tipo_promocion"
						+ "LEFT JOIN atracciones ON atracciones.id_atraccion = promociones.id_atraccion_premio"
						+ "LEFT JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = promociones.id_tipo_atraccion");
		ResultSet rs = instruccion.executeQuery();
		// ------------------------------------------------------
		List<Promocion> promociones = new ArrayList<Promocion>();
		Map<Integer, Atraccion> mapDeAtraccionesPorID = Atraccion.crearMapDeAtracciones(atracciones);
		Map<Integer, List<Atraccion>> mapaDeIDPromocionAtraccion = crearMapDeAtraccionesInvolucradas(
				mapDeAtraccionesPorID);

		while (rs.next()) {
			Promocion nuevaPromocion = crearPromocion(rs, mapaDeIDPromocionAtraccion, mapDeAtraccionesPorID);
			promociones.add(nuevaPromocion);
		}

		return promociones;
	}

	private Promocion crearPromocion(ResultSet rs, Map<Integer, List<Atraccion>> mapaDeIDPromocionAtraccion,
			Map<Integer, Atraccion>mapDeAtraccionesPorID)
			throws SQLException {
		// Tomamos el tipo de promocion
		TipoDePromocion tipoPromocion = TipoDePromocion.valueOf(rs.getString("tipo_promocion"));

		// Tomamos los datos de la tabla
		int id = rs.getInt("id_promocion");
		String nombre = rs.getString("nombre_promocion");
		List<Atraccion> atraccionesInvolucradas = buscarAtraccionesInvolucradas(rs.getInt("atracciones_involucradas"),
				mapaDeIDPromocionAtraccion);
		TipoDeAtraccion tipoAtraccion = TipoDeAtraccion.valueOf(rs.getString("tipo_atraccion"));

		Promocion nuevaPromocion = null;
		if (tipoPromocion == TipoDePromocion.ABSOLUTA) {
			Double costoPromocion = rs.getDouble("costo_promocion");
			nuevaPromocion = new Absoluta(nombre, tipoAtraccion, atraccionesInvolucradas, costoPromocion, id);

		} else if (tipoPromocion == TipoDePromocion.PORCENTUAL) {
			Double descuentoPromocion = rs.getDouble("descuento_promocion");
			nuevaPromocion = new Porcentual(nombre, tipoAtraccion, atraccionesInvolucradas, descuentoPromocion, id);

		} else if (tipoPromocion == TipoDePromocion.AXB) {
			int atraccionPremioID = rs.getInt("id_atraccion_premio");
			Atraccion atraccionPremio=mapDeAtraccionesPorID.get(Integer.valueOf(atraccionPremioID));
			nuevaPromocion = new AxB(nombre, tipoAtraccion, atraccionesInvolucradas, atraccionPremio, id);
		}
		return nuevaPromocion;
	}

	private Map<Integer, List<Atraccion>> crearMapDeAtraccionesInvolucradas(Map<Integer, Atraccion> mapaDeAtracciones) {
		Connection conn = ConexionBDD.getConnection();
		PreparedStatement instruccion = conn.prepareStatement("SELECT * FROM atracciones_involucradas");
		ResultSet rs = instruccion.executeQuery();
		// -----------------------------------------
		Map<Integer, List<Atraccion>> mapAtraccionesInvolucradas = new HashMap<Integer, List<Atraccion>>();

		while (rs.next()) {
			Integer promocionID = rs.getInt("id_promocion");
			Integer atraccionID = rs.getInt("id_atraccion");
			if (mapAtraccionesInvolucradas.containsKey(promocionID)) {
				mapAtraccionesInvolucradas.get(promocionID).add(mapaDeAtracciones.get(atraccionID));
			} else {
				mapAtraccionesInvolucradas.put(promocionID, new LinkedList<Atraccion>());
			}
		}
		return mapAtraccionesInvolucradas;
	}

	private List<Atraccion> buscarAtraccionesInvolucradas(int id, Map<Integer, List<Atraccion>> mapPromocionAtraccion) {
		return mapPromocionAtraccion.get(Integer.valueOf(id));
	}

	@Override
	public int countAll() throws SQLException {
		Connection conn = ConexionBDD.getConnection();
		PreparedStatement instruccion = conn.prepareStatement("SELECT count(*) FROM (productos)");
		ResultSet rs = instruccion.executeQuery();
		return rs.getInt(1);
	}

	@Override
	public int insert(Promocion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Promocion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Promocion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Promocion> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
