package items.inventory;

import items.excecoes.OutofItemsException;


/**
 * @author Vicente
 * @author Felipe Moret
 */
public class Flash extends GeneralItems{
	
	private static final long serialVersionUID = 1L;
	
	public Flash(String name, int number) {
		super(name,number);
	}
		
	public void effect() throws OutofItemsException {
		super.effect();
	}

	
}
