package gameController;

import map.interfaces.*;
import monster.Interfaces.IMonster;
import player.IPlayerMax;

public interface IStage {
	public void connectToGameController();
	public void bootStage();
	public IPlayerMax getPlayer();
	public IGameMap getMap();
	public IMonster getMonster();
}
