package gameController;

import items.interfaces.IItemManagement;
import monster.Interfaces.IMonster;
import player.IPlayerMax;
import saveGame.IsaveManagement;

public interface IGameController {
	public void update();
	public void move();
	public void conectar(IMonster compMonster, IPlayerMax compPlayer, IItemManagement compItemManagement, IsaveManagement svg, int f/*, IMapVisual compMapVisual*/);
	public boolean playerTestWin();
	public boolean playerGameOver();
}