package items.inventory;

import items.interfaces.IItems;

import java.io.Serializable;

/**
 * @author Vicente
 * @Felipe Moret
 * classe mae de todos os items, assim contendo os metodos e atributos basicos
 */
public class GeneralItems implements IItems, Serializable {


	private static final long serialVersionUID = 1L;

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
	
/**	
 * @return name nome do item respectivo
 */
	public String getName() {
		return this.name;
	}

/**	
 * @return number numero de itens presentes
 */
	public int getNumber() {
		return this.number;
	}
	
	public void setNumber(int number) {
		this.number = number;
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
