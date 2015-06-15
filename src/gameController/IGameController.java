package gameController;

import items.interfaces.IItemManagement;

import java.util.ArrayList;

import player.IPlayerMax;
import monster.Interfaces.*;

public interface IGameController {
	public void update();
	public void move();
	public void conectar(IMonster compMonster, IPlayerMax compPlayer, IItemManagement compItemManagement/*, IMapVisual compMapVisual*/);
	public boolean playerTestWin();
	public boolean playerGameOver();
	public IPlayerMax getCompPlayer();
	public IMonster getCompMonster();
	public IItemManagement getItemManagement();
}