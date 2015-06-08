package gameController;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import anima.component.ISupports;
import map.GameMap;	
import player.IPlayerMax;

public interface IGameController extends ISupports{
	public IPlayerMax getPlayer();
	public GameMap getMap();
	public Entidade getEntidades();
	public void update();
	public void setCommand(Input command);
}
