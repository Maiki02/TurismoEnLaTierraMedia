package app;

import java.util.List;

import archivos.*;
import producto.*;
import usuario.*;
import parqueDeAtracciones.*;

public class App {

	public static void main(String[] args) {
		List<Usuario> usuarios = ArchivoUsuarios.leerArchivoUsuarios();
		List<Atraccion> atracciones= ArchivoAtracciones.leerArchivoAtracciones();
		List<Promocion> promociones= ArchivoPromociones.leerArchivoPromociones(atracciones);
		ParqueDeAtracciones parque= new ParqueDeAtracciones(usuarios, atracciones, promociones);
		
		parque.ofrecerProductosALosUsuarios();
		//La lista de usuarios ya cambia dentro de esa funcion
		
		//Es momento de escribirlos
		ArchivoUsuarios.escribirUsuarios(usuarios);
		
		//No se si falta algo xD
		
		
		
	}

}
