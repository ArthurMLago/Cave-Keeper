package monster.Interfaces;

import java.util.ArrayList;

import monster.Fonte.AbstractMonster;
import anima.component.ISupports;

public interface IMonsters extends ISupports {

	public ArrayList<AbstractMonster> generateMonsters();
	public boolean isMonstersAlive();
	
}
