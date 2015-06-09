package player;

import items.excecoes.OutofItemsException;

public interface IPlayerAction {
	
	public void setLighter();
	public boolean move(char direction);
	public boolean shoot(char direction);
	public boolean useFlare();
	public boolean useStick();
	public boolean useFlash();
	
}