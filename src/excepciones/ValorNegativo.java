package excepciones;
/***
 * ValorNegativo se lanza cuando un valor (que será pasado por archivo), posea un valor negativo
 * y este sea no valido.
 */
@SuppressWarnings("serial")
public class ValorNegativo extends RuntimeException {

	public ValorNegativo(String string) {
		super(string);
	}

}
