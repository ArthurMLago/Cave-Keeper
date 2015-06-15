package gameController;

import items.interfaces.IItemManagement;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import anima.component.ISupports;
import map.GameMap;	
import map.interfaces.IGameMap;
import player.IPlayerMax;
import saveGame.IsaveManagement;
import visual.interfaces.IMapVisual;
import monster.Interfaces.*;

public interface IGameController {
	public void update();
	public void move();
	public void conectar(IMonster compMonster, IPlayerMax compPlayer, IItemManagement compItemManagement, IsaveManagement svg, int f/*, IMapVisual compMapVisual*/);
	public boolean playerTestWin();
	public boolean playerGameOver();
}