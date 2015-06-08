package gameController;

import items.interfaces.IItemManagement;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import anima.component.ISupports;
import map.GameMap;	
import map.interfaces.IGameMap;
import player.IPlayerMax;
import monster.Interfaces.*;

public interface IGameController extends ISupports{
	public IPlayerMax getPlayer();
	public IGameMap getMap();
	public IMonster getEntidades();
	public void update();
	public void setCommand(Input command);
	public void move();
	public IItemManagement getItemManagement();
	public void conectar(IMonster compMonster, IPlayerMax compPlayer, IItemManagement compItemManagement);
}