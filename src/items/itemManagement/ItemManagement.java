package items.itemManagement;

import items.interfaces.*;
import items.inventory.*;

import java.util.ArrayList;

public class ItemManagement implements IItemManagement{
	
//	ArrayList em que cada posicao armazena um objeto correspondente a um TIPO de item
	private ArrayList <IItems> inventory = new ArrayList <IItems>();
	
	public void startInventory() {
		inventory.add(new Flare("flare", 0));
		inventory.add(new Flash("flash", 0));
		inventory.add(new GeneralItems("fuel", 0));
		inventory.add(new powerUp("powerUp", 0));
		inventory.add(new GeneralItems("saltAmmo", 0));
		inventory.add(new Stick("stick", 0));
		
	}
	
//funcao a ser chamada para usar um item, dando como parametro, a posicao no ArrayList do item a ser usado	
	public void useItem(int place) {
		inventory.get(place).effect();		
	}

//funcao a ser chamada ao um item, dando como parametro, a posicao no ArrayList do item a ser recebido
	public void obtainItem(int place) {
		inventory.get(place).increase();
	}
	
//funcao que retorna a quantidade de um item
	public int displayNumber(int place) {
		return inventory.get(place).getNumber();
	}
	
//funcao que retorna o nome de um item	
	public String displayName(int place) {
		return inventory.get(place).getName();
	}
}