package DAO;

import java.sql.*;
import java.util.*;

import conexion.ConexionBDD;
import excepciones.*;
import producto.*;

public class PromocionDAOImpl implements iPromocionDAO {

	private static final String SQL_LISTAR = "SELECT id_promocion, nombre_promocion, tipo_promocion, tipo_atraccion, costo_promocion, descuento_promocion, id_atraccion_premio "
			+ "FROM promociones "
			+ "LEFT JOIN tipo_promocion ON tipo_promocion.id_tipo_promocion = promociones.id_tipo_promocion "
			+ "LEFT JOIN atracciones ON atracciones.id_atraccion = promociones.id_atraccion_premio "
			+ "LEFT JOIN tipo_atraccion ON tipo_atraccion.id_tipo_atraccion = promociones.id_tipo_atraccion";

	public List<Promocion> listarPromocionesValidas(Map<Integer, Atraccion> mapDeAtraccionesPorID) {
		try {
			Connection conn = ConexionBDD.getConexion();
			PreparedStatement instruccion = conn.prepareStatement(SQL_LISTAR);
			ResultSet rs = instruccion.executeQuery();
			// ------------------------------------------------------
			List<Promocion> promociones = new ArrayList<Promocion>();
			Map<Integer, List<Atraccion>> mapaDeIDPromocionAtraccion = crearMapDeAtraccionesInvolucradas(
					mapDeAtraccionesPorID);

			while (rs.next()) {
				try {
					Promocion nuevaPromocion = crearPromocion(rs, mapaDeIDPromocionAtraccion, mapDeAtraccionesPorID);
					promociones.add(nuevaPromocion);
				} catch (AtraccionDeDistintoTipo addt) {
					System.err.println(addt);
				} catch (ValorNegativo ne) {
					System.err.println(ne.getMessage());
				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leidos no es un numero valido");
				} catch (IllegalArgumentException iae) {
					System.err.println("Tipo de atraccion no reconocida");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}

			}

			return promociones;
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	private Promocion crearPromocion(ResultSet rs, Map<Integer, List<Atraccion>> mapaDeIDPromocionAtraccion,
			Map<Integer, Atraccion> mapDeAtraccionesPorID)
			throws ValorNegativo, AtraccionDeDistintoTipo, NumberFormatException, IllegalArgumentException {
		try {
			// Tomamos el tipo de promocion
			TipoDePromocion tipoPromocion = TipoDePromocion.valueOf(rs.getString("tipo_promocion"));

			// Tomamos los datos de la tabla
			int id = rs.getInt("id_promocion");
			String nombre = rs.getString("nombre_promocion");
			List<Atraccion> atraccionesInvolucradas = buscarAtraccionesInvolucradas(rs.getInt("id_promocion"),
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
				Atraccion atraccionPremio = mapDeAtraccionesPorID.get(Integer.valueOf(atraccionPremioID));
				atraccionesInvolucradas.add(atraccionPremio);
				nuevaPromocion = new AxB(nombre, tipoAtraccion, atraccionesInvolucradas, atraccionPremio, id);
			}
			return nuevaPromocion;
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	private Map<Integer, List<Atraccion>> crearMapDeAtraccionesInvolucradas(Map<Integer, Atraccion> mapaDeAtracciones) {
		try {
			Connection conn = ConexionBDD.getConexion();
			PreparedStatement instruccion = conn.prepareStatement("SELECT * FROM atracciones_involucradas");
			ResultSet rs = instruccion.executeQuery();
			// -----------------------------------------
			Map<Integer, List<Atraccion>> mapAtraccionesInvolucradas = new HashMap<Integer, List<Atraccion>>();

			while (rs.next()) {
				Integer promocionID = rs.getInt("id_promocion");
				Integer atraccionID = rs.getInt("id_atraccion");
				if (!mapAtraccionesInvolucradas.containsKey(promocionID)) {
					mapAtraccionesInvolucradas.put(promocionID, new LinkedList<Atraccion>());
				}
				mapAtraccionesInvolucradas.get(promocionID).add(mapaDeAtracciones.get(atraccionID));
			}
			return mapAtraccionesInvolucradas;
		} catch (Exception e) {
			throw new DatosPerdidos(e);
		}
	}

	private List<Atraccion> buscarAtraccionesInvolucradas(int id, Map<Integer, List<Atraccion>> mapPromocionAtraccion) {
		return mapPromocionAtraccion.get(Integer.valueOf(id));
	}

}
