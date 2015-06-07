package monster.Interfaces;

import java.util.ArrayList;

import monster.Fonte.AbstractMonster;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/** Interface com as operacoes basicas de gerenciamento de monstros do componente. */

@ComponentInterface("<http://santanvarzea.com/monster.Interfaces.IMonster>")
public interface IMonster extends ISupports {

	public void generateMonsters();
	public boolean isMonstersAlive();
	public AbstractMonster getMonster();
	
}
