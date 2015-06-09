package player;

import gameController.*;

public interface IPlayerMax extends Entidade {
	
	public void setSpawnPointPlayer(int x, int y);
	public void setLighter();
	public boolean move(char direction);
	public boolean shoot(char direction);
	public void useFlare();
	public void useStick();
	public int getX();
	public int getY();
	public int getFacing();
	public boolean getLighter();
}
