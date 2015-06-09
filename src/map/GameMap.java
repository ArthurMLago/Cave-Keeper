package map;

import map.exceptions.OutOfMapBoundsException;
import map.interfaces.IGameMap;
import gameController.Entidade;
import map.Position;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.ArrayList;

/**
 * 
 * Classe que armazena e gerencia o mapa do jogo
 * <p>
 * Um objeto de Map é contém uma matriz de TileMaps para armazenar o mapa, para obter informações de um tile em uma posicao
 * especifica, deve-se usar o método {@link #getTileAt(int, int) getTileAt(x,y)} que retorna um objeto da classe TileMap
 * na posição especificada.
 * @author ArthurMLago
 *
 */
public class GameMap implements IGameMap,Serializable {
	private static final long serialVersionUID = 1L;

	private TileMap[][] Matriz;
	
	private int MapWidth, MapHeight;
	
	private ArrayList<Position> SpawnPointList;
	private Hashtable<Entidade, Position> AssignedSpawnPoints;
	
	
	/**
	 * Construtor padrão, cria um mapa a partir de uma matriz de TileMaps dada.
	 * @param Matriz matriz de TileMaps
	 */
	public GameMap(TileMap[][] Matriz, ArrayList<Position> SpawnPointList){
		this.Matriz = Matriz;
		this.MapHeight = Matriz.length;
		this.MapWidth = Matriz[0].length;
		
		this.SpawnPointList = SpawnPointList;
		AssignedSpawnPoints = new Hashtable<Entidade, Position>();
	}
	
	/**
	 * Método para retornar o limite horizontal do mapa( a largura).
	 */
	public int getLimitX(){
		return MapWidth;
	}
	
	/**
	 * Método para retornar o limite vertical do mapa( a altura).
	 */
	public int getLimitY(){
		return MapHeight;
	}
	
	
	/**
	 * Método para obter o Tile na posição especificada
	 * @param x Posição na horizontal do Tile desejado
	 * @param y Posição na vertical do Tile desejado
	 * @return O objeto da classe TileMap na posição especificada.
	 * @throws OutOfMapBoundsException
	 */
	public TileMap getTileAt(int x, int y) throws OutOfMapBoundsException{
		if (y < Matriz.length){
			throw(new OutOfMapBoundsException());
		}
		if (x < Matriz[0].length){
			throw(new OutOfMapBoundsException());
		}
		return Matriz[x][y];
	}
	
	
	/**
	 * Método para recuperar os SpawnPoints das entidades no mapa.
	 * <p>
	 * O Método recebe como parâmetro um objeto implementando a interface Entidade, e atribui a ele um dos SpawnPoints do
	 * mapa.
	 * <p>
	 * Ao se chamar o método mandando como atributo uma entidade que ja foi atribuida um SpawnPoint, o retorno será a 
	 * mesma posição retornada na primeira chamada com tal Entidade, pois a classe Mapa mantém um registro de todos as
	 * entidades que requeriram um SpawnPoint, e o SpawnPoint atribuido.
	 * @param entity Entidade na qual deseja-se obter o SpawPoint
	 * @return Objeto da classe {@link map.Position Position} contendo a posição do SpawnPoint
	 */
	public Position getSpawnPoint(Entidade entity){
		boolean AvailableSpawnPoint = true;
		if (!AssignedSpawnPoints.containsKey(entity)){
			AvailableSpawnPoint = false;
			for (Position i : SpawnPointList){
				if (!AssignedSpawnPoints.containsValue(i)){
					AssignedSpawnPoints.put(entity, i);
					AvailableSpawnPoint = true;
					break;
				}
			}
		}
		
		if (AvailableSpawnPoint == true){
			return AssignedSpawnPoints.get(entity);
		}else{
			return null;
		}
		
	}
}
