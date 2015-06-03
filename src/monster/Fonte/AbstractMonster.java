package monster.Fonte;

import gameController.Entidade;
import java.io.Serializable;

import monster.Interfaces.*;

public abstract class AbstractMonster implements IAbstractMonster, Serializable {
	
	protected int hp, posX, posY, spaces;
	protected boolean live = true; 
	
	/** Getter do tipo de entidade, no caso um monstro. */
	public String getTipo() {
		return "monster";
	}
	
	/** Define a posicao do monstro.
	 * @param x Posicao X do monstro.
	 * @param y Posicao Y do monstro. */
	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
	}
	
	/** Getter da posicao X atual do monstro. */
	public int getX() {
		return posX;
	}
	
	/** Getter da posicao Y atual do monstro. */
	public int getY() {
		return posY;
	}
	
	/** Calcula distancia entre o personagem e o monstro.
	 * @param playerX Posicao X do player.
	 * @param playerY Posicao Y do player. */
	public double getDistance (double playerX, double playerY) {
		double distance;
		distance = Math.sqrt(Math.pow((this.getX() - playerX), 2) + Math.pow((this.getY() - playerY), 2));
		return distance;
	}
	
	/** Emite o som do monstro de acordo com a distancia dele ao player. */
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
	
	/** Getter da vida do monstro. */
	public int getHp() {
		return hp;
	}
	
	/** Reduz a vida do monstro e verifica se o mesmo morrera. */
	public void takeShot() {
		hp--;
		if(hp == 0)
			live = false;
	}
	
	/** Verifica se o monstro esta vivo. */
	public boolean isAlive() {
		return live;
	}
	
	public abstract void walk();
	public abstract int getImage();
	
}
