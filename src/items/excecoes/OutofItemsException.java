package items.excecoes;

public class OutofItemsException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public OutofItemsException(String message) {
		super(message);
	}

}
