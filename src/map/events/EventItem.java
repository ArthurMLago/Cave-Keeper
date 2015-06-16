package map.events;

import map.enumerations.EventType;
import items.interfaces.IItemManagement;
import items.itemManagement.ItemsList;

/**
 * Classe que representa um evento de um item jogado no mapa.
 * @author ArthurMLago
 *
 */
public class EventItem extends Event {
	private static final long serialVersionUID = 1L;
	private ItemsList ItemType;
	private int Quantity;
	private IItemManagement item;

	/**
	 * Construtor padrão de um evento de item, recebe todas as informações necessarias para definir um evento
	 * @param ItemType Tipo do evento
	 * @param Quantity Quantidade
	 */
	public EventItem(ItemsList ItemType, int Quantity, IItemManagement item) {
		super(EventType.ITEM);
		this.ItemType = ItemType;
		this.Quantity = Quantity;
		this.item = item;
		
	}

	/**
	 * Método que aplica o efeito do evento, no caso, adiciona items no inventario do player.
	 */
	@Override
	public void applyEffect() {
		item.obtainItem(ItemType,Quantity);
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
