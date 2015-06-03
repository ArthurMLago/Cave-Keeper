package player;

public interface IPlayerMax {
	
	public void setLighter();
	public boolean move(char direction);
	public boolean shoot(char direction);
	public void useFlare();
	public void useStick();
	public int getX();
	public int getY();
	public char getFacing();

}
