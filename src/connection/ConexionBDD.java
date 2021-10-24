package connection;

import java.sql.*;

public class ConexionBDD {

	private static String url = "C:\\Users\\Esteban\\Desktop\\BDDTP\\TurismoEnLaTierraMedia.db";
	private static Connection conexion;

	public static Connection getConexion() throws SQLException {
		if (conexion == null) {
			conexion = DriverManager.getConnection(url);
		}
		return conexion;
	}

}
