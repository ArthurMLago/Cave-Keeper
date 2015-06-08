package items.inventory;

import visual.interfaces.IMapVisual;

/**
 * @author Vicente
 * @author Felipe Moret
 */
public class Flash extends GeneralItems {
	
	private static final long serialVersionUID = 1L;
	private IMapVisual map;
	
	public Flash(String name, int number) {
		super(name,number);
	}
		
	public void effect() {
		map.flareVisual();

	}

	
}
