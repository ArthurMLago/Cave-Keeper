package monster.Fonte;
import map.*;


public class SuperGhost extends AbstractMonster {

	public SuperGhost() {
		hp = 2;
		spaces = 1;
	}
	
	/*  Nada */
	
	@Override
	public int getImage() {
		return 0/*Por enquanto. */;
	}
	
	@Override
	public void walk() {
		int playerX = Player.getPlayerX();
		int playerY = Player.getPlayerY();
		int monsterX = getX();
		int monsterY = getY();
		int steps = spaces;
		while (steps >= 0) {
			if (playerX > monsterX && Map.getTileat(monsterX+1, monsterY).getType.Walkable) {
				this.setPosition(monsterX+1, monsterY);
				steps--;
			}
			else if (playerX < monsterX && Map.getTileat(monsterX-1, monsterY).getType.Walkable) {
				this.setPosition(monsterX-1, monsterY);
				steps--;
			}
			if (playerX > monsterY && Map.getTileat(monsterX+1, monsterY).getType.Walkable) {
				this.setPosition(monsterX, monsterY+1);
				steps--;
			}
			else if (playerX < monsterY && Map.getTileat(monsterX+1, monsterY).getType.Walkable) {
				this.setPosition(monsterX, monsterY-1);
				steps--;
			}
		}
	}

}
