package items.itemManagement;

import java.util.ArrayList;

import saveGame.saveManagement;

public class TestSaveGame {
		
		public static void main(String argc[])  {
			
//			saveManagement salvador = new saveManagement();
			
//			ArrayList<ISupports> toBeSaved = new ArrayList<ISupports>();
			
//			saveGame <ItemManagement> outroSalvador = new saveGame <ItemManagement>();
			
			/*ItemManagement mochila = new ItemManagement();*/
			
			/*mochila.setNumber(ItemsList.Flare, 13);*/
			
//			toBeSaved.add(mochila);
			
			/*outroSalvador.saveState(mochila, "items");*/
			
//			ItemManagement mochila = null;
			
//			mochila = outroSalvador.obtainState("items");
			
//			System.out.println(mochila.displayNumber(ItemsList.Flare));
			
			/*******************************************************************************/
			
			saveManagement salvador = new saveManagement();
			
			ArrayList<Object> toBeSaved = new ArrayList<Object>();
			
//			ItemManagement mochila = new ItemManagement();
//			
//			mochila.setNumber(ItemsList.Flare, 13);
//			
//			toBeSaved.add(mochila);
//			
//			salvador.serializeEverything(toBeSaved);
			
			
			toBeSaved = salvador.deserializeEverything();
			
			System.out.println(((ItemManagement) toBeSaved.get(0)).displayNumber(ItemsList.Flare));
			
		}
}
