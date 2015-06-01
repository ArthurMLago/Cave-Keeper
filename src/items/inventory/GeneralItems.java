package items.inventory;

import items.interfaces.IItems;

/**
 * @author Vicente
 * classe mae de todos os items, assim contendo os metodos e atributos basicos
 */
public class GeneralItems implements IItems {

/**
 * atributos: nome e quantidade no inventario
 */
	private String name;
	private int number;
	
/**
 * construtor
 * @param name nome inicial
 * @param number quantidade inicial
 */
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
	
/**
 * ao usar o item, sua quantidade no inventario eh decrementada
 * e chama-se uma notificacao
 */
	public void effect() {
		this.decrease();
		this.notifica();
	}
		
	public void notifica() {
		System.out.println("modificacao feita com sucesso");
	}
}
