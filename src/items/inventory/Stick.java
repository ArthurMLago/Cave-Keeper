package items.inventory;

import items.excecoes.OutofItemsException;

/**
 * @author Vicente
 * @author Felipe Moret
 */
public class Stick extends GeneralItems {
	
	private static final long serialVersionUID = 1L;

	public Stick(String name, int number) {
		super(name,number);
	}
	
	
	public void effect() throws OutofItemsException {
		super.effect();
		
/**
 * chama funcao que checa o que envolta do carinha e detona a armadilha, caso exista
 */
	}
}