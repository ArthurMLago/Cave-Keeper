package gameController;

import ioComponent.IoComponent;
import ioComponent.interfaces.IIoComponent;
import items.interfaces.IItemManagement;
import map.ItemSpawn;
import map.MapGenerator;
import map.Position;
import map.interfaces.IGameMap;
import monster.Interfaces.IMonster;
import player.IPlayerMax;
import saveGame.IsaveGame;
import visual.MapVisual;
import visual.interfaces.IAudioEffect;
import visual.interfaces.IMapVisual;
import items.itemManagement.*;
import anima.component.ISupports;
import java.util.ArrayList;
import saveGame.saveGame;

/**
 * Componente que faz a conexao dos outros componentes
 * 
 * @author MiguelHenrique
 *
 */

public class GameController implements IGameController {
	private static final GameController sharedInstance = new GameController();

	private IGameMap compMap;
	private IMonster compMonster;
	private IPlayerMax compPlayer;
	private IItemManagement compItemManagement;
	private IMapVisual compMapVisual;
	private IIoComponent compIo;
	private IsaveGame compSave;
	private int fase;
	
//	criacao do vetor de Itemspawns
	public void colocaItens() {
		ItemSpawn[] vetor = new ItemSpawn[25];
		for(int i = 0;i < 50;i++) {
			if(i >= 0 && i < 5) {
				vetor[i] = new ItemSpawn(ItemsList.Flare, 5);
			}
			if(i >= 5 && i < 10) {
				vetor[i] = new ItemSpawn(ItemsList.Flash, 5);
			}
			if(i >= 10 && i < 15) {
				vetor[i] = new ItemSpawn(ItemsList.Fuel, 5);
			}
			if(i >= 15 && i < 20) {
				vetor[i] = new ItemSpawn(ItemsList.SaltAmmo, 5);
			}
			if(i >= 20 && i < 25) {
				vetor[i] = new ItemSpawn(ItemsList.Stick, 5);
			}
		}
		
		MapGenerator.sharedInstance().setItemSpawnList(vetor);
		MapGenerator.sharedInstance().setNTraps(5);
	}
	
	
	private GameController() {
	}

	public void conectar(IMonster compMonster, IPlayerMax compPlayer, IItemManagement compItemManagement, int f/*, IMapVisual compMapVisual*/) {
		this.compMonster = compMonster;
		this.compPlayer = compPlayer;
		this.compItemManagement = compItemManagement;
		/*this.compMapVisual = compMapVisual;*/
		this.fase = f;
		bootGameController();
	}

	private void bootGameController() {
		Position playerSpawn;

		
		// Inicializa o componente mapa.
		MapGenerator.sharedInstance().setMapHeight(20);
		MapGenerator.sharedInstance().setMapWidth(20);
		MapGenerator.sharedInstance().setWalkablePath(175);
		MapGenerator.sharedInstance().connect(compItemManagement);
		colocaItens();
		compMap = MapGenerator.sharedInstance().generateMap();
		
		
		// Inicializa o componente player
		playerSpawn = compMap.getSpawnPoint(compPlayer);
		compPlayer.setSpawnPointPlayer(playerSpawn.getX(), playerSpawn.getY());
		compPlayer.connect(compMonster, compItemManagement, compMap);
		
		// Inicializa o componente monster
		compMonster.connect(compPlayer, compMap);
		compMonster.generateMonsters(this.fase);
		compMonster.setMonsterPosition(0);		

		compIo = new IoComponent();
		
		// Inicializa o componente visual
		compMapVisual = new MapVisual(compMap, compPlayer, compMonster, compIo);
		
		compIo.connect(compMapVisual, compPlayer, compSave);
		compIo.setActions();
		
		compMapVisual.start();
	}

	public static GameController getSharedInstance() {
		return sharedInstance;
	}

	/* Eh chamado todo frame, ou seja, esta, praticamente, em loop infinito. */
	@Override
	public void update() {
		if(playerGameOver()) {
			System.out.println("Entrou no game over.");
			compMapVisual.end();
			System.out.println("Voce morreu.");
		}	
		else if (playerTestWin()){
			System.out.println("entrou no test win");
			compMapVisual.end();
			System.out.println("Voce ganhou.");
		}
		else {
//			System.out.println("entrou no handler");
			compIo.executeAction();
		}
	}

	/* Chamado toda vez que o player se move.
	 * Nao eh chamado quando se usa itens. */
	public void move() {
		compMonster.runMonstersActions(0);
		double volume = 1-(compMonster.getDistance(0) / 10);
		
		if (volume < 0) {
			volume = 0;
		}

		if (compMapVisual instanceof IAudioEffect) {
			((IAudioEffect) compMapVisual).playEffect((float) volume, "footstep");
		}
	}
	
	public boolean playerTestWin() {
		if(compMonster.isMonstersAlive() == false)
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
	
	public ArrayList<ISupports> getComponentsToSave() {
		ArrayList<ISupports> list = new ArrayList<ISupports>();
		list.add(compMap);
		list.add(compMonster);
		list.add(compPlayer);
		list.add(compItemManagement);
		return list;
	}
	
	public void saveEverything() {
		
	}
}
