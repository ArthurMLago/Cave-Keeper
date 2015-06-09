package map.events;

import gameController.GameController;
import map.Event;
import items.itemManagement.ItemsList;

public class EventItem extends Event {
	private ItemsList ItemType;
	private int Quantity;

	public EventItem(ItemsList ItemType, int Quantity) {
		this.ItemType = ItemType;
		this.Quantity = Quantity;
	}

	@Override
	public void applyEffect() {
		GameController.getSharedInstance().getItemManagement().obtainItem(ItemType,Quantity);
	}
	
	

}
