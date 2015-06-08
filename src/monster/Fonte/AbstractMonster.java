package monster.Fonte;

import gameController.Entidade;

import java.io.Serializable;
import java.util.Random;

import player.Player;
import gameController.*;
import map.GameMap;
import monster.Interfaces.*;

public abstract class AbstractMonster implements IAbstractMonster, Serializable {
	
	protected int hp, posX, posY, spaces;
	protected boolean live = true;
	protected boolean following;
	
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
	public double getDistance (int playerX, int playerY) {
		double distance;
		distance = Math.sqrt(Math.pow((this.getX() - playerX), 2) + Math.pow((this.getY() - playerY), 2));
		return distance;
	}
	
	/** Emite o som do monstro de acordo com a distancia dele ao player. */
	public void emitSound (int playerX, int playerY) {
		int distance = (int) getDistance (playerX, playerY);
		if (distance < 10) {
			/* ReproduzirSom(10-distance); */
		}
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
	
	/** Realiza um movimento seguindo o player */
	public void followWalk() {
		Player player = getPlayer();
		Map map = getMap();
		Random random = new Random();
		
		int playerX = player.getPlayerX();
		int playerY = player.getPlayerY();
		
		int monsterX = getX();
		int monsterY = getY();
		
		int steps = spaces;
		
		while (steps >= 0) {
			int direcao = random.nextInt(1);
			
			if (playerX > monsterX && playerY > monsterY) {
				if (direcao == 0) {
					if (map.getTileAt(monsterX+1, monsterY).getType() == Walkable) {
						this.setPosition(monsterX+1, monsterY);
						steps--;
					}
				}
				else if (direcao == 1) {
					if (map.getTileAt(monsterX, monsterY+1).getType() == Walkable) {
						this.setPosition(monsterX, monsterY+1);
						steps--;
					}
				}
			}
			if (playerX < monsterX && playerY > monsterY) {
				if (direcao == 0) {
					if (map.getTileAt(monsterX, monsterY+1).getType() == Walkable) {
						this.setPosition(monsterX, monsterY+1);
						steps--;
					}
				}
				else if (direcao == 1) {
					if (map.getTileAt(monsterX-1, monsterY).getType() == Walkable) {
						this.setPosition(monsterX-1, monsterY);
						steps--;
					}
				}
			}
			if (playerX < monsterX && playerY < monsterY) {
				if (direcao == 0) {
					if (map.getTileAt(monsterX, monsterY-1).getType() == Walkable) {
						this.setPosition(monsterX, monsterY-1);
						steps--;
					}
				}
				else if (direcao == 1) {
					if (map.getTileAt(monsterX-1, monsterY).getType() == Walkable) {
						this.setPosition(monsterX-1, monsterY);
						steps--;
					}
				}
			}
			if (playerX > monsterX && playerY < monsterY) {
				if (direcao == 0) {
					if (map.getTileAt(monsterX, monsterY-1).getType() == Walkable) {
						this.setPosition(monsterX, monsterY-1);
						steps--;
					}
				}
				else if (direcao == 1) {
					if (map.getTileAt(monsterX+1, monsterY).getType() == Walkable) {
						this.setPosition(monsterX+1, monsterY);
						steps--;
					}
				}
			}
			if (playerX == monsterX) {
				if (playerY > monsterY) {
					if (map.getTileAt(monsterX, monsterY+1).getType() == Walkable) {
						this.setPosition(monsterX, monsterY+1);
						steps--;
					}
				}
				else if (playerY < monsterY) {
					if (map.getTileAt(monsterX, monsterY-1).getType() == Walkable) {
						this.setPosition(monsterX, monsterY-1);
						steps--;
					}
				}
			}
			if (playerY == monsterY) {
				if (playerX > monsterX) {
					if (map.getTileAt(monsterX+1, monsterY).getType() == Walkable) {
						this.setPosition(monsterX+1, monsterY);
						steps--;
					}
				}
				else if (playerX < monsterX) {
					if (map.getTileAt(monsterX-1, monsterY).getType() == Walkable) {
						this.setPosition(monsterX-1, monsterY);
						steps--;
					}
				}
			}
		}
	}
	
	/** Realiza um movimento aleatorio pelo mapa */
	public void randomWalk() {
		Random random = new Random();
		int direcao = random.nextInt(4);
		
		int monsterX = getX();
		int monsterY = getY();
		
		
		Map map = getMap();
		if (direcao == 0) {
			if(map.getTileAt(monsterX, monsterY+1).getType() == Walkable)
				this.setPosition(monsterX, monsterY+1);
		}
		else if (direcao == 1) {
			if(map.getTileAt(monsterX, monsterY-1).getType() == Walkable)
				this.setPosition(monsterX, monsterY-1);
		}
		else if (direcao == 2) {
			if(map.getTileAt(monsterX+1, monsterY).getType() == Walkable)
				this.setPosition(monsterX+1, monsterY);
		}
		else if (direcao == 3) {
			if(map.getTileAt(monsterX-1, monsterY).getType() == Walkable)
				this.setPosition(monsterX-1, monsterY);
		}
	}
	/** Define se o monstro deve começar a seguir o jogador */
	public void setFollowing(boolean following) {
		this.following = following;
	}
	
	/** Diz se o monstro esta seguindo ou nao o player */
	public boolean getFollowing() {
		return this.following;
	}
	public abstract void walk();
	public abstract String getImage();
	
}
