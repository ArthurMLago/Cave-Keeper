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
		
		Map Result = new Map();
		
		return Result;
	}
	
}
