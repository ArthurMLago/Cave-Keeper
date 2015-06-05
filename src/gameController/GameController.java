package gameController;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import player.IPlayerMax;
import anima.component.IRequires;
import anima.component.ISupports;
import anima.component.InterfaceType;
import map.GameMap;
//import map.*;
import map.MapGenerator;

public class GameController implements IGameController {
	private static final GameController sharedInstance = new GameController();;
	
	private GameController() { }
	
	public static GameController getSharedInstance() {
		return sharedInstance;
	}
	
	public static void main(String[] args) {

	}

	@Override
	public int addRef() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInstanceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> T queryInterface(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> T queryInterface(String arg0,
			InterfaceType arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> IRequires<T> queryReceptacle(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int release() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPlayerMax getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameMap getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Entidade> getEntidades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCommand(Input command) {
		// TODO Auto-generated method stub
		
	}

}
