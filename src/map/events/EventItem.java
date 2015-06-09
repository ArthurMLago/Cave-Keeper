package map.events;

import gameController.GameController;
import map.Event;
import items.itemManagement.ItemsList;

/**
 * Classe que representa um evento de um item jogado no mapa.
 * @author ArthurMLago
 *
 */
public class EventItem extends Event {
	private ItemsList ItemType;
	private int Quantity;

	/**
	 * Construtor padrão de um evento de item, recebe todas as informações necessarias para definir um evento
	 * @param ItemType Tipo do evento
	 * @param Quantity Quantidade
	 */
	public EventItem(ItemsList ItemType, int Quantity) {
		this.ItemType = ItemType;
		this.Quantity = Quantity;
	}

	/**
	 * Método que aplica o efeito do evento, no caso, adiciona items no inventario do player.
	 */
	@Override
	public void applyEffect() {
		GameController.getSharedInstance().getItemManagement().obtainItem(ItemType,Quantity);
	}
	
	/**
	 * Getter basico para obter o tipo de item do evento
	 * @return Um tipo do enumerado ItemsList para o tipo de item que esta jogado no mapa.
	 */
	public ItemsList getItemType(){
		return ItemType;
	}
	
	/**
	 * Getter basico para obter a quantidade do item que esta jogado no mapa.
	 * @return A quantidade do item
	 */
	public int getQuantity(){
		return Quantity;
	}
	
	

}
