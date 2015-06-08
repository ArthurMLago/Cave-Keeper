package items.itemManagement;

import java.util.ArrayList;
import saveGame.saveGame;

/**
 * @author Vicente
 * @author Felipe Moret
 */
public class TestComponent {

	public static void main(String[] args) {
		
//		/*criacao de um novo inventario*/
//		ItemManagement myBag = new ItemManagement();
//		
//		/*criacao do ArrayList de quantidades iniciais*/
//		ArrayList <Integer> quantities = new ArrayList<Integer>();
//		for(int i = 0;i < 6;i++) {
//			quantities.add(5);
//		}
//		
//		myBag.startInventoryNumbers(quantities);
//		myBag.useItem(0);
//				
//		myBag.printEverything();
		
		/*teste de serializacao
		saveGame<ItemManagement> salvador = new saveGame<ItemManagement>();
		
		salvador.saveState(myBag);
		 */
		/*teste de deserializacao*/
		saveGame<ItemManagement> salvador = new saveGame<ItemManagement>();
		ItemManagement myBag = null;
		
		myBag = salvador.obtainState();
		
		myBag.printEverything();
	}

}
