package map;
import map.enumerations.EventType;

public abstract class Event {
	
	public EventType types;
	
	public abstract void applyEffect();

}
