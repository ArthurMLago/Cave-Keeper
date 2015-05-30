package map;



//import map.Map;

import java.util.Random;


/**
 * Classe responsavel por gerar mapas jogaveis aleatoriamente, recebendo caracteristicas do mapa através dos diversos
 * métodos setters, e retorna uma instância de {@link map.Map Mapa} através do método {@link #generateMap() generateMap()}.
 * <p>
 * É um singleton, portanto, existe apenas uma instância dessa classe que pode ser acessada através do método 
 * {@link #sharedInstance() sharedInstance()}.
 * @author Arthur Moraes do Lago
 *
 */
public class MapGenerator {
	private static MapGenerator sharedInstancee;
	
	Random randomGenerator;
	
	private int MapWidth, MapHeight;
	private int MapWalkablePaths;
	private double lightPercentage;
	
	private int[][] Matriz;
	
	
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
	 * Define a porcentagem do mapa que estará iluminada por luzes permanentes:
	 * @param value  Porcentagem do mapa iluminado (0.0 -> 1.0)
	 */
	public void setLightPercentage(double value){	lightPercentage = value;}
	
	
	/**
	 * Método que gera um mapa aleatório jogavel com as caracteristicas especificadas.
	 * @return Um objeto da classe Mapa, contendo um mapa jogavel com as caracteristicas especificadas.
	 */
	public void generateMap(){
		Matriz = new int[MapWidth][MapHeight];
		
		//Posicao inicial do random blobber:
		int PosX = MapWidth/2 - 10;
		int PosY = MapHeight/2;
		
		//Geracao do mapa bruto:
		//Contagem de passos do random blobber:
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
			if (Matriz[PosX][PosY] == 0){
				Matriz[PosX][PosY] = 1;
				ChangeCount++;
			}
		}
		//Imprimir o mapa:
		DEBUGprintMap();
		
		//Hora de aplicar filtros:
		//Nao permitir pontos inpassaveis no meio de pontos passaveis:
		filterIsolatedTiles();
		
		//tirar passsagems muito estreitas:
		filterNarrowPassage();
		
		
		
		
		//Imprimir:
		DEBUGprintMap();
		
		

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
		for (int i = 0; i < MapWidth; i++){
			for (int j = 0; j < MapHeight; j++){
				if (Matriz[i][j] == 0){
					if (i + 1 > MapWidth - 1){
						continue;
					}
					if (i - 1 < 0){
						continue;
					}
					if (j + 1 > MapHeight - 1){
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
		for (int i = 0; i < MapWidth; i++){
			for (int j = 0; j < MapHeight; j++){
				int iActualMin = i - 1, iActualMax = i + 1,jActualMin = j - 1, jActualMax = j + 1;
				if (i + 1 > MapWidth - 1){
					iActualMax = MapWidth - 1;
				}
				if (i - 1 < 0){
					iActualMin = 0;
				}
				if (j + 1 > MapHeight - 1){
					jActualMax = MapHeight - 1;
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
	 * Metodo apenas para testes, imprime o mapa no console:
	 */
	private void DEBUGprintMap(){
		for (int i = 0; i < MapWidth; i++){
			for (int j = 0; j < MapHeight; j++){
				System.out.print(Matriz[i][j] + ",");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
}
