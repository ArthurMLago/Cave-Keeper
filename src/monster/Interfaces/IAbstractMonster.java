package monster.Interfaces;

import map.interfaces.IGameMap;
import gameController.Entidade;

/** Interface que define as operacoes basicas de um monstro. */
public interface IAbstractMonster extends Entidade {
	
	public String getTipo();
	public void setPosition(int x, int y);
	public int getX();
	public int getY();
	public double getDistance(int playerX, int playerY);
	public void emitSound(int playerX, int playerY);
	public int getHp();
	public void takeShot();
	public boolean isAlive();
	public void followWalk(int playerX, int playerY, IGameMap map);
	public void randomWalk(IGameMap map);
	public void setFollowing(boolean following);
	public boolean getFollowing();
	public String getImage();
	
}
