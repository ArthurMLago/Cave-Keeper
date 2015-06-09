package items.inventory;

import player.IPlayerMax;
import map.enumerations.TileType;
import map.interfaces.IGameMap;
import items.excecoes.OutofItemsException;

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
		if(map.getTileAt(playerX+1, playerY).getType() == TileType.Walkable
	
	/**
     * chama funcao que checa o que envolta do carinha e detona a armadilha, caso exista
     */
		
		
		
	}
}