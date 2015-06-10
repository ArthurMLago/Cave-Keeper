package gameController;

import items.excecoes.OutofItemsException;
import items.interfaces.IItemManagement;
import items.itemManagement.ItemsList;
import map.MapGenerator;
import map.Position;
import map.interfaces.IGameMap;
import monster.Interfaces.IMonster;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import player.IPlayerAction;
import player.IPlayerMax;
import player.PlayerDownAction;
import player.PlayerFlareAction;
import player.PlayerLeftAction;
import player.PlayerRightAction;
import player.PlayerSetLighterAction;
import player.PlayerShootDownAction;
import player.PlayerShootLeftAction;
import player.PlayerShootRightAction;
import player.PlayerShootUpAction;
import player.PlayerStickAction;
import player.PlayerUpAction;
import player.PlayerWaitAction;
import visual.ActionHandler;
import visual.MapVisual;
import visual.interfaces.IActionPlayer;
import visual.interfaces.IActionPlayerMapVisual;
import visual.interfaces.IAudioEffect;
import visual.interfaces.IMapVisual;
import anima.component.IRequires;
import anima.component.ISupports;
import anima.component.InterfaceType;

/**
 * Componente que faz a conexao dos outros componentes
 * 
 * @author MiguelHenrique
 *
 */

public class GameController implements IGameController {
	private static final GameController sharedInstance = new GameController();
	private Input command;
	private ActionHandler handler;
	private IActionPlayerMapVisual playerFlare, playerSetLighter,
			playerShootDown, playerShootLeft, playerShootRight, playerShootUp;
	private IActionPlayer playerDown, playerLeft, playerRight, playerUp,
			playerStick, playerWait, playerLighter;
	private IGameMap compMap;
	private IMonster compMonster;
	private IPlayerMax compPlayer;
	private IItemManagement compItemManagement;
	private IMapVisual compMapVisual;

	private GameController() {
	}

	public void conectar(IMonster compMonster, IPlayerMax compPlayer,
			IItemManagement compItemManagement) {
		this.compMonster = compMonster;
		this.compPlayer = compPlayer;
		this.compItemManagement = compItemManagement;
		bootGameController(1);
	}

	private void bootGameController(int fase) {
		Position playerSpawn;

		// TODO: Instanciar map, player e monstros
		MapGenerator.sharedInstance().setMapHeight(20);
		MapGenerator.sharedInstance().setMapWidth(20);
		MapGenerator.sharedInstance().setWalkablePath(175);
		compMap = MapGenerator.sharedInstance().generateMap();
		playerSpawn = compMap.getSpawnPoint(compPlayer);
		compPlayer.setSpawnPointPlayer(playerSpawn.getX(), playerSpawn.getY());

		compMonster.connect(compPlayer, compMap);
		compMonster.generateMonsters(1);
		compMonster.setMonsterPosition(0);

		compPlayer.connect(compMonster, compItemManagement);

		// TODO: Instanciar as outras ações do player
		playerDown = new PlayerDownAction();
		playerDown.setKey(Input.KEY_DOWN);
		playerDown.connect((IPlayerAction) compPlayer);

		playerLeft = new PlayerLeftAction();
		playerLeft.setKey(Input.KEY_LEFT);
		playerLeft.connect((IPlayerAction) compPlayer);

		playerRight = new PlayerRightAction();
		playerRight.setKey(Input.KEY_RIGHT);
		playerRight.connect((IPlayerAction) compPlayer);

		playerUp = new PlayerUpAction();
		playerUp.setKey(Input.KEY_UP);
		playerUp.connect((IPlayerAction) compPlayer);

		playerShootDown = new PlayerShootDownAction();
		playerShootDown.setKey(Input.KEY_S);
		playerShootDown.connect((IPlayerAction) compPlayer);

		playerShootLeft = new PlayerShootLeftAction();
		playerShootLeft.setKey(Input.KEY_A);
		playerShootLeft.connect((IPlayerAction) compPlayer);

		playerShootRight = new PlayerShootRightAction();
		playerShootRight.setKey(Input.KEY_D);
		playerShootRight.connect((IPlayerAction) compPlayer);

		playerShootUp = new PlayerShootUpAction();
		playerShootUp.setKey(Input.KEY_W);
		playerShootUp.connect((IPlayerAction) compPlayer);

		playerFlare = new PlayerFlareAction();
		playerFlare.setKey(Input.KEY_R);
		playerFlare.connect((IPlayerAction) compPlayer);

		playerStick = new PlayerStickAction();
		playerStick.setKey(Input.KEY_E);
		playerStick.connect((IPlayerAction) compPlayer);

		playerWait = new PlayerWaitAction();
		playerWait.setKey(Input.KEY_G);
		playerWait.connect((IPlayerAction) compPlayer);
		
		playerLighter = new PlayerSetLighterAction();
		playerLighter.setKey(Input.KEY_L);
		playerLighter.connect((IPlayerAction) compPlayer);

		// TODO: Conectar as outras ações no handler depois de instanciar

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
		handler.connect(playerLighter);

		// TODO: Conectar mapVisual as outras ações
		compMapVisual = new MapVisual();
		compMapVisual.connect(this);

		playerShootDown.connect(compMapVisual);
		playerShootUp.connect(compMapVisual);
		playerShootLeft.connect(compMapVisual);
		playerShootRight.connect(compMapVisual);
		playerFlare.connect(compMapVisual);
		
		compMapVisual.start();
	}

	public static GameController getSharedInstance() {
		return sharedInstance;
	}

	@Override
	public void update() {
		if (handler != null)
			handler.handle(command);
	}

	@Override
	public void setCommand(Input command) {
		this.command = command;
	}

	public IPlayerMax getPlayer() {
		return this.compPlayer;
	}

	public IGameMap getMap() {
		return this.compMap;
	}

	public IMonster getEntidades() {
		return this.compMonster;
	}

	public IMapVisual getMapVisual() {
		return compMapVisual;
	}

	public void move() {
		if (compMonster.isMonstersAlive()) {
			compMonster.runMonstersActions(0);
			compPlayer.checkLighter();

			if (compMapVisual instanceof IAudioEffect) {

				((IAudioEffect) compMapVisual).playEffect(
						(float) (1-(compMonster.getDistance(0) / 10)), "footstep");
			}
			
			if(compPlayer.getX() == compMonster.getX(0) && compPlayer.getY() == compMonster.getY(0)){
				compMapVisual.end();
			}
		}
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
	public IItemManagement getItemManagement() {
		return compItemManagement;
	}
}
