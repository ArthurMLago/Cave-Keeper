package map;

import java.util.ArrayList;

enum TileType{
	Walkable,
	Wall,
	Void,
	
}

public class TileMap {
	private TileType type;
	private int tileImage;
	private ArrayList<Event> EventList;
	
	public TileMap(TileType type, int tileImage, ArrayList<Event> EventList){
		
		
	}
	
	public TileType getType(){
		return type;
	}
	public int getImage(){
		return tileImage;
	}
}
