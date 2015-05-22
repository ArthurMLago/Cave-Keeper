package map;

public class Map {
	private TileMap[][] Matriz;
	
	public Map(TileMap[][] Matriz){
		this.Matriz = Matriz;
	}
	
	public TileMap getTileat(int x, int y){
		if (y < Matriz.length){
//			throw(); depois eu vejo isso
		}
		if (x < Matriz[0].length){
//			throw();
		}
		return Matriz[x][y];
	}
}
