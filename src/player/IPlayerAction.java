package player;

public interface IPlayerAction {
	
	public void setLighter();
	public boolean move(char direction);
	public boolean shoot(char direction);
	public void useFlare();
	public void useStick();
	
}