package player;

import gameController.GameController;
import items.itemManagement.ItemManagement;
import player.IPlayerMax;

public class Player implements IPlayerPosition, IPlayerAction, IPlayerMax {
	
	private int posX,
		        posY;
	
	private char facing;
	
	private boolean lighter;
	
	private ItemManagement bag;
	
	public Player(int x, int y) {
		facing = Facing.SOUTH;
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
	
	public int getFacing() {
		return facing;
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
		
		if (direction == Facing.NORTH) {
			if ((GameController.sharedInstance.map.getTipe(posX, posY + 1)) == "walkable")
				posY++;
			else
				return false;			
		}
		
		else if (direction == Facing.SOUTH) {
			if (GameController.sharedInstance.map.getTipe(posX, posY - 1) == "walkable")
				posY--;
			else
				return false;
		}
		
		else if (direction == Facing.EAST) {
			if (GameController.sharedInstance.map.getTipe(posX + 1, posY) == "walkable")
				posX++;
			else
				return false;
		}
		
		else if (direction == Facing.WEST) {
			if (GameController.sharedInstance.map.getTipe(posX - 1, posY) == "walkable")
				posX--;
			else
				return false;
		}
		else
			return false;
		
		bag.update(posX, posY);
		GameController.SharedInstance.map.update(posX, posY);
		
		return true;
	}
	
	public boolean shoot(char direction) {
		if (bag.displayNumber(4/*AMMO*/) == 0)
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

	public void useFlare() {
		bag.useItem(0/*FLARE*/);
	}
	
	public void useStick() {
		bag.useItem(5);
	}
}
