package excepciones;
/***
 * CantidadDatosInvalidos se lanza cuando una linea (ingresada por archivo), se splitee y tenga mas
 * o menos de ciertos valores esperados.
 */
@SuppressWarnings("serial")
public class CantidadDatosInvalidos extends RuntimeException {

	public CantidadDatosInvalidos(String string) {
		super(string);
	}

}
