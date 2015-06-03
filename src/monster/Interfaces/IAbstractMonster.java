package monster.Interfaces;

import gameController.Entidade;

/** Interface que define as operacoes basicas de um monstro. */
public interface IAbstractMonster extends Entidade {
	
	public String getTipo();
	public void setPosition(int x, int y);
	public int getX();
	public int getY();
	public double getDistance(double playerX, double playerY);
	public void emitSound();
	public int getHp();
	public void takeShot();
	public boolean isAlive();
	public abstract void walk();
	public int getImage();
	
}
