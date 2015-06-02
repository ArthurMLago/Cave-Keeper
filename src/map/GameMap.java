package map;

import map.exceptions.OutOfMapBoundsException;
import gameController.Entidade;
import map.Position;

import java.util.Hashtable;

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
public class GameMap {
	private TileMap[][] Matriz;
	
	private Hashtable<Entidade, Position> AssignedSpawnPoints;
	
	/**
	 * Construtor padrão, cria um mapa a partir de uma matriz de TileMaps dada.
	 * @param Matriz matriz de TileMaps
	 */
	public GameMap(TileMap[][] Matriz){
		this.Matriz = Matriz;
		
		AssignedSpawnPoints = new Hashtable<Entidade, Position>(); 
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
		
		if (AssignedSpawnPoints.containsKey(entity)){
			return AssignedSpawnPoints.get(entity);
		}else{
			AssignedSpawnPoints.put(entity, new Position(5,5));
		}
		return new Position(5,5);
	}
}
