package player;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;
import items.interfaces.IItemManagement;
import map.interfaces.IGameMap;
import monster.Interfaces.IMonster;
import gameController.*;

@ComponentInterface("<http://cave.com/player.IPlayerMax>")
public interface IPlayerMax extends Entidade, ISupports {
	
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
	public void connect(IMonster arg0, IItemManagement arg1, IGameMap arg2);
	public void checkLighter();
	public boolean useFlash();
	
}
