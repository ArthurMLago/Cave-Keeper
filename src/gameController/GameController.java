package gameController;

//import map.*;
import map.MapGenerator;

public class GameController {

	public static void main(String[] args) {
		MapGenerator.sharedInstance().generateMap();

	}

}
