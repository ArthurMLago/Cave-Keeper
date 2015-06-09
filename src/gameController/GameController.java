package gameController;

import items.excecoes.OutofItemsException;
import items.interfaces.IItemManagement;
import items.itemManagement.ItemsList;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import player.*;
import visual.interfaces.*;
import visual.*;
import saveGame.*;
import monster.*;
import monster.Interfaces.*;
import map.*;
import map.interfaces.*;
import anima.annotation.Component;
import anima.component.IRequires;
import anima.component.ISupports;
import anima.component.InterfaceType;
import anima.component.base.ComponentBase;

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
			playerStick, playerWait;
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
	}

	private void bootGameController(int fase) {
		Position playerSpawn;

		// TODO: Instanciar map, player e monstros
		compMap = MapGenerator.sharedInstance().generateMap();
		playerSpawn = compMap.getSpawnPoint(compPlayer);
		compPlayer.setSpawnPointPlayer(playerSpawn.getX(), playerSpawn.getY());

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

		// TODO: Conectar mapVisual as outras ações

		try {
			compMapVisual = new MapVisual();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		playerShootDown.connect(compMapVisual);
		playerShootUp.connect(compMapVisual);
		playerShootLeft.connect(compMapVisual);
		playerShootRight.connect(compMapVisual);
		playerFlare.connect(compMapVisual);
	}

	public static GameController getSharedInstance() {
		return sharedInstance;
	}

	@Override
	public void update() {
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

	public void move() {
		if (compMonster.isMonstersAlive()) {
			compMonster.runMonstersActions(0);
			if (compPlayer.getLighter()) {
				try {
					compItemManagement.useItem(ItemsList.Fuel);
				} catch (OutofItemsException e) {
					compPlayer.setLighter();
				}
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
