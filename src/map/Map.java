package map;

import map.exceptions.OutOfMapBoundsException;
import gameController.Entidade;

public class Map {
	private TileMap[][] Matriz;
	
	public Map(){
		
	}
	
	public Map(TileMap[][] Matriz){
		this.Matriz = Matriz;
	}
	
	public TileMap getTileat(int x, int y) throws OutOfMapBoundsException{
		if (y < Matriz.length){
			throw(new OutOfMapBoundsException());
		}
		if (x < Matriz[0].length){
			throw(new OutOfMapBoundsException());
		}
		return Matriz[x][y];
	}
	
	public TileMap getSpawnPoint(Entidade entity){
		return Matriz[5][5];
	}
}
