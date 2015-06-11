package map;

import items.itemManagement.ItemsList;

public class ItemSpawn {
	private ItemsList itemID;
	private int itemQuantity;

	public ItemSpawn(ItemsList itemID, int itemQuantity){
		this.itemID = itemID;
		this.itemQuantity = itemQuantity;
	}
	
	public ItemsList getItemID(){
		return itemID;
	}
	
	public int getItemQuantity(){
		return itemQuantity;
	}
	
}
