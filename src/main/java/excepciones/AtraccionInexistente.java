package excepciones;
/***
 * AtraccionInexistente se lanza cuando un string, no representa ninguna atraccion.
 */
@SuppressWarnings("serial")
public class AtraccionInexistente extends RuntimeException {

	public AtraccionInexistente(String string) {
		super(string);
	}

}
