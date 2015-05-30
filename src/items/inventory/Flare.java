package items.inventory;

public class Flare extends GeneralItems {
	
	public Flare(String name, int number) {
		super(name,number);
	}
	
	public void effect() {
		super.effect();
		
//		chama funcao que ilumina parte do mapa onde o item foi usado
	}
}
