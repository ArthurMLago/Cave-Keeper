package items.inventario;

import items.interfaces.IItems;

public class GeneralItems implements IItems {

	private String name;
	private int number;
	
	public GeneralItems(String name, int number) {
		this.name = name;
		this.number = number;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void increase() {
		this.number = this.number + 1;
	}
	
	public void decrease() {
		this.number = this.number - 1;
	}
	
	public void effect() {
		this.decrease();
		this.notifica();
	}
	
	public void notifica() {
		System.out.println("modificacao feita com sucesso");
	}
}
