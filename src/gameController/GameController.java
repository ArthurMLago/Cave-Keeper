package gameController;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import player.IPlayerAction;
import player.IPlayerMax;
import player.Player;
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
import map.Position;
import monster.Monster;

public class GameController implements IGameController {
	private static final GameController sharedInstance = new GameController();
	private Entidade player;
	private GameMap map;
	private IMapVisual mapVisual;
	private Input command;
	private ActionHandler handler;
	private IActionPlayerMapVisual playerFlare,
			playerSetLighter, playerShootDown, playerShootLeft,
			playerShootRight, playerShootUp;
	private IActionPlayer playerDown, playerLeft, playerRight, playerUp, playerStick, playerWait;
	private Entidade entidades;
	
	private GameController() { }
	
	private void bootGameController(int fase) {
		Position playerSpawn;
		
		//TODO: Instanciar map, player e monstros
		map = MapGenerator.sharedInstance().generateMap();
		playerSpawn = map.getSpawnPoint(player);
		player = new Player(playerSpawn.x, playerSpawn.y);
		entidades = new Monster(fase);
		
		//TODO: Instanciar as outras a��es do player
		playerDown =  new PlayerDownAction();
		playerDown.setKey(Input.KEY_DOWN);
		playerDown.connect((IPlayerAction) player);
		
		playerLeft =  new PlayerLeftAction();
		playerLeft.setKey(Input.KEY_LEFT);
		playerLeft.connect((IPlayerAction) player);
		
		playerRight = new PlayerRightAction();
		playerRight.setKey(Input.KEY_RIGHT);
		playerRight.connect((IPlayerAction) player);
		
		playerUp = new PlayerUpAction();
		playerUp.setKey(Input.KEY_UP);
		playerUp.connect((IPlayerAction) player);
		
		playerShootDown = new PlayerShootDownAction();
		playerShootDown.setKey(Input.KEY_S);
		playerShootDown.connect((IPlayerAction) player);
		
		playerShootLeft = new PlayerShootLeftAction();
		playerShootLeft.setKey(Input.KEY_A);
		playerShootLeft.connect((IPlayerAction) player);
		
		playerShootRight = new PlayerShootRightAction();
		playerShootRight.setKey(Input.KEY_D);
		playerShootRight.connect((IPlayerAction) player);
		
		playerShootUp = new PlayerShootUpAction();
		playerShootUp.setKey(Input.KEY_W);
		playerShootUp.connect((IPlayerAction) player);
		
		playerFlare = new PlayerFlareAction();
		playerFlare.setKey(Input.KEY_R);
		playerFlare.connect((IPlayerAction) player);
		
		playerStick = new PlayerStickAction();
		playerStick.setKey(Input.KEY_E);
		playerStick.connect((IPlayerAction) player);
		
		playerWait = new PlayerWaitAction();
		playerWait.setKey(Input.KEY_G);
		playerWait.connect((IPlayerAction) player);
		
		//TODO: Conectar as outras a��es no handler depois de instanciar
		
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
		
		//TODO: Conectar mapVisual as outras a��es
		
		mapVisual = new MapVisual();
		playerShootDown.connect(mapVisual);
		playerShootUp.connect(mapVisual);
		playerShootLeft.connect(mapVisual);
		playerShootRight.connect(mapVisual);
		playerFlare.connect(mapVisual);
		
		//TODO: Conectar o mapVisual ao gameController
		mapVisual.connect(this);
	}

	public static GameController getSharedInstance() {
		return sharedInstance;
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
	public Entidade getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public GameMap getMap() {
		// TODO Auto-generated method stub
		return map;
	}

	@Override
	public Entidade getEntidades() {
		// TODO Auto-generated method stub
		return entidades;
	}

	@Override
	public void update() {
		handler.handle(command);
	}

	@Override
	public void setCommand(Input command) {
		this.command = command;
	}
}