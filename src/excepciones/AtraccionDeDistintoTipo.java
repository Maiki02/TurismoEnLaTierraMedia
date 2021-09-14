package excepciones;
/***
 * AtraccionDeDistintoTipo se lanza un tipo de atraccion, es distinto al tipo que indica una atraccion
 */
@SuppressWarnings("serial")
public class AtraccionDeDistintoTipo extends RuntimeException {

	public AtraccionDeDistintoTipo(String string) {
		super(string);
	}

}