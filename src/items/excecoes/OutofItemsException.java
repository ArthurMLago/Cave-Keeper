package items.excecoes;

/**
 * @author Vicente
 * @author Felipe Moret
 * 
 * excessao criada para quando tenta-se usar um item se sua quantidade eh zero
 */
public class OutofItemsException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public OutofItemsException(String message) {
		super(message);
	}

}
