package map;


import map.GameMap;
import map.Position;
import map.enumerations.TileType;
import map.interfaces.IMapGenerator;

import java.util.Random;
import java.util.ArrayList;




/**
 * Classe responsavel por gerar mapas jogaveis aleatoriamente, recebendo caracteristicas do mapa através dos diversos
 * métodos setters, e retorna uma instância de {@link map.GameMap GameMap} através do método {@link #generateMap() generateMap()}.
 * <p>
 * É um singleton, portanto, existe apenas uma instância dessa classe que pode ser acessada através do método 
 * {@link #sharedInstance() sharedInstance()}.
 * @author Arthur Moraes do Lago
 *
 */
public class MapGenerator implements IMapGenerator{
	//Instância estatica da classe( singleton):
	private static MapGenerator sharedInstancee;
	
	//Gerador de numeros aleatorios:
	Random randomGenerator;
	
	//Propriedades do mapa:
	private int MapWidth, MapHeight;
	private int MapWalkablePaths;
	private int NSpawnPoints;
	private double lightPercentage;
	
	//Variaveis para criação de mapa
	private int[][] Matriz;
	private ArrayList<Position> SpawnPointList;
	
	
	/**
	 * Método para obter a instância da classe singleton, criando-a se ainda nao existir.
	 * @return A instância única da classe MapGenerator
	 */
	public static MapGenerator sharedInstance(){
		if (sharedInstancee == null){
			sharedInstancee = new MapGenerator();
		}
		return sharedInstancee;
	}
	
	
	/**
	 * Construtor padrão, com valores de propriedades padrões.
	 */
	private MapGenerator(){
		MapWidth = 30;
		MapHeight = 30;
		MapWalkablePaths = 450;
		NSpawnPoints = 4;
		lightPercentage = 0.1;
		
		randomGenerator = new Random();
	}
	
	
	/**
	 * Define a largura do mapa a ser gerado.
	 * @param value Largura do mapa em Tiles
	 */
	public void setMapWidth(int value){	MapWidth = value;}
	
	
	/**
	 * Define a altura do mapa a ser gerado.
	 * @param value Altura do mapa em Tiles
	 */
	public void setMapHeight(int value){	MapHeight = value;}
	
	
	/**
	 * Define a quantidade de Tiles passaveis que estarão presentes no mapa.
	 * <p>
	 * Um valor de 0, gerara um mapa sem Tiles passaveis, enquanto que um valor igual a área do mapa gerará um mapa sem
	 * paredes.
	 * @param value Numero de Tiles passaveis
	 */
	public void setWalkablePath(int value){	MapWalkablePaths = value;}
	
	
	/**
	 * Função para definir o numero de Spawn Points.
	 * @param Numero de Spawn Points do mapa
	 */
	public void setNSpawnPoints(int value){ NSpawnPoints = value;}
	
	
	/**
	 * Define a porcentagem do mapa que estará iluminada por luzes permanentes:
	 * @param value  Porcentagem do mapa iluminado (0.0 -> 1.0)
	 */
	public void setLightPercentage(double value){	lightPercentage = value;}
	
	
	/**
	 * Método que gera um mapa aleatório jogavel com as caracteristicas especificadas.
	 * @return Um objeto da classe Mapa, contendo um mapa jogavel com as caracteristicas especificadas.
	 */
	public GameMap generateMap(){
		
		Matriz = new int[MapHeight][MapWidth];
		SpawnPointList = new ArrayList<Position>();
		
		TileMap[][] MatrizTiles = new TileMap[MapHeight][MapWidth];
		
		//Posicao inicial do random blobber:
		int PosX = MapWidth/2;
		int PosY = MapHeight/2;
		
		
		//Geracao do mapa bruto:
		//Contagem de vezes que o o blobber alterou a matriz:
		int ChangeCount = 0;
		//Loop de movimentacao do blobber:
		while (ChangeCount < MapWalkablePaths){
			//Descobrir em qual direcao o blobber andara:
			int RandomDirection = randomGenerator.nextInt();
			RandomDirection %= 5;
			switch (RandomDirection){
				case 1:
					PosY++;
					break;
				case 2:
					PosX++;
					break;
				case 3:
					PosY--;
					break;
				case 4:
					PosX--;
					break;
			}
			
			//Garantir que esta dentro do mapa:
			PosX = clampToMapWidth(PosX);
			PosY = clampToMapHeight(PosY);
			
			//Se nao for passavel, agora eh:
			if (Matriz[PosY][PosX] == 0){
				Matriz[PosY][PosX] = 1;
				ChangeCount++;
			}
		}
		//Imprimir o mapa:
//		DEBUGprintMap();
		
		//Hora de aplicar filtros:
		//Nao permitir pontos inpassaveis no meio de pontos passaveis:
		filterIsolatedTiles();
		
		//tirar passsagems muito estreitas:
		filterNarrowPassage();
		
		//Criar paredes:
		setMapLimits();
		
		//criar spawn points:
		CreateSpawnPoints();
		
		for (int i = 0; i < MapHeight; i++){
			for (int j = 0; j < MapWidth; j++){
				String tileImage;
				TileType tileEnum;
				if (!(Matriz[i][j] == 0)){
					tileImage = "Grass";
					tileEnum = TileType.Walkable;
				}else{
					tileImage = "Rock";
					tileEnum = TileType.Wall;
				}
				
				ArrayList<Event> eventArray = new ArrayList<Event>();
				
				MatrizTiles[i][j] = new TileMap(tileEnum,tileImage,eventArray);
			}
		}
		
		//Imprimir:
		DEBUGprintMap();
		
		return new GameMap(MatrizTiles,SpawnPointList);
		
		
		

	}
	
	
	/**
	 * Métodos para facilitar a verificação se dada coordenada esta dentro do mapa, para valores de largura.
	 * @param x : Posicao na horizontal;
	 * @return Posicao na horizontal garantidamente dentro do mapa.
	 */
	private int clampToMapWidth(int x){
		if (x > MapWidth - 1){
			x = MapWidth - 1;
		}else if(x < 0){
			x = 0;
		}
		return x;
	}
	
	
	/**
	 * Métodos para facilitar a verificação se dada coordenada esta dentro do mapa, para valores de altura.
	 * @param x : Posicao na vertical;
	 * @return Posicao na vertical garantidamente dentro do mapa.
	 */
	private int clampToMapHeight(int y){
		if (y > MapHeight - 1){
			y = MapHeight - 1;
		}else if(y < 0){
			y = 0;
		}
		return y;
	}
	
	
	/**
	 * Método para aplicar filtro de remoção de Tiles isolados dso mapa.
	 */
	private void filterIsolatedTiles(){
		for (int i = 0; i < MapHeight; i++){
			for (int j = 0; j < MapWidth; j++){
				if (Matriz[i][j] == 0){
					if (i + 1 > MapHeight - 1){
						continue;
					}
					if (i - 1 < 0){
						continue;
					}
					if (j + 1 > MapWidth - 1){
						continue;
					}
					if (j - 1 < 0){
						continue;
					}
					
					if (Matriz[i + 1][j] == 0){
						continue;
					}
					if (Matriz[i-1][j] == 0){
						continue;
					}
					if (Matriz[i][j+1] == 0){
						continue;
					}
					if (Matriz[i][j-1] == 0){
						continue;
					}
					Matriz[i][j] = 1;
				}
				
			}
		}
	}
	
	
	/**
	 * Método para aplicar filtro de remoção de passagems estreitas.
	 */
	private void filterNarrowPassage(){
		for (int i = 0; i < MapHeight; i++){
			for (int j = 0; j < MapWidth; j++){
				int iActualMin = i - 1;
				int iActualMax = i + 1;
				int jActualMin = j - 1;
				int jActualMax = j + 1;
				if (i + 1 > MapHeight - 1){
					iActualMax = MapHeight - 1;
				}
				if (i - 1 < 0){
					iActualMin = 0;
				}
				if (j + 1 > MapWidth - 1){
					jActualMax = MapWidth - 1;
				}
				if (j - 1 < 0){
					jActualMin = 0;
				}
				if (Matriz[i][j] == 1){
					if ((Matriz[iActualMin][j] == 0) && (Matriz[iActualMax][j] == 0)){
						Matriz[i][j] = 1;
					}
					if ((Matriz[i][jActualMin] == 0) && (Matriz[i][jActualMax] == 0)){
						Matriz[i][j] = 1;
					}
				}
			}
		}
	}
	
	/**
	 * Função que faz com que as bordas do mapa sejam sempre paredes:
	 */
	private void setMapLimits(){
		//Criar as paredes do mapa:
		for (int i = 0; i < MapHeight; i++){
			Matriz[i][0] = 0;
			Matriz[i][MapWidth - 1] = 0;
		}
		for (int i = 0; i < MapWidth; i++){
			Matriz[0][i] = 0;
			Matriz[MapHeight - 1][i] = 0;
		}
	}
	
	
	/**
	 * Função que cria SpawnPoints espalhados pelo mapa, de form a deixa-los sempre suficientemente afastados,
	 * e que estejam em posições validas.
	 */
	private void CreateSpawnPoints(){
		Position[] SpawnPointPosition = new Position[NSpawnPoints];
		
		//Encontrar uma distancia minima entre os spawns:
		double MinDistance = Math.sqrt(MapWalkablePaths) / NSpawnPoints;
		
		//Ccriar SpawnPoint aleatorios ate que eles obedecam as condicoes necessarias
		boolean satisfactory = false;
		while(!satisfactory){
			satisfactory = true;
			
			for (int i = 0; i < NSpawnPoints; i++){
				do{
					SpawnPointPosition[i] = new Position(randomGenerator.nextInt(MapHeight),randomGenerator.nextInt(MapWidth));
				}while(Matriz[SpawnPointPosition[i].getX()][SpawnPointPosition[i].getY()] == 0);
			}
			
			for (int i = 0; (i < NSpawnPoints) && satisfactory; i++){
				for (int j = i + 1; (j < NSpawnPoints) && satisfactory; j++){
					if (SpawnPointPosition[i].distanceTo(SpawnPointPosition[j]) < MinDistance){
						satisfactory = false;
					}
				}
			}
		}
		
		for (int i = 0; i < NSpawnPoints; i++){
			SpawnPointList.add(SpawnPointPosition[i]);
			Matriz[SpawnPointPosition[i].getX()][SpawnPointPosition[i].getY()] = 8;
		}
	}
	
	/**
	 * Metodo apenas para testes, imprime o mapa no console:
	 */
	private void DEBUGprintMap(){
		System.out.print("{");
		for (int i = 0; i < MapHeight; i++){
			System.out.print("{");
			for (int j = 0; j < MapWidth - 1; j++){
				System.out.print(Matriz[i][j]+",");
			}
			System.out.print(Matriz[i][MapHeight - 1]);
			System.out.print("},");
		}
		System.out.print("}");
	}
	
}
