package monster.Interfaces;

import java.util.ArrayList;

import monster.Fonte.AbstractMonster;
import anima.component.ISupports;

/** Interface com as operacoes basicas de gerenciamento de monstros do componente. */
public interface IMonster extends ISupports {

	public ArrayList<AbstractMonster> generateMonsters();
	public boolean isMonstersAlive();
	public AbstractMonster getMonster();
	
}
