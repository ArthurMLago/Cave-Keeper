package items.itemManagement;

import items.interfaces.IItems;
import items.inventario.Flare;
import items.inventario.Flash;
import items.inventario.GeneralItems;
import items.inventario.Stick;
import items.inventario.powerUp;

import java.util.ArrayList;


public class ItemManagement {
	
	ArrayList <IItems> inventory = new ArrayList <IItems>();
	
	public void startInventory() {
		inventory.add(new Flare("flare", 0));
		inventory.add(new Flash("flash", 0));
		inventory.add(new GeneralItems("fuel", 0));
		inventory.add(new powerUp("powerUp", 0));
		inventory.add(new GeneralItems("saltAmmo", 0));
		inventory.add(new Stick("stick", 0));
		
	}
	
	public void useItem(int place) {
		inventory.get(place).effect();		
	}
	
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