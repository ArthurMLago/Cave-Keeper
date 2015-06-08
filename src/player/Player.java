package player;

import java.util.ArrayList;

import map.enumerations.TileType;
import map.exceptions.OutOfMapBoundsException;
import gameController.*;
import items.itemManagement.ItemManagement;
import player.IPlayerMax;


public class Player implements IPlayerPosition, IPlayerAction, IPlayerMax {

	private int posX, posY;

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

		if (direction == 'E') {
			for (int i = x + 1; flag == 0; i++) {
				if ((TileType.Walkable
						.equals(GameController.getSharedInstance().getMap()
								.getTileAt(i, y).getType())))
					flag = 1;
				else if ((GameController.getSharedInstance().gameMonster.getX() == i)
						&& (gameController.sharedInstance.gameMonster.getY() == posY))
					flag = 2;
			}
		}

		if (direction == 'W') {
			for (int i = x - 1; flag == 0; i--) {
				if ((TileType.Walkable
						.equals(GameController.getSharedInstance().getMap()
								.getTileAt(i, y).getType())))
					flag = 1;
				else if ((GameController.getSharedInstance().gameMonster.getX() == i)
						&& (gameController.sharedInstance.gameMonster.getY() == posY))
					flag = 2;
			}
		}

		if (direction == 'N') {
			for (int i = y + 1; flag == 0; i++) {
				if ((TileType.Walkable
						.equals(GameController.getSharedInstance().getMap()
								.getTileAt(x, i).getType())))
					flag = 1;
				else if ((GameController.getSharedInstance().gameMonster.getX() == posX)
						&& (gameController.sharedInstance.gameMonster.getX() == i))
					flag = 2;
			}
		}

		if (direction == 'S') {
			for (int i = y - 1; flag == 0; i--) {
				if ((TileType.Walkable
						.equals(GameController.getSharedInstance().getMap()
								.getTileAt(x, i).getType())))
					flag = 1;
				else if ((GameController.getSharedInstance().gameMonster.getX() == posX)
						&& (gameController.sharedInstance.gameMonster.getX() == i))
					flag = 2;
			}
		}

		if (flag == 1)
			return false;
		if (flag == 2)
			return true;

	}

	public void useFlare() {
		bag.useItem(0/* FLARE */);
	}

	public void useStick() {
		bag.useItem(5);
	}
}
