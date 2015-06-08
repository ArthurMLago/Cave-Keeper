package player;

import map.enumerations.TileType;
import map.exceptions.OutOfMapBoundsException;
import gameController.*;
import items.itemManagement.ItemManagement;
import player.IPlayerMax;

public class Player implements IPlayerPosition, IPlayerAction, IPlayerMax, Entidade {

	private int posX, posY;

	private char facing;

	private boolean lighter;

	private ItemManagement bag;

	public Player() {
		facing = Facing.SOUTH;
		lighter = false;
		bag = new ItemManagement();
		this.posX = 0;
		this.posY = 0;
	}
	
	public String getTipo() {
		return "player";
	}
	
	public int getImage() {
		return 1;
	}
	
	public void setSpawnPointPlayer(int x, int y) {
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

		try {
			if (direction == Facing.NORTH) {
				if ((TileType.Walkable.equals(GameController
						.getSharedInstance().getMap().getTileAt(posX, posY + 1)
						.getType())))
					posY++;
				else
					return false;
			}

			else if (direction == Facing.SOUTH) {
				if ((TileType.Walkable.equals(GameController
						.getSharedInstance().getMap().getTileAt(posX, posY - 1)
						.getType())))
					posY--;
				else
					return false;
			}

			else if (direction == Facing.EAST) {
				if ((TileType.Walkable.equals(GameController
						.getSharedInstance().getMap().getTileAt(posX + 1, posY)
						.getType())))
					posX++;
				else
					return false;
			}

			else if (direction == Facing.WEST) {
				if ((TileType.Walkable.equals(GameController
						.getSharedInstance().getMap().getTileAt(posX - 1, posY)
						.getType())))
					posX--;
				else
					return false;
			} else
				return false;
		} catch (OutOfMapBoundsException erro) {
		}

		return true;
	}

	public boolean shoot(char direction) {
		if (bag.displayNumber(4/* AMMO */) == 0)
			return false;

		facing = direction;

		int x = posX, y = posY;

		Entidade monsterReference = GameController.getSharedInstance()
				.getEntidades();

		int flag = 0;
		try {
			if (direction == 'E') {
				for (int i = x + 1; flag == 0; i++) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(i, y)
							.getType())))
						flag = 1;
					else if ((monsterReference.getX() == i)
							&& (monsterReference.getY() == posY))
						flag = 2;
				}
			}

			if (direction == 'W') {
				for (int i = x - 1; flag == 0; i--) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(i, y)
							.getType())))
						flag = 1;
					else if ((monsterReference.getX() == i)
							&& (monsterReference.getY() == posY))
						flag = 2;
				}
			}

			if (direction == 'N') {
				for (int i = y + 1; flag == 0; i++) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(x, i)
							.getType())))
						flag = 1;
					else if ((monsterReference.getX() == posX)
							&& (monsterReference.getY() == i))
						flag = 2;
				}
			}

			if (direction == 'S') {
				for (int i = y - 1; flag == 0; i--) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(x, i)
							.getType())))
						flag = 1;
					else if ((monsterReference.getX() == posX)
							&& (monsterReference.getY() == i))
						flag = 2;
				}
			}

		}

		catch (OutOfMapBoundsException erro) {
		}

		if (flag == 1)
			return false;
		if (flag == 2)
			return true;

		return false;

	}

	public void useFlare() {
		bag.useItem(0/* FLARE */);
	}

	public void useStick() {
		bag.useItem(5);
	}
}
