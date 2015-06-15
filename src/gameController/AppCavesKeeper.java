package gameController;
import player.*;
import visual.interfaces.*;
import visual.*;
import saveGame.*;
import monster.*;
import monster.Interfaces.*;
import map.*;
import items.*;
import items.itemManagement.*;
import items.interfaces.*;

import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;



public class AppCavesKeeper {
	public static void main(String argc[]) {
		
		try {
			IGlobalFactory componentFactory = ComponentContextFactory.createGlobalFactory();
			componentFactory.registerPrototype(Monster.class);
			componentFactory.registerPrototype(Player.class);
			componentFactory.registerPrototype(ItemManagement.class);
			componentFactory.registerPrototype(MapVisual.class);
			
			for(int i = 1; i <= 4; i++) {
				IMonster compMonster = componentFactory.createInstance("<http://cave.com/monster.Monster>");
				IPlayerMax compPlayer = componentFactory.createInstance("<http://cave.com/player.Player>");
				IItemManagement compItemManagement = componentFactory.createInstance("<http://cave.com/items.itemManagement.ItemManagement>");
				/*IMapVisual compMapVisual = componentFactory.createInstance("<http://cave.com/visual.MapVisual>");*/
				
				GameController.getSharedInstance().conectar(compMonster, compPlayer, compItemManagement, i/*, compMapVisual*/);
				System.out.println("passei aqui");
			}
			
		}
		catch(Exception e) { 
			e.printStackTrace();
			System.out.println("Exception");
		}
	}
}
