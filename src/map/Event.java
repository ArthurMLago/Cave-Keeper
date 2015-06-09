package map;
import map.enumerations.EventType;

/**
 * Classe abstrata para Eventos do mapa, todos os outros tipos de Evento herdam desta classe, e portanto implementam
 * o método {@link #applyEffects() applyEffects()} que aplica os efeitos do evento no resto do jogo.
 * @author ArthurMLago
 *
 */
public abstract class Event {
	
	private EventType type;
	
	/**
	 * Método que aplica os efeitos do evento no resto do jogo.Será reescrito para cada tipo de evento.
	 */
	public abstract void applyEffect();
	
	/**
	 * Getter basico do tipo do evento.
	 * @return Um enumerado {@link map.enumerations.EventType EventType} para o tipo desse evento
	 */
	public EventType getType(){
		return type;
	}
	
	/**
	 * Método para descartar esse evento, ele não será mais ativado
	 */
	public void discardEvent(){
		
	}

}
