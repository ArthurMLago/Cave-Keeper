package monster.Fonte;

import gameController.Entidade;

public abstract class AbstractMonster implements Entidade {
	
	int hp, posX, posY, spaces;
	boolean live = true; 
	
	public String getTipo() {
		return "monster";
	}
	
	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
	}
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
	
	public double getDistance (double playerX, double playerY) {
		double distance;
		distance = Math.sqrt(Math.pow((this.getX() - playerX), 2) + Math.pow((this.getY() - playerY), 2));
		return distance;
	}
	
	public void emitSound () {
		double playerX = Player.getPlayerX;
		double playerY = Player.getPlayerY;
		int distance = (int) getDistance (playerX, playerY);
		
		if(distance <= 10)
			/* Emitir som pequeno. */;
		else if(distance > 10 && distance <= 20)
			/* Emitir som medio. */;
		else
			/* Emitir som alto. */;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void takeShot() {
		hp--;
		if(hp == 0)
			live = false;
	}
	
	public boolean isAlive() {
		return live;
	}
	
	public abstract void walk();
	
}
