package saveGame;

import items.itemManagement.ItemManagement;

import java.util.ArrayList;

import saveGame.saveGame;
import anima.component.ISupports;

public class saveManagement {

	public void serializeEverything(ArrayList <ISupports> components) {
		saveGame <ISupports> TheSaver = new saveGame<ISupports>();
		
		for(int i = 0; i < components.size(); i++) {
			// serialiaza cada obejto das casa de components
			switch(i) {
			case 0:
				TheSaver.saveState(components.get(i), "items");
				break;
			case 1:
				TheSaver.saveState(components.get(i), "player");
				break;
			case 2:
				TheSaver.saveState(components.get(i), "monster");
				break;
			case 3:
				TheSaver.saveState(components.get(i), "map");
				break;
			default:
				System.out.println("ops");	
			}
		}
	}
	
	public void deserializeEverything(ArrayList <ISupports> components) {
		saveGame <ISupports> TheSaver = new saveGame<ISupports>();
		
		for(int i = 0;i < components.size() ; i++) {
			// serialiaza cada obejto das casa de components
			switch(i) {
			case 0:
				components.add(TheSaver.obtainState("items"));
				break;
			case 1:
				components.add(TheSaver.obtainState("player"));
				break;
			case 2:
				components.add(TheSaver.obtainState("monster"));
				break;
			case 3:
				components.add(TheSaver.obtainState("map"));
			default:
				System.out.println("ops");	
			}
		}
	}
}
