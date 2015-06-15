package gameController;

import items.interfaces.IItemManagement;
import map.MapGenerator;
import map.Position;
import map.interfaces.*;
import monster.Interfaces.IMonster;
import player.IPlayerMax;

public class Stage implements IStage {
	private int StageID;
	private IGameMap compMap;
	private IMonster compMonster;
	private IPlayerMax compPlayer;
	private IItemManagement compItemManagement;
	
	public Stage(int StageID) {
		this.StageID = StageID;
	}
	
	public void connectToGameController() {
		this.compMonster = GameController.getSharedInstance().getCompMonster();
		this.compPlayer = GameController.getSharedInstance().getCompPlayer();
		this.compItemManagement = GameController.getSharedInstance().getItemManagement();
	}
	
	public void bootStage() {
		bootMap();
		bootPlayer();
		bootMonster();
	}
	
	public void bootPlayer() {
		Position playerSpawn = compMap.getSpawnPoint(compPlayer);
		compPlayer.setSpawnPointPlayer(playerSpawn.getX(), playerSpawn.getY());
		compPlayer.connect(compMonster, compItemManagement, compMap);		
	}
	
	public void bootMap() {
		MapGenerator.sharedInstance().setMapHeight(20);
		MapGenerator.sharedInstance().setMapWidth(20);
		MapGenerator.sharedInstance().setWalkablePath(175);
		MapGenerator.sharedInstance().connect(compItemManagement);
		GameController.getSharedInstance().colocaItens();
		compMap = MapGenerator.sharedInstance().generateMap();
	}
	
	public void bootMonster() {
		compMonster.connect(compPlayer, compMap);
		compMonster.generateMonsters(this.StageID);
		compMonster.setMonsterPosition(0);
	}
	
	public IPlayerMax getPlayer() {
		return compPlayer;
	}
	
	public IGameMap getMap() {
		return compMap;
	}
	
	public IMonster getMonster() {
		return compMonster;
	}
}