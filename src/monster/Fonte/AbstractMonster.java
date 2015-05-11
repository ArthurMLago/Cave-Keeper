package Fonte;

public abstract class AbstractMonster {
	
	int hp, posX, posY;
	boolean live = true; 
	
	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public double getDistance(int playerX, int playerY) {
		return /*A fazer*/ 0;
	}
	
	public void emitSound() {
		/*Algo*/
	}
	
	public int getHp() {
		return hp;
	}
	
	public void takeShot() {
		hp--;
		if(hp == 0)
			live = false;
	}
	
	public boolean isLive() {
		return live;
	}
	
	public abstract void walk();
	
}
