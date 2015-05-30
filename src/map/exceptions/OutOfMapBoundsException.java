package map.exceptions;

public class OutOfMapBoundsException extends Exception {
	
	private static final long serialVersionUID = 0x18fe9a0c;

	public OutOfMapBoundsException() {
		super("Programa tentou acessar coordenadas fora do mapa!");
	}

	public OutOfMapBoundsException(String message) {
		super(message);
	}

	public OutOfMapBoundsException(Throwable cause) {
		super(cause);
	}

	public OutOfMapBoundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public OutOfMapBoundsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
