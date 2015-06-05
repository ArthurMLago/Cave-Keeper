package gameController;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import player.IPlayerAction;
import player.IPlayerMax;
import player.PlayerDownAction;
import visual.ActionHandler;
import visual.MapVisual;
import visual.MapVisualActionFlare;
import visual.MapVisualActionShoot;
import visual.interfaces.IActionMapVisual;
import visual.interfaces.IActionPlayer;
import visual.interfaces.IActionPlayerMapVisual;
import visual.interfaces.IMapVisual;
import anima.component.IRequires;
import anima.component.ISupports;
import anima.component.InterfaceType;
import map.GameMap;
//import map.*;
import map.MapGenerator;

public class GameController implements IGameController {
	private static final GameController sharedInstance = new GameController();;
	private IPlayerMax player;
	private GameMap map;
	private IMapVisual mapVisual;
	private Input command;
	private ActionHandler handler;
	private IActionPlayerMapVisual playerDown, playerFlare, playerLeft, playerRight,
			playerSetLighter, playerShootDown, playerShootLeft,
			playerShootRight, playerShootUp, playerStick, playerUp, playerWait;
	private ArrayList<Entidade> entidades;
	
	private GameController() {
		entidades = new ArrayList<Entidade>();
		
		//TODO: Instanciar map e Player
		
		//TODO: Instanciar as outras ações do player
		playerDown = new PlayerDownAction();
		playerDown.setKey(Input.KEY_DOWN);
		playerDown.connect((IPlayerAction) player);
		
		
		handler = new ActionHandler();
		handler.connect(playerDown);
		//TODO: Conectar as outras ações no handler depois de instanciar
		
		mapVisual = new MapVisual();
		playerDown.connect(mapVisual);
		//TODO: Conectar mapVisual as outras ações

		mapVisual.connect(this);
	}

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
