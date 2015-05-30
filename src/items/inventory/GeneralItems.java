package items.inventory;

import items.interfaces.IItems;

public class GeneralItems implements IItems {

// Os atributos sao o nome e a quantidade de um item
	private String name;
	private int number;
	
//o objeto deve ser sempre criado com um nome e com sua quantidade inicial	
	public GeneralItems(String name, int number) {
		this.name = name;
		this.number = number;
	}
	
//	getters dos atributos
	public String getName() {
		return this.name;
	}
	
	public int getNumber() {
		return this.number;
	}
	
//	manipuladores de quantidade de items, utilizados no uso e obtencao dos mesmos
	public void increase() {
		this.number = this.number + 1;
	}
	
	public void decrease() {
		this.number = this.number - 1;
	}
	
//	funcao de efeito de item, que pode ser reescrita nos filhos, caso tenham um efeito especial
	public void effect() {
		this.decrease();
		this.notifica();
	}
	
//  sistema de notificacao de uso ou obtencao de item	
	public void notifica() {
		System.out.println("modificacao feita com sucesso");
	}
}
