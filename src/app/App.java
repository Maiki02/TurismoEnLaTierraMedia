package app;

import java.sql.SQLException;

import parqueDeAtracciones.*;

public class App {

	public static void main(String[] args) throws SQLException {
		ParqueDeAtracciones parque= new ParqueDeAtracciones();	
		parque.ofrecerProductosALosUsuarios();

	}

}
