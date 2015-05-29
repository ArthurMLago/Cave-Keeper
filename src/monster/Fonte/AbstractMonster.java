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
	
	public void emitSound(int playerX, int playerY) {
		double distance = Math.sqrt( Math.pow((this.getX() - (double) playerX), 2) + Math.pow((this.getY() - (double) playerY), 2) );
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
	
	public boolean isALive() {
		return live;
	}
	
	public abstract void walk();
	
}
