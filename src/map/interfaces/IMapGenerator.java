package map.interfaces;

import anima.annotation.ComponentInterface;
import map.GameMap;
import map.MapGenerator;

@ComponentInterface("<http://cave.com/map.interfaces.IMapGenerator>")
public interface IMapGenerator {
	
	/**
	 * Define a largura do mapa a ser gerado.
	 */
	public void setMapWidth(int value);
	
	
	/**
	 * Define a altura do mapa a ser gerado.
	 */
	public void setMapHeight(int value);
	
	
	/**
	 * Define a quantidade de Tiles passaveis que estarão presentes no mapa.
	 */
	public void setWalkablePath(int value);
	
	
	/**
	 * Função para definir o numero de Spawn Points.
	 */
	public void setNSpawnPoints(int value);
	
	
	/**
	 * Define a porcentagem do mapa que estará iluminada por luzes permanentes:
	 */
	public void setLightPercentage(double value);
	
	
	/**
	 * Método que gera um mapa aleatório jogavel com as caracteristicas especificadas.
	 */
	public GameMap generateMap();
	
	
	
}
