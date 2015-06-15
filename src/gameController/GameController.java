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
import saveGame.IsaveManagement;
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
	private static int stageNumber = 1;
	private IGameMap compMap;
	private IMonster compMonster;
	private IPlayerMax compPlayer;
	private IItemManagement compItemManagement;
	private IMapVisual compMapVisual;
	private IIoComponent compIo;
	
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

	public void conectar(IMonster compMonster, IPlayerMax compPlayer, IItemManagement compItemManagement/*, IMapVisual compMapVisual*/) {
		this.compMonster = compMonster;
		this.compPlayer = compPlayer;
		this.compItemManagement = compItemManagement;
		/*this.compMapVisual = compMapVisual;*/
		bootGameController();
	}

	private void bootGameController() {
		IStage newStage = new Stage(stageNumber);
		newStage.connectToGameController();
		newStage.bootStage();
		
		compMonster = newStage.getMonster();
		compMap = newStage.getMap();
		compPlayer = newStage.getPlayer();

		compIo = new IoComponent();
		
		// Inicializa o componente visual
		compMapVisual = new MapVisual(compMap, compPlayer, compMonster, compIo, compItemManagement);
		
		compIo.connect(compMapVisual, compPlayer);
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
			stageNumber++;
			compMapVisual.end();
			bootGameController();
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
			compMapVisual.playFootstep((float) volume);
		}
	}
	
	public IPlayerMax getCompPlayer() {
		return compPlayer;
	}
	
	public IMonster getCompMonster() {
		return compMonster;
	}
	
	public IItemManagement getItemManagement() {
		return compItemManagement;
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
	
	public ArrayList<Object> getComponentsToSave() {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(compItemManagement);
		list.add(compPlayer);
		list.add(compMonster);
		list.add(compMap);		
		return list;
	}

}
