package monster.Interfaces;

import java.util.ArrayList;

import monster.IMap;
import monster.IPlayer;
import monster.Fonte.AbstractMonster;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/** Interface com as operacoes basicas de gerenciamento de monstros do componente. */

@ComponentInterface("<http://santanvarzea.com/monster.Interfaces.IMonster>")
public interface IMonster extends ISupports {

	public void generateMonsters();
	public boolean isMonstersAlive();
	public AbstractMonster getMonster();
	public void connect (IPlayer player, IMapVisual map);
	public void setMonsterPosition(int monsterID, int x, int y);
	public void runMonstersActions(int monsterID);
	public void setFollow();
	public void getHit(int monsterID);
	public void deleteMonsters();
	public int getX(int monsterID);
	public int getY(int monsterID);
	
}
