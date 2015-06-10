package player;

import items.interfaces.IItemManagement;
import monster.Interfaces.IMonster;
import gameController.*;

public interface IPlayerMax extends Entidade {
	
	public void setSpawnPointPlayer(int x, int y);
	public void setLighter();
	public boolean move(char direction);
	public boolean shoot(char direction);
	public boolean useFlare();
	public boolean useStick();
	public int getX();
	public int getY();
	public char getFacing();
	public boolean getLighter();
	public void connect(IMonster arg0, IItemManagement arg1);
}
