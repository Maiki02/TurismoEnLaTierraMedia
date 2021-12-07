package DAO;

import java.util.List;
import java.util.Map;

import producto.*;

public interface iPromocionDAO {

	public abstract List<Promocion> listarPromocionesValidas(Map<Integer, Atraccion> mapDeAtraccionesPorID);

}
