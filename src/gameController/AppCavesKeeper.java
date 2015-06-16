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
import java.util.Scanner;


public class AppCavesKeeper {
	public static void main(String argc[]) {
		
		try {
			IGlobalFactory componentFactory = ComponentContextFactory.createGlobalFactory();
			componentFactory.registerPrototype(Monster.class);
			componentFactory.registerPrototype(Player.class);
			componentFactory.registerPrototype(ItemManagement.class);
			componentFactory.registerPrototype(MapVisual.class);
			componentFactory.registerPrototype(saveManagement.class);
			
			IMonster compMonster = componentFactory.createInstance("<http://cave.com/monster.Monster>");
			IPlayerMax compPlayer = componentFactory.createInstance("<http://cave.com/player.Player>");
			IItemManagement compItemManagement = componentFactory.createInstance("<http://cave.com/items.itemManagement.ItemManagement>");
			/*IMapVisual compMapVisual = componentFactory.createInstance("<http://cave.com/visual.MapVisual>");*/
			IsaveManagement compSaveManagement = componentFactory.createInstance("<http://cave.com/saveGame.saveManagement>");
				
			System.out.println("Digite o que quer: 1 - Novo jogo /// 2 - Load game");
			Scanner entrada = new Scanner(System.in);
			int inteiro = entrada.nextInt();
			
			if(inteiro == 1)
				GameController.getSharedInstance().conectar(compMonster, compPlayer, compItemManagement, compSaveManagement, 1/*, compMapVisual*/);
			else
				GameController.getSharedInstance().loadFromDeserialization(compSaveManagement);
			System.out.println("passei aqui");
			entrada.close();
			
		}
		catch(Exception e) { 
			e.printStackTrace();
			System.out.println("Exception");
		}
	}
}
