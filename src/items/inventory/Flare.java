package items.inventory;

/**
 * @author Vicente
 * @author Felipe Moret
 */
public class Flare extends GeneralItems {
	
	
	private static final long serialVersionUID = 1L;

	public Flare(String name, int number) {
		super(name,number);
	}
	
	public void effect() {
		super.effect();
		

//		GameController.sharedInstance.map.iluminateRadius();

	}
}
