package gameController;

//import map.*;
import map.MapGenerator;

public class GameController implements IGameController {
	private static final GameController sharedInstance = new GameController();;
	
	private GameController() { }
	
	public static GameController getSharedInstance() {
		return sharedInstance;
	}
	
	public static void main(String[] args) {

	}
	
	public IMap getMap() {
		return Map;
	}
	
	public IPlayer getPlayer() {
		return Player
	}

}
