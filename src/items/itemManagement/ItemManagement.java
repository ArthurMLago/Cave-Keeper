package items.itemManagement;

import items.excecoes.OutofItemsException;
import items.interfaces.IItemManagement;
import items.interfaces.IItems;
import items.inventory.Flare;
import items.inventory.Flash;
import items.inventory.GeneralItems;
import items.inventory.Stick;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import anima.annotation.Component;
import anima.component.base.ComponentBase;

/**
 * @author Vicente
 * @author Felipe Moret
 *
 * Classe responsavel pela comunicacao entre outras classes e o inventario
 * Classe respons�vel por gerenciar os itens dispon�ves, seu incremento, decremento e quantidade, al�m
 * de sua inicializa��o
 */
@Component(id="<http://cave.com/items.itemManagement.ItemManagement>", provides={"<http://cave.com/items.interfaces.IItemManagement>"})
public class ItemManagement extends ComponentBase implements IItemManagement, Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * ArrayList em que cada posicao armazena um objeto correspondente a um TIPO de item
	 */
	private ArrayList <IItems> inventory = new ArrayList <IItems>();

/**
 * Inicializando o ArrayList dos itens como vazio para todas as posi��es
 */
	public ItemManagement() {
		inventory.add(new Flare("flare", 50000));
		inventory.add(new Flash("flash", 5));
		inventory.add(new GeneralItems("fuel", 5));
		inventory.add(new GeneralItems("saltAmmo", 5));
		inventory.add(new Stick("stick", 5));
		
	}

/**
 * Inicializando o ArrayList dos itens com uma quantidade determinada para todas as posi��es
 */
	public void startInventoryNumbers(ArrayList<Integer> quantities) {
		for(int i = 0; i < quantities.size(); i++) {
			inventory.get(i).setNumbers(quantities.get(i));
		}
	}
	
/**
 * @param tipo enumerado identificando o item
 * chama o metodo que faz o efeito do item
 * @throws OutofItemsException 
 */
	public void useItem(ItemsList place) throws OutofItemsException {
		int position = 0;
		position = this.getPosition(place);
		
		inventory.get(position).effect();		
	}

/**
 * ao obter um novo item, chama-se tal metodo para aumentar sua quantidade
 * @param tipo enumerado identificando o item
 */
	public void obtainItem(ItemsList place) {
		int position = 0;
		
		position = this.getPosition(place);
		inventory.get(position).increase();
	}
	
/**
 * metodo chamado ao pisar em uma armadilha
 * zera a quantidade de um item aleatorio do inventario	
 */
	public void itsaTrap() {
		int position = 0;
		Random random_number = new Random();
		ItemsList name;
		
		position = random_number.nextInt(5);
		name = getEnum_name(position);
		this.setNumber(name, 0);
		
	}
	

/**
 * ao obter um novo item, chama-se tal metodo para aumentar sua quantidade
 * Sobrecarca de metodo
 * @param tipo enumerado identificando o item
 * @param quantidade identifica a quantidade que foi obtida do item
 */
	public void obtainItem(ItemsList place, int quantidade) {
		int position = 0;
		position = this.getPosition(place);
		
		inventory.get(position).increase(quantidade);
	}
	

/**
 * metodo usado para obter a quantidade de itens dispon�veis
 * @param tipo enumerado identificando o item
 */	
	public int displayNumber(ItemsList place) {
		int position = 0;
		position = this.getPosition(place);
		
		return inventory.get(position).getNumber();
	}
	

/**
 *metodo usado para atribuir a quantidade de itens dispon�veis
 * @param tipo enumerado identificando o item
 * @param number identifica o numero a ser atribuido
 */	
	public void setNumber(ItemsList place, int number) {
		int position = 0;
		position = this.getPosition(place);
		
		inventory.get(position).setNumbers(number);
	}
	

/**
 *metodo usado para obter o nome do item em determinada posicao do arraylist
 * @param tipo enumerado identificando o item
 */	
	public String displayName(ItemsList place) {
		int position = 0;
		position = this.getPosition(place);

		return inventory.get(position).getName();
	}
	
/**	
 * @param place tipo enumerado
 * @return a posicao do tipo enumerado no arraylist
 */
	public int getPosition(ItemsList place) {
		int position = 0;
		
		switch(place) {
		case Flare:
			position = 0;
			break;
		case Flash:
			position = 1;
			break;
		case Fuel:
			position = 2;
			break;
		case SaltAmmo:
			position = 3;
			break;
		case Stick:
			position = 4;
			break;	
		}
			
		return position;
	}
	
/**
 * faz o inverso do metodo acima	
 * @param place inteiro que representa a casa no array list
 * @return o tipo enumerado do item que tem na casa
 */
	public ItemsList getEnum_name(int place) {
		ItemsList name;
		
		switch(place) {
		case 0:
			name = ItemsList.Flare;
			break;
		case 1:
			name = ItemsList.Flash;
			break;
		case 2:
			name = ItemsList.Fuel;
			break;
		case 3:
			name = ItemsList.SaltAmmo;
			break;
		case 4:
			name = ItemsList.Stick;
			break;
		default:
			name = null;
		}
		return name;
	}
}
