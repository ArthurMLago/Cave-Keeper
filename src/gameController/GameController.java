package gameController;

//import map.*;
import map.MapGenerator;

public class GameController {
	private static final GameController sharedInstance = new GameController();;
	
	private GameController() { }
	
	public static GameController getSharedInstance() {
		return sharedInstance;
	}
	
	public static void main(String[] args) {
		MapGenerator.sharedInstance().generateMap();
		
		

	}

}
