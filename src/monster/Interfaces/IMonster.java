package monster.Interfaces;

import map.interfaces.IGameMap;
import player.IPlayerMax;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/** Interface com as operacoes basicas de gerenciamento de monstros do componente. */

@ComponentInterface("<http://santanvarzea.com/monster.Interfaces.IMonster>")
public interface IMonster extends ISupports {

	public void generateMonsters(int f);
	public boolean isMonstersAlive();
	public void connect (IPlayerMax player, IGameMap map);
	public void setMonsterPosition(int monsterID);
	public void runMonstersActions(int monsterID);
	public void setFollow();
	public double getDistance (int monsterID);
	public void getHit(int monsterID);
	public void deleteMonsters();
	public int getX(int monsterID);
	public int getY(int monsterID);
	public String getImage(int monsterID);
	
}
