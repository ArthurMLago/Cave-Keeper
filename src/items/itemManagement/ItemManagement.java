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

import anima.annotation.Component;
import anima.component.base.ComponentBase;

/**
 * @author Vicente
 * @author Felipe Moret
 * Classe responsavel pela comunicacao entre outras classes e o inventario
 */
@Component(id="<http://santanvarzea.com/items.itemManagement.ItemManagement>", provides={"<http://santanvarzea.com/items.interfaces.IItemManagement>"})
public class ItemManagement extends ComponentBase implements IItemManagement, Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * ArrayList em que cada posicao armazena um objeto correspondente a um TIPO de item
	 */
	private ArrayList <IItems> inventory = new ArrayList <IItems>();


	public ItemManagement() {
		inventory.add(new Flare("flare", 0));
		inventory.add(new Flash("flash", 0));
		inventory.add(new GeneralItems("fuel", 0));
		inventory.add(new GeneralItems("saltAmmo", 0));
		inventory.add(new Stick("stick", 0));
		
	}
	
	public void startInventoryNumbers(ArrayList<Integer> quantities) {
		for(int i = 0; i < quantities.size(); i++) {
			inventory.get(i).setNumber(quantities.get(i));
		}
	}
	
/**
 * @param posicao do ArrayList em que se encontra o tipo do item
 * chama o metodo que faz o efeito do item
 * @throws OutofItemsException 
 */
	public void useItem(ItemsList place) throws OutofItemsException {
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
		default:
			/*exception*/
		}
		inventory.get(position).effect();		
	}

/**
 * ao obter um novo item, chama-se tal metodo para aumentar sua quantidade
 */
	public void obtainItem(ItemsList place) {
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
		default:
			/*exception*/
		}
		inventory.get(position).increase();
	}
	
	public int displayNumber(ItemsList place) {
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
		default:
			/*exception*/
		}
		return inventory.get(position).getNumber();
	}
	
	public void setNumber(ItemsList place, int number) {
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
		default:
			/*exception*/
		}
		inventory.get(position).setNumber(number);
	}
	
	public String displayName(ItemsList place) {
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
		default:
			/*exception*/
		}
		return inventory.get(position).getName();
	}
	
//	public void printEverything() {
//		int value;
//		String name;
//		for(int i = 0;i < inventory.size();i ++) {
//			value = displayNumber(i);
//			name = displayName(i);
//			System.out.println("Nome: " + name + "  Quantidade: " + value);
//		}
//	}
}