package map.events;

import items.interfaces.IItemManagement;
import map.Event;
import map.enumerations.EventType;

public class EventTrap extends Event{

	private IItemManagement item;
	
	/**
	 * Construtor padrão de um Evento de Trap, como todas as traps são iguais, o construtor apenas chama o construtor
	 * da superclasse com o parâmetro do tipo Trap.
	 */
	public EventTrap(IItemManagement item) {
		super(EventType.TRAP);
		this.item = item;
		
	}
	
	/**
	 * Método para aplicar os efeitos da Trap, apenas chama o método designado no componente de items.
	 */
	public void applyEffect(){
		item.itsaTrap();
	}

}
