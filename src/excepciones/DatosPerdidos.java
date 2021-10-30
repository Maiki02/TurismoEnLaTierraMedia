package excepciones;

/***
 * Datos perdidos se lanza cuando nuestra base de datos presenta un error.
 */

public class DatosPerdidos extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatosPerdidos(Exception e) {
		super(e);
	}

}
