package map.interfaces;

import gameController.Entidade;
import map.Position;
import map.TileMap;
import map.exceptions.OutOfMapBoundsException;

public interface IGameMap {
	
	/**
	 * Método para recuperar os SpawnPoints das entidades no mapa.
	 */ 
	public Position getSpawnPoint(Entidade entity);
	
	/**
	 * Método para obter o Tile na posição especificada
	 */
	public TileMap getTileAt(int x, int y) throws OutOfMapBoundsException;
	
	/**
	 * Método para obter o Tile na posição especificada
	 */
	public int checkEventAt(int x, int y);

}
