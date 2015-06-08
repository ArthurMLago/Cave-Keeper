package gameController;

import items.interfaces.IItemManagement;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import player.*;
import visual.interfaces.*;
import visual.*;
import saveGame.*;
import monster.*;
import monster.Interfaces.*;
import map.*;
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

public class GameController {
	private static final GameController sharedInstance = new GameController();
	private Input command;
	private ActionHandler handler;
	private IActionPlayerMapVisual playerFlare, playerSetLighter,
			playerShootDown, playerShootLeft, playerShootRight, playerShootUp;
	private IActionPlayer playerDown, playerLeft, playerRight, playerUp,
			playerStick, playerWait;

	private IMonster compMonster;
	private IPlayer compPlayer;
	private IItemManagement compItemManagement;
	
	private GameController() {
	}

	public void conectar(IMonster compMonster, IPlayer compPlayer, IItemManagement compItemManagement) {
		this.compMonster = compMonster;
		this.compPlayer = compPlayer;
		this.compItemManagement = compItemManagement;
	}
	
	private void bootGameController(int fase) {
		Position playerSpawn;

		// TODO: Instanciar map, player e monstros
		map = MapGenerator.sharedInstance().generateMap();
		player = new Player();
		compMonster = new Monster();
		playerSpawn = map.getSpawnPoint(player, compMonster);
		player.setSpawnPointPlayer(playerSpawn.getX(), playerSpawn.getY());

		// TODO: Instanciar as outras ações do player
		playerDown = new PlayerDownAction();
		playerDown.setKey(Input.KEY_DOWN);
		playerDown.connect((IPlayerAction) player);

		playerLeft = new PlayerLeftAction();
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

		mapVisual = new MapVisual();
		playerShootDown.connect(mapVisual);
		playerShootUp.connect(mapVisual);
		playerShootLeft.connect(mapVisual);
		playerShootRight.connect(mapVisual);
		playerFlare.connect(mapVisual);

		// TODO: Conectar o mapVisual ao gameController
		mapVisual.connect(this);
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
}
