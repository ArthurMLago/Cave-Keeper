package map.mapGenerator;

import map.Map;

public class MapGenerator {
	private MapGenerator sharedInstance;
	
	private int MapWidth, MapHeight;
	private int MapWalkablePaths;
	private double lightPercentage;
	
	public MapGenerator sharedInstance(){
		if (sharedInstance == null){
			sharedInstance = new MapGenerator();
		}
		return sharedInstance;
	}
	
	private MapGenerator(){
		MapWidth = 20;
		MapHeight = 20;
		MapWalkablePaths = 150;
		lightPercentage = 0.1;
	}
	
	public void setMapWidth(int value){	MapWidth = value;}
	public void setMapHeight(int value){	MapHeight = value;}
	public void setWalkablePath(int value){	MapWalkablePaths = value;}
	public void setLightPercentage(double value){	lightPercentage = value;}
	
	public Map generateMap(){
		int[][] Matriz = new int[MapWidth][MapHeight];
		
		//Inicializar a matriz:
		for (int i = 0; i < MapWidth; i++){
			for (int j = 0; j < MapHeight; j++){
				Matriz[i][j] = 0;
			}
		}
		
		//Posicao inicial do random blobber:
		int PosX = MapWidth/2;
		int PosY = MapHeight/2;
		
		//Geracao do mapa bruto:
		//Contagem de passos do random blobber:
		int WalkCount = 0;
		//Contagem de vezes que o o blobber alterou a matriz:
		int ChangeCount = 0;
		while (ChangeCount > MapWalkablePaths){
			
		}
		
		
		Map Result = new Map();
		
		return Result;
	}
	
}
