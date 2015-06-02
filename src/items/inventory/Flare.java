package items.inventory;

public class Flare extends GeneralItems {
	
	public Flare(String name, int number) {
		super(name,number);
	}
	
	public void effect() {
		super.effect();
		

//		GameController.sharedInstance.map.iluminateRadius();

	}
}
