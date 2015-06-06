package gameController;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import player.IPlayerAction;
import player.IPlayerMax;
import player.PlayerDownAction;
import player.PlayerUpAction;
import player.PlayerLeftAction;
import player.PlayerRightAction;
import player.PlayerSetLighterAction;
import player.PlayerShootDownAction;
import player.PlayerShootUpAction;
import player.PlayerShootLeftAction;
import player.PlayerShootRightAction;
import player.PlayerStickAction;
import player.PlayerFlareAction;
import player.PlayerUpAction;
import player.PlayerWaitAction;
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
	
	private GameController() { }
	
	private void bootGameController() {
		entidades = new ArrayList<Entidade>();
		
		//TODO: Instanciar map e Player
	
		
		//TODO: Instanciar as outras ações do player
		playerDown = (IActionPlayerMapVisual) new PlayerDownAction();
		playerDown.setKey(Input.KEY_DOWN);
		playerDown.connect((IPlayerAction) player);
		
		playerLeft = (IActionPlayerMapVisual) new PlayerLeftAction();
		playerLeft.setKey(Input.KEY_LEFT);
		playerLeft.connect((IPlayerAction) player);
		
		playerRight = (IActionPlayerMapVisual) new PlayerRightAction();
		playerRight.setKey(Input.KEY_RIGHT);
		playerRight.connect((IPlayerAction) player);
		
		playerUp = (IActionPlayerMapVisual) new PlayerUpAction();
		playerUp.setKey(Input.KEY_UP);
		playerUp.connect((IPlayerAction) player);
		
		playerShootDown = (IActionPlayerMapVisual) new PlayerShootDownAction();
		playerShootDown.setKey(Input.KEY_S);
		playerShootDown.connect((IPlayerAction) player);
		
		playerShootLeft = (IActionPlayerMapVisual) new PlayerShootLeftAction();
		playerShootLeft.setKey(Input.KEY_A);
		playerShootLeft.connect((IPlayerAction) player);
		
		playerShootRight = (IActionPlayerMapVisual) new PlayerShootRightAction();
		playerShootRight.setKey(Input.KEY_D);
		playerShootRight.connect((IPlayerAction) player);
		
		playerShootUp = (IActionPlayerMapVisual) new PlayerShootUpAction();
		playerShootUp.setKey(Input.KEY_W);
		playerShootUp.connect((IPlayerAction) player);
		
		playerFlare = (IActionPlayerMapVisual) new PlayerFlareAction();
		playerFlare.setKey(Input.KEY_R);
		playerFlare.connect((IPlayerAction) player);
		
		playerStick = (IActionPlayerMapVisual) new PlayerStickAction();
		playerStick.setKey(Input.KEY_E);
		playerStick.connect((IPlayerAction) player);
		
		playerWait = (IActionPlayerMapVisual) new PlayerWaitAction();
		playerWait.setKey(Input.KEY_G);
		playerWait.connect((IPlayerAction) player);
		
		//TODO: Conectar as outras ações no handler depois de instanciar
		
		handler = new ActionHandler();
		handler.connect(playerDown);
		handler.connect(playerUp);
		handler.connect(playerLeft);
		handler.connect(playerRight);
		handler.connect(playerShootDown);
		handler.connect(playerShootUp);
		handler.connect(playerShootLeft);
		handler.connect(playerShootRight);
		handler.connect(playerFlare);
		handler.connect(playerStick);
		handler.connect(playerWait);
		
		//TODO: Conectar mapVisual as outras ações
		
		mapVisual = new MapVisual();
		playerDown.connect(mapVisual);
		playerUp.connect(mapVisual);
		playerRight.connect(mapVisual);
		playerLeft.connect(mapVisual);
		playerShootDown.connect(mapVisual);
		playerShootUp.connect(mapVisual);
		playerShootLeft.connect(mapVisual);
		playerShootRight.connect(mapVisual);
		playerFlare.connect(mapVisual);
		playerStick.connect(mapVisual);
		playerWait.connect(mapVisual);
		
		//TODO: Conectar o mapVisual ao gameController
		mapVisual.connect(this);
	}

	public static GameController getSharedInstance() {
		return sharedInstance;
	}

	public static void main(String[] args) {
		int fase;
		
		
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
		return player;
	}

	@Override
	public GameMap getMap() {
		// TODO Auto-generated method stub
		return map;
	}

	@Override
	public ArrayList<Entidade> getEntidades() {
		// TODO Auto-generated method stub
		return entidades;
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
