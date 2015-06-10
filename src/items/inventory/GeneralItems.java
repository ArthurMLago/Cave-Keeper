package items.inventory;

import items.excecoes.OutofItemsException;
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
	
	
/**	
 * @param number obtem valor do numero de itens disponiveis
 */
	public void setNumbers(int number) {
		this.number = number;
	}
	
/**	
 * Aumenta o a quantidade de itens disponíveis em uma unidade
 */
	public void increase() {
		this.number = this.number + 1;
	}
	
/**	
 * Aumenta o a quantidade de itens disponíveis em uma quantidade recebida
 * @param quantidade quantidade de itens a ser adicionada
 * faz a sobrecarga do método acima
 */	
	public void increase(int quantidade) {
		this.number = this.number + quantidade;
	}

/**	
 * Diminui o a quantidade de itens disponíveis em uma unidade
 */
	public void decrease() {
		this.number = this.number - 1;
	}
	
/**
 * ao usar o item, sua quantidade no inventario eh decrementada
 * e chama-se uma notificacao
 * @throws OutofItemsException 
 */
	public void effect() throws OutofItemsException  {
		if(this.number <= 0) {
			throw new OutofItemsException("Out of items!");
		}
		else {
			this.decrease();
			this.notifica();
		}
	}
	
/**	
 * Notifica se o efeito do item foi executado corretamente
 */
	public void notifica() {
		System.out.println("modificacao feita com sucesso");
	}
}
