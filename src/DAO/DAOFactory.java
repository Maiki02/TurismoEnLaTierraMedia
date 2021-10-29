package DAO;

public class DAOFactory {

	public static iUsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}

	public static iPromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}
	
	public static iAtraccionDAO getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}
	
}
