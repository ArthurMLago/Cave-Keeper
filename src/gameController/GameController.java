package gameController;

import items.interfaces.IItemManagement;
import map.ItemSpawn;
import map.MapGenerator;
import map.Position;
import map.interfaces.IGameMap;
import monster.Interfaces.IMonster;

import org.newdawn.slick.Input;

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
import items.itemManagement.*;
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
	private int fase = 1;

	
	
//	criacao do vetor de Itemspawns
	public void colocaItens() {
		ItemSpawn[] vetor = new ItemSpawn[50];
		for(int i = 0;i < 50;i++) {
			if(i >= 0 && i < 10) {
				vetor[i] = new ItemSpawn(ItemsList.Flare, 5);
			}
			if(i >= 10 && i < 20) {
				vetor[i] = new ItemSpawn(ItemsList.Flash, 5);
			}
			if(i >= 20 && i < 30) {
				vetor[i] = new ItemSpawn(ItemsList.Fuel, 5);
			}
			if(i >= 30 && i < 40) {
				vetor[i] = new ItemSpawn(ItemsList.SaltAmmo, 5);
			}
			if(i >= 40 && i < 50) {
				vetor[i] = new ItemSpawn(ItemsList.Stick, 5);
			}
		}
		
		MapGenerator.sharedInstance().setItemSpawnList(vetor);
		MapGenerator.sharedInstance().setNTraps(5);
	}
	
	
	private GameController() {
	}

	public void conectar(IMonster compMonster, IPlayerMax compPlayer,
			IItemManagement compItemManagement) {
		this.compMonster = compMonster;
		this.compPlayer = compPlayer;
		this.compItemManagement = compItemManagement;
		bootGameController();
	}

	private void bootGameController() {
		Position playerSpawn;

		// TODO: Instanciar map, player e monstros
		MapGenerator.sharedInstance().setMapHeight(20);
		MapGenerator.sharedInstance().setMapWidth(20);
		MapGenerator.sharedInstance().setWalkablePath(175);
		colocaItens();
		compMap = MapGenerator.sharedInstance().generateMap();
		playerSpawn = compMap.getSpawnPoint(compPlayer);
		compPlayer.setSpawnPointPlayer(playerSpawn.getX(), playerSpawn.getY());

		compMonster.connect(compPlayer, compMap);
		compMonster.generateMonsters(1);
		compMonster.setMonsterPosition(0);

		compPlayer.connect(compMonster, compItemManagement);

		// TODO: Inicializar as classes que correspondem a acoes do usuario.
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

		// TODO: Conectar as outras a��es no handler depois de instanciar
		
		/* Handler verifica se alguma tecla foi pressionada e executa a acao correspondente. */
		
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

		// TODO: Conectar mapVisual as outras a��es
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

	/* Eh chamado todo frame, ou seja, esta em loop infinito. */
	@Override
	public void update() {
		if(playerGameOver()) {
			compMapVisual.end();
			System.out.println("Voce morreu.");
		}	
		else if (playerTestWin()){
			compMapVisual.end();
			System.out.println("Voce ganhou.");
		}
		else {
			handler.handle(command);
		}
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

	/* Chamado toda vez que o player se move.
	 * Nao eh chamado quando se usa itens. */
	public void move() {
		compMonster.runMonstersActions(0);

		if (compMapVisual instanceof IAudioEffect) {
			((IAudioEffect) compMapVisual).playEffect(
					(float) (1-(compMonster.getDistance(0) / 10)), "footstep");
		}
	}
	
	public boolean playerTestWin() {
		if((compPlayer.getX() != compMonster.getX(0)) && (compPlayer.getY() != compMonster.getY(0)) && (compMonster.isMonstersAlive() == false))
			return true;
		else 
			return false;
	}
	
	public boolean playerGameOver() {
		if(compPlayer.getX() == compMonster.getX(0) && compPlayer.getY() == compMonster.getY(0))
			return true;
		else
			return false;
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
	public int release() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IItemManagement getItemManagement() {
		return compItemManagement;
	}
}
