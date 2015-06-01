package player;

import gameController.GameController;
import items.itemManagement.ItemManagement;

public class Player {
	
	private int posX,
		        posY;
	
	private char facing;
	
	private boolean lighter;
	
	private ItemManagement bag;
	
	public Player(int x, int y) {
		facing = 'S';
		lighter = false;
		bag = new ItemManagement();	
		this.posX = x;
		this.posY = y;
	}
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
	
	public boolean getLighter() {
		return lighter;
	}
	
	public void setLighter() {
		if (lighter)
			lighter = false;
		else
			lighter = true;
	}
	
	public void setLighter(boolean state) {
		lighter = state;
	}
	
	public boolean move(char direction) {
		facing = direction; 
		
		if (direction == 'N') {
			if ((GameController.sharedInstance.map.getTipe(posX, posY + 1)) == "walkable")
				posY++;
			else
				return false;			
		}
		
		else if (direction == 'S') {
			if (GameController.sharedInstance.map.getTipe(posX, posY - 1) == "walkable")
				posY--;
			else
				return false;
		}
		
		else if (direction == 'E') {
			if (GameController.sharedInstance.map.getTipe(posX + 1, posY) == "walkable")
				posX++;
			else
				return false;
		}
		
		else if (direction == 'W') {
			if (GameController.sharedInstance.map.getTipe(posX - 1, posY) == "walkable")
				posX--;
			else
				return false;
		}
		else
			return false;
		
		return true;
	}
	
	public boolean shoot(char direction) {
		if (bag.getAmmo == 0)
			return false;
		
		facing = direction;
		
		int x = posX, y = posY;
		
		int flag = 0;
		
		if (direction == 'E') {
			for(int i = x + 1; flag == 0; i++) {
				if (GameController.sharedInstance.gameMap.getTipe(i, y) == "wall")
					flag = 1;
				else if((gameController.sharedInstance.gameMonster.getX() == i) && (gameController.sharedInstance.gameMonster.getX() == posY))
					flag = 2;
			}
		}
		
		if (direction == 'W') {
			for(int i = x - 1; flag == 0; i--) {
				if (GameController.sharedInstance.gameMap.getTipe(i, y) == "wall")
					flag = 1;
				else if((gameController.sharedInstance.gameMonster.getX() == i) && (gameController.sharedInstance.gameMonster.getX() == posY))
					flag = 2;
			}
		}
		
		if (direction == 'N') {
			for(int i = y + 1; flag == 0; i++) {
				if (GameController.sharedInstance.gameMap.getTipe(x, i) == "wall")
					flag = 1;
				else if((gameController.sharedInstance.gameMonster.getX() == posX) && (gameController.sharedInstance.gameMonster.getX() == i))
					flag = 2;
			}
		}
		
		if (direction == 'S') {
			for(int i = y - 1; flag == 0; i--) {
				if (GameController.sharedInstance.gameMap.getTipe(x, i) == "wall")
					flag = 1;
				else if((gameController.sharedInstance.gameMonster.getX() == posX) && (gameController.sharedInstance.gameMonster.getX() == i))
					flag = 2;
			}
		}
		
		if (flag == 1)
			return false;
		if (flag == 2)
			return true;
		
	}

}
