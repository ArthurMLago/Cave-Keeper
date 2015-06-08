package items.itemManagement;

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
 */
	public void useItem(int place) {
		inventory.get(place).effect();		
	}

/**
 * ao obter um novo item, chama-se tal metodo para aumentar sua quantidade
 */
	public void obtainItem(int place) {
		if(place >= 0 && place <= 4)
			inventory.get(place).increase();
	}
	
	public int displayNumber(int place) {
		return inventory.get(place).getNumber();
	}
	
	public void setNumber(int place, int number) {
		inventory.get(place).setNumber(number);
	}
	
	public String displayName(int place) {
		return inventory.get(place).getName();
	}
	
	public void printEverything() {
		int value;
		String name;
		for(int i = 0;i < inventory.size();i ++) {
			value = displayNumber(i);
			name = displayName(i);
			System.out.println("Nome: " + name + "  Quantidade: " + value);
		}
	}
}