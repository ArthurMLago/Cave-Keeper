package map.events;

import gameController.GameController;
import map.Event;
import map.enumerations.EventType;

public class EventTrap extends Event{

	/**
	 * Construtor padrão de um Evento de Trap, como todas as traps são iguais, o construtor apenas chama o construtor
	 * da superclasse com o parâmetro do tipo Trap.
	 */
	public EventTrap() {
		super(EventType.TRAP);
		
	}
	
	/**
	 * Método para aplicar os efeitos da Trap, apenas chama o método designado no componente de items.
	 */
	public void applyEffect(){
		GameController.getSharedInstance().getItemManagement().itsaTrap();
	}

}
