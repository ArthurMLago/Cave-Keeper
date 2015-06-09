package items.inventory;

import items.excecoes.OutofItemsException;
import map.interfaces.IGameMap;
import player.IPlayerMax;
import map.enumerations.*;
import map.exceptions.OutOfMapBoundsException;

/**
 * @author Vicente
 * @author Felipe Moret
 */
public class Stick extends GeneralItems {
	
	private static final long serialVersionUID = 1L;
	private IGameMap map;
	private IPlayerMax player;
	public Stick(String name, int number) {
		super(name,number);
	}
	
	
	public void effect() throws OutofItemsException {
		super.effect();
		int playerX = player.getX();
		int playerY = player.getY();
		for(int i = -1;i < 2;i++) {
			for(int j = -1;j < 2;j++) {
				try {
					if(map.getTileAt(playerX + i, playerY + j).checkForEvents(EventType.TRAP) != null) {
						map.getTileAt(playerX + i, playerY + j).discardEventOfType(EventType.TRAP);
					}
				} catch (OutOfMapBoundsException e) {
	
				}	
			}
		}
	}
}