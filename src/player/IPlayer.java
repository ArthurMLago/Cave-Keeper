package player;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;

public interface IPlayer {

	public String getTipo();
	public int getImage();
	public void setSpawnPointPlayer(int x, int y);
	public int getX();
	public int getY();
	public int getFacing();
	public boolean getLighter();
	public void setLighter();
	public void setLighter(boolean state);
	public boolean move(char direction);
	public boolean shoot(char direction);
	public void useFlare();
	public void useStick();
	
}
