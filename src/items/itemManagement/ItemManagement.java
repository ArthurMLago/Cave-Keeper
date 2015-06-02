package items.itemManagement;

import items.interfaces.*;
import items.inventory.*;
import java.util.ArrayList;

/**
 * @author Vicente
 * Classe responsavel pela comunicacao entre outras classes e o inventario
 */
public class ItemManagement implements IItemManagement{
	
	/**
	 * ArrayList em que cada posicao armazena um objeto correspondente a um TIPO de item
	 */
	private ArrayList <IItems> inventory = new ArrayList <IItems>();


	public void startInventory() {
		inventory.add(new Flare("flare", 0));
		inventory.add(new Flash("flash", 0));
		inventory.add(new GeneralItems("fuel", 0));
		inventory.add(new powerUp("powerUp", 0));
		inventory.add(new GeneralItems("saltAmmo", 0));
		inventory.add(new Stick("stick", 0));
		
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
		inventory.get(place).increase();
	}
	
	public int displayNumber(int place) {
		return inventory.get(place).getNumber();
	}
	
	public String displayName(int place) {
		return inventory.get(place).getName();
	}
}